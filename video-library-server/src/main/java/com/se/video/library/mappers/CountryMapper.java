package com.se.video.library.mappers;

import com.se.video.library.model.Country;
import com.se.video.library.payload.request.CountryItemResponse;
import com.se.video.library.payload.response.CountryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper
public interface CountryMapper {

    CountryMapper INSTANCE = Mappers.getMapper(CountryMapper.class);

    @Mapping(source = "country.id", target = "id")
    @Mapping(source = "country.name", target = "countryRequest.name")
    CountryResponse toCountryResponse(Country country);


    CountryItemResponse toCountryItemResponse(Country country);
}