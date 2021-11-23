package com.se.video.library.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "${application-name}",
                version = "${application-version}",
                description = "${application-description}",// TODO : sample
                contact = @Contact(
                        email = "evad.se@gmail.com"
                )
        )
)
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
@Configuration
public class OpenApiConfig {

//"This documentation app provides REST APIs"

//    application-description=@project.description@
//            application-version=@project.version@
//    application-description=@project.description@
//            application-version=@project.version@
//            application-name=Video library api
}
