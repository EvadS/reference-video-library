package com.se.video.library.util;


import org.apache.commons.io.FilenameUtils;
import org.apache.tika.Tika;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.UUID;

public class ImageUtils {

    public static final String PREVIEW_PREFFIX = "_";
    // TODO: move to constants
    private final int width = 200;
    private final int recomendedHeight = 200;

    public static BufferedImage cropImage(BufferedImage img,
                                          ResizeModel resizeModel) {

        // change size
        BufferedImage resized = resizeBufferedImage(img, resizeModel.getPreviewWidth(), resizeModel.getPreviewHeight());

        // Crop
        BufferedImage croppedImage = resized.getSubimage(
                resizeModel.getDx(),
                resizeModel.getDy(),
                resizeModel.getCroppedWidth()
                , // widht
                resizeModel.getCroppedHeight()); // height

        return croppedImage;
    }

    public static BufferedImage resizeBufferedImage(BufferedImage img, int width, int height) {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();

        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }

    /**
     * @param originalWidth
     * @param originalHeight
     * @param destinationWidth
     * @param recommendedHeight
     * @return
     */
    public static ResizeModel buildResizeModelByCurrentSize(int originalWidth, int originalHeight
            , int destinationWidth, int recommendedHeight) {
        ResizeModel model;

        Dimension scalableSize = getDimensionsByFixedWidth(originalWidth, originalHeight, destinationWidth);

        int dx = 0;
        int dy = 0;

        int preview_width = scalableSize.width;
        int preview_height = scalableSize.height;

        // landscape
        if (scalableSize.width > destinationWidth) {
            int diffWidth = scalableSize.width - destinationWidth;
            dx = diffWidth / 2;

            model = new ResizeModel(dx, dy, preview_width, preview_height, destinationWidth, recommendedHeight);
        }

        // portrait
        if (scalableSize.height > recommendedHeight) {
            int diffHeight = scalableSize.height - recommendedHeight;
            dy = diffHeight / 2;

            model = new ResizeModel(dx, dy, preview_width, preview_height, destinationWidth, recommendedHeight);
        } else if (recommendedHeight > scalableSize.height) {
            preview_height = scalableSize.height;

            model = new ResizeModel(dx, dy, preview_width, preview_height, destinationWidth, preview_height);
        } else {
            // w == h
            model = new ResizeModel(dx, dy, preview_width, preview_height, destinationWidth, recommendedHeight);
        }

        return model;
    }

    public static Dimension getDimensionsByFixedWidth(int currWidth, int currHeight, int width) {
        float destWidth = currWidth;
        float destHeigth = currHeight;

        float ratio = width / destWidth;

        destWidth = width;
        destHeigth = currHeight * ratio;

        return new Dimension((int) destWidth, (int) destHeigth);
    }

    public static String buildImageName(MultipartFile file) {

        Tika tika = new Tika();
        String mimeType = null;
        try {
            String detect = tika.detect(file.getBytes());

            String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());

            return UUID.randomUUID().toString() + "." + fileExtension;

        } catch (Exception ex) {
            // TODO:  exception handling
            return "test";
        }
    }

    public static String buildPreviewImageName(String fileName) {
        return PREVIEW_PREFFIX + fileName;
    }
}
