package com.se.video.library.payload.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CountryDetailsResponse {

    @Schema(description = "unique identifier.", required = true)
    private long id;

    @Schema(description = "name.", required = true)
    private String name;

    private boolean enabled = true;
}
