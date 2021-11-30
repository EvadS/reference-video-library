package com.se.video.library.payload.response;

import com.se.video.library.payload.request.CountryRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;


@Data
@AllArgsConstructor
@Schema(description = "Provide information about country")
@ToString
public class CountryResponse {

    @Schema(description = "unique identifier.", required = true)
    private long id;

    private CountryRequest countryRequest;

    private boolean enabled= false;
}
