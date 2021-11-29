package com.se.video.library.services.impl;

import com.se.video.library.config.FileStorageProperties;
import com.se.video.library.errors.exception.FileStorageException;
import com.se.video.library.payload.FileDataModel;
import com.se.video.library.payload.enums.MediaResourceType;
import com.se.video.library.services.FileStoreService;
import com.se.video.library.util.ImageUtils;
import com.se.video.library.util.ResizeModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.tika.Tika;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Collectors;

@Service
@Slf4j
public class LocalFileStoreService implements FileStoreService {

    //TODO: rename
    private static final String FOLDER = "media";
    private final String mediaRootLocation;


    public LocalFileStoreService(FileStorageProperties properties) {
        this.mediaRootLocation = properties.getUploadDir();
    }


    public void validateMultiPartFile(MultipartFile file) {
//
//        if (file.isEmpty()) {
//            throw new StorageException("Failed to store empty file " + file.getName());
//        }
//
//        String filename = StringUtils.cleanPath(file.getOriginalFilename());
//
//        if (filename.contains(".."))
//            throw new StorageException(String.format("Cannot store file with relative path outside current directory %s", filename));
    }


    @Override
    public String saveFile(FileDataModel mediaDataModel) {
        Path targetFolderPath = getFolderPath(mediaDataModel.getId(), mediaDataModel.getMediaResourceType());
        File folder = new File(targetFolderPath.toString());

        if (!folder.exists()) {
            folder.mkdirs();
        }

        String fileName = StringUtils.cleanPath(ImageUtils.buildImageName(mediaDataModel.getFile()));
        try {

            MultipartFile file = mediaDataModel.getFile();

            Path targetLocationFull = targetFolderPath.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocationFull, StandardCopyOption.REPLACE_EXISTING);


            String previewFileName = ImageUtils.buildPreviewImageName(fileName);

            scaleImage(targetFolderPath, fileName ,previewFileName);

            return fileName;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }


    private void scaleImage(Path filePath, String fileName, String previewFileName){
        // TODO: move to properties
        int previewHeight = 150;
        int previewWidth = 150;

         BufferedImage previewBufferedImage = null;
        try {

            Path targetLocationFull = filePath.resolve(fileName);
            previewBufferedImage = ImageIO.read(new File(targetLocationFull.toString()));


            int originalWidth = previewBufferedImage.getWidth();
            int originalHeight = previewBufferedImage.getHeight();

            ResizeModel resizeModel = ImageUtils.buildResizeModelByCurrentSize(originalWidth,
                    originalHeight, previewWidth, previewHeight);

            BufferedImage croppedBufferedImage = ImageUtils.cropImage(previewBufferedImage, resizeModel);


            Path targetLocationFull2 = filePath.resolve(previewFileName);

            File output = new File(targetLocationFull2.toString());
            ImageIO.write(croppedBufferedImage, "png", output);
        }catch (Exception e){
            log.error("some error" + e.getMessage());
        }
    }

    @Override
    public String updateMediaName(String fileName, FileDataModel mediaModel) {

        deleteFile(fileName, mediaModel);

        return saveFile(mediaModel);
    }

    @Override
    public void deleteFile(String fileName, FileDataModel mediaModel) {
        Path targetFolderPath = getFolderPath(mediaModel.getId(), mediaModel.getMediaResourceType());

        Path targetLocationFull = targetFolderPath.resolve(fileName);
        File file = new File(targetLocationFull.toString());
        if (file.exists()) {
            file.delete();
        }
    }

    @Override
    public String getFilePath(Long id, MediaResourceType mediaResourceType, String fileName) {

        Path folderPath = getFolderPath(id, mediaResourceType);

        return folderPath.resolve(fileName).toString();

    }

    private Path getFolderPath(Long id, MediaResourceType mediaResourceType) {
        return Paths.get(mediaRootLocation, mediaResourceType.name(),
                String.valueOf(id));
    }


}
