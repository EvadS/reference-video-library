package com.se.video.library.payload.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.data.domain.Sort;

import javax.validation.constraints.Min;
import java.util.List;

@Data
@Schema(name = "FilmSearchRequest",
        description = "Provide film search request")
public class FilmSearchRequest {

    private String name;
    private String director;
    private Integer yearStart;
    private Integer yearEnd;

    @Min(1)
    @Schema(description = "number items on page",required = true,  defaultValue = "1")
    private Integer pageSize;

    @Min(0)
    @Schema(description = "current page", required = true,  defaultValue = "0")
    private Integer pageNumber;
}
