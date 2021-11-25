package com.se.video.library.services.impl;

import com.se.video.library.model.Country;
import com.se.video.library.model.repository.CountryRepository;
import com.se.video.library.errors.exception.AlreadyExistException;
import com.se.video.library.mappers.CountryMapper;
import com.se.video.library.payload.request.CountryItemResponse;
import com.se.video.library.payload.request.CountryRequest;
import com.se.video.library.payload.response.CountryResponse;
import com.se.video.library.services.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public CountryResponse create(CountryRequest countryRequest) {

        Optional<Country> first = countryRepository.findAllByName(countryRequest.getName()).stream()
                .findFirst();

        if(first.isPresent()){
            throw  new AlreadyExistException("Country", "name", countryRequest.getName());
        }

        Country country = new Country();
        country.setName(countryRequest.getName());

        countryRepository.save(country);
        return CountryMapper.INSTANCE.toCountryResponse(country);
    }

    @Override
    public List<CountryItemResponse> getAll() {

        return countryRepository.findAll().stream()
                .map(CountryMapper.INSTANCE::toCountryItemResponse)
                .collect(Collectors.toList());
    }
}
