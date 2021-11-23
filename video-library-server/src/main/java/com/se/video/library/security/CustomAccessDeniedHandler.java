package com.se.video.library.security;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.se.video.library.errors.models.ErrorDetail;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationEntryPoint.class);

    @Value("${reflectoring.trace:false}")
    private boolean printStackTrace;


    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException {
        ErrorDetail errors = new ErrorDetail();
        errors.setStatus(HttpStatus.FORBIDDEN.value());
        errors.setMessage("ou do not have permission to access this resource.");

        if (printStackTrace) {
            errors.setStackTrace(ExceptionUtils.getStackTrace(accessDeniedException));
        }

        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        OutputStream output = response.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(output, errors);
        output.flush();
    }
}
