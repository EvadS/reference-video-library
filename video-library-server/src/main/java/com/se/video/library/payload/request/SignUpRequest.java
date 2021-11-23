package com.se.video.library.payload.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Schema(description = "Provide model for registration")
public class SignUpRequest {

    @Schema(description = "user name",
            example = "username", required = true)
    @NotBlank
    @Size(min = 4, max = 40)
    private String username;

    @Schema(description = "user name",
            example = "username", required = true)
    @NotBlank
    @Size(max = 40)
    @Email
    private String email;

    @Schema(description = "user pwd", required = true)
    @NotBlank
    @Size(min = 6, max = 20)
    private String password;
}
