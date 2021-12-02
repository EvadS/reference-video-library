package com.se.video.library.payload.response;

import com.se.video.library.payload.request.CountryRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
@AllArgsConstructor
@Schema(description = "Provide information about country")
@ToString
public class CountryResponse {

    @Schema(description = "unique identifier.", required = true)
    private long id;

    @NotNull(message = "Name cannot be null")
    @NotBlank(message = "Name cannot be Empty")
    @Schema(description = "Name of the country.",
            example = "Ukraine", required = true)
    private String name;

    @NotNull(message = "Name cannot be null")
    @Schema(description = "is country enabled.",required = true)
    private Boolean enabled;
}
