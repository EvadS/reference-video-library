package com.se.video.library.payload.request;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Schema(description = "Provide country model")
public class CountryRequest {

    @NotNull(message = "Name cannot be null")
    @NotBlank(message = "Name cannot be Empty")
    @Schema(description = "Name of the country.",
            example = "Ukraine", required = true)
    private String name;
}
