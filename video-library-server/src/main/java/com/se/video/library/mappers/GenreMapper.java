package com.se.video.library.mappers;

import com.se.video.library.model.Genre;
import com.se.video.library.payload.request.GenreItemResponse;
import com.se.video.library.payload.response.GenreResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper
public interface GenreMapper {

    GenreMapper INSTANCE = Mappers.getMapper(GenreMapper.class);

    @Mapping(source = "genre.id", target = "id")
    @Mapping(source = "genre.name", target = "genreRequest.name")
    GenreResponse toGenreResponse(Genre genre);

    GenreItemResponse toGenreItemResponse(Genre genre);
}