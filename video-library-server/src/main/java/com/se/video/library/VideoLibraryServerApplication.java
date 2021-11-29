package com.se.video.library;

import com.se.video.library.config.AppProperties;
import com.se.video.library.config.FileStorageProperties;
import com.se.video.library.payload.FileDataModel;
import com.se.video.library.payload.enums.MediaResourceType;
import com.se.video.library.services.FileStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

//@EnableJpaAuditing
@SpringBootApplication
@EnableConfigurationProperties({
        AppProperties.class,
        FileStorageProperties.class,FileStorageProperties.class
})
public class VideoLibraryServerApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(VideoLibraryServerApplication.class, args);
    }

    @Autowired
    FileStoreService fileStoreService;

    @Override
    public void run(String... args) throws Exception {
        FileDataModel fileDataModel = new FileDataModel(1L , null, MediaResourceType.FILM);

       // fileStoreService.saveFile(fileDataModel);
    }
}
