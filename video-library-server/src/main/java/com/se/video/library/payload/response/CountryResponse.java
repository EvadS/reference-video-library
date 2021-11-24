package com.se.video.library.payload.response;

import com.se.video.library.payload.request.CountryRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


@Data
@Schema(description = "Provide information about country")
public class CountryResponse {

    @Schema(description = "unique identifier.", required = true)
    private long id;

    private CountryRequest countryRequest;
}
