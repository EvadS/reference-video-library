package com.se.video.library.mappers;

import com.se.video.library.dao.models.FilmEntity;
import com.se.video.library.payload.request.FilmRequest;
import com.se.video.library.payload.response.FilmItemResponse;
import com.se.video.library.payload.response.FilmResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FilmMapper {

    FilmMapper INSTANCE = Mappers.getMapper(FilmMapper.class);

    FilmResponse toFilmResponse (FilmEntity filmEntity);

    FilmItemResponse toFilmItemResponse(FilmEntity filmEntity);

    FilmEntity toFilmEntity (FilmRequest filmEntity);
}
