package com.se.video.library.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties(prefix = "app")
@Validated
@Data
public class AppProperties {

    private String jwtSecret;
    private Long jwtExpirationMs;

}
