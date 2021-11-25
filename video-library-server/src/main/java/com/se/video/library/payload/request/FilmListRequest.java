package com.se.video.library.payload.request;

import lombok.Data;

import java.util.List;

@Data
public class FilmListRequest {

    private String name;
    private String director;
    private Integer yearStart;
    private Integer yearEnd;

    private Integer  pageSize;
    private Integer  pageNumber;

}
