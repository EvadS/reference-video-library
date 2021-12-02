package com.se.video.library.services;

import com.se.video.library.payload.request.CountryRequest;
import com.se.video.library.payload.response.CountryDetailsResponse;
import com.se.video.library.payload.response.CountryResponse;

import java.util.List;

public interface CountryService {

    CountryResponse create(CountryRequest countryRequest);

    List<CountryResponse> getAll(String title);

    CountryDetailsResponse getById(Long id);

    void deleteById(long id);

    CountryResponse update(Long id, CountryRequest request);
}
