package com.se.video.library.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.se.video.library.constraint.EnumValidator;
import com.se.video.library.payload.enums.Genre;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Schema(description = "Provide film model")
public class FilmRequest {

    @NotNull(message = "Name cannot be null")
    @NotBlank(message = "Name cannot be Empty")
    @Schema(description = "Name of the film.",
            example = "Film", required = true)
    private String name;

    @JsonProperty("genre_id")
    @EnumValidator(
            enumClazz = Genre.class,
            message = "This error is coming from the enum class"
    )
    private String genre;

    @NotNull(message = "Year cannot be null")
    @Schema(description = "Year of the film.",
            example = "2000", title ="year", required = true)
    private Integer year;

    @NotNull(message = "Director cannot be null")
    @NotBlank(message = "Director cannot be Empty")
    @Schema(description = "Director of the film.",
            example = " Robert Weide",title ="director", required = true)
    private String director;

    @NotNull(message = "Duration cannot be null")
    @Schema(description = "Duration of the film in minutes.",
            example = "121", title ="duration", required = true)
    private  Integer duration;
}
