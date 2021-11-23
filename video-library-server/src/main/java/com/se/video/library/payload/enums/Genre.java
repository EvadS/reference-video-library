package com.se.video.library.payload.enums;

import lombok.Getter;

import java.util.stream.Stream;

@Getter
public enum Genre {
    DETECTIVE(1),
    COMEDY(2),
    THRILLER(3),
    DRAMA(4);

    private int code;

    Genre(int code) {
        this.code = code;
    }

    /**
     * Get genre by code
     * @param code int Genre equivalent
     * @return Genre
     */
    public static Genre of(int code){
        return Stream.of(Genre.values())
                .filter(f-> f.getCode()==code)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

}
