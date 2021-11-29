package com.se.video.library.payload;

import com.se.video.library.payload.enums.MediaResourceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
public class FileDataModel {
    private Long id;
    private MultipartFile file;
    private MediaResourceType mediaResourceType;
}
