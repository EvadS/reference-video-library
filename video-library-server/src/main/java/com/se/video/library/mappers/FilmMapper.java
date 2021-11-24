package com.se.video.library.mappers;

import com.se.video.library.dao.models.Country;
import com.se.video.library.dao.models.Film;
import com.se.video.library.payload.enums.Genre;
import com.se.video.library.payload.request.CountryItemResponse;
import com.se.video.library.payload.request.FilmRequest;
import com.se.video.library.payload.response.FilmItemResponse;
import com.se.video.library.payload.response.FilmResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface FilmMapper {

    FilmMapper INSTANCE = Mappers.getMapper(FilmMapper.class);

    FilmResponse toFilmResponse (Film filmEntity);

    FilmItemResponse toFilmItemResponse(Film filmEntity);

    default Genre map(int value){
        return  Genre.COMEDY;
    }


    List<CountryItemResponse> map(List<Country> employees);
}
