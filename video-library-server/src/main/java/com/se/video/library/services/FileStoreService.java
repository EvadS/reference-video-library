package com.se.video.library.services;

import com.se.video.library.payload.FileDataModel;
import com.se.video.library.payload.enums.MediaResourceType;

public interface FileStoreService {
    String saveFile(FileDataModel mediaDataModel);

    String updateMediaName(String fileId, FileDataModel mediaModel);

    void deleteFile(String fileName ,FileDataModel mediaModel);

    String getFilePath(Long id, MediaResourceType mediaResourceType, String fileName);
}
