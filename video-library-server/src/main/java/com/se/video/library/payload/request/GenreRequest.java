package com.se.video.library.payload.request;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Schema(description = "Provide genre model")
public class GenreRequest {

    @NotNull(message = "Name cannot be null")
    @NotBlank(message = "Name cannot be Empty")
    @Schema(description = "Name of the genre.",
            example = "Comedy", required = true)
    private String name;
}
