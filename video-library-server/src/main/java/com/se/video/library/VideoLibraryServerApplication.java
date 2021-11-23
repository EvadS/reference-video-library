package com.se.video.library;

import com.se.video.library.config.AppProperties;
import com.se.video.library.config.FileStorageProperties;
import com.se.video.library.config.JpaAuditingConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
@EnableConfigurationProperties({
        AppProperties.class,
        FileStorageProperties.class
})
public class VideoLibraryServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(VideoLibraryServerApplication.class, args);
    }

}
