package com.se.video.library.mappers;

import com.se.video.library.dao.models.Country;
import com.se.video.library.payload.request.CountryItemResponse;
import com.se.video.library.payload.response.CountryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper
public interface CountryMapper {

    CountryMapper INSTANCE = Mappers.getMapper(CountryMapper.class);

    CountryResponse toCountryResponse(Country country);

}