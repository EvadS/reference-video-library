package com.se.video.library.mappers;

import com.se.video.library.dao.models.Genre;
import com.se.video.library.payload.request.GenreItemResponse;
import com.se.video.library.payload.response.GenreResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper
public interface GenreMapper {

    GenreMapper INSTANCE = Mappers.getMapper(GenreMapper.class);

    GenreResponse toGenreResponse(Genre genre);

    GenreItemResponse toGenreItemResponse(Genre genre);
}