package com.se.video.library.services;

import com.se.video.library.payload.request.CountryItemResponse;
import com.se.video.library.payload.request.CountryRequest;
import com.se.video.library.payload.response.CountryResponse;

import java.util.List;

public interface CountryService {

    CountryResponse create (CountryRequest countryRequest);

    List<CountryItemResponse> getAll();
}
