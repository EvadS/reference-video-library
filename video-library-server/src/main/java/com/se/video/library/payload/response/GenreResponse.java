package com.se.video.library.payload.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
@Schema(description = "Provide information about country")
public class GenreResponse {

    @Schema(description = "unique identifier.", required = true)
    private long id;

    @NotNull(message = "Name cannot be null")
    @NotBlank(message = "Name cannot be Empty")
    @Schema(description = "Name of the genre.",
            example = "Comedy", required = true)
    private String name;
}
