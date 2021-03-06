package com.se.video.library.utils;

import com.se.video.library.payload.enums.Genre;
import com.se.video.library.payload.request.FilmRequest;
import com.se.video.library.payload.response.FilmResponse;
import com.se.video.library.payload.response.FilmItemResponse;

public class FilmUtils {


    public static FilmRequest prepareFilmItem(String name) {
        FilmRequest item = FilmRequest.builder()
                    .director("Director")
                    .duration(123)

                    .year(2020)
                    .name(name)
                .build();

        return item;
    }

    public static FilmItemResponse prepareFilmResponse(String name) {
        FilmItemResponse item = FilmItemResponse.builder()
                .id(1l)
                .director("Director")
                .duration(123)
                .genre(Genre.COMEDY)
                .year(2020)
                .name(name)
                .build();

        return item;
    }

    public static FilmResponse prepareFilmItemResponse(String name) {
        FilmResponse item = FilmResponse.builder()
                .id(1l)
                .director("Director")
                .duration(123)
                .name(name)
                .build();

        return item;
    }
}
