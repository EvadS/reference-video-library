package com.se.video.library.services.impl;

import com.se.video.library.dao.models.Country;
import com.se.video.library.dao.models.Film;
import com.se.video.library.dao.repository.CountryRepository;
import com.se.video.library.dao.repository.FilmRepository;
import com.se.video.library.errors.exception.AlreadyExistException;
import com.se.video.library.errors.exception.DataBaseConstraintException;
import com.se.video.library.errors.exception.ResourceNotFoundException;
import com.se.video.library.mappers.CountryMapper;
import com.se.video.library.payload.IdNames;
import com.se.video.library.payload.request.CountryRequest;
import com.se.video.library.payload.response.CountryDetailsResponse;
import com.se.video.library.payload.response.CountryResponse;
import com.se.video.library.services.CountryService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;
    private final FilmRepository filmRepository;

    public CountryServiceImpl(CountryRepository countryRepository, FilmRepository filmRepository) {
        this.countryRepository = countryRepository;
        this.filmRepository = filmRepository;
    }

    @Override
    public CountryResponse create(CountryRequest countryRequest) {

        Optional<Country> first = countryRepository.findAllByName(countryRequest.getName()).stream()
                .findFirst();

        if (first.isPresent()) {
            throw new AlreadyExistException("Country", "name", countryRequest.getName());
        }

        Country country = new Country();
        country.setName(countryRequest.getName());

        countryRepository.save(country);
        return CountryMapper.INSTANCE.toCountryResponse(country);
    }


    @Override
    public CountryResponse update(Long id, CountryRequest request) {

        // validate name
        List<Country> byNameAndIdNot = countryRepository.findByNameAndIdNot(request.getName(), id);
        if (!byNameAndIdNot.isEmpty()) {
            throw new AlreadyExistException("Country", "name", request.getName());
        }

        Country country = countryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Country", "id", id));

        country.setName(request.getName());
        country.setEnabled(request.getEnabled());
        countryRepository.save(country);

        return CountryMapper.INSTANCE.toCountryResponse(country);
    }

    @Override
    public List<CountryResponse> getAll(String name) {

        List<Country> countriesList = new ArrayList<>();

        if (!StringUtils.isEmpty(name)) {
            countriesList = countryRepository.findByNameContaining(name);
        } else {
            countriesList = countryRepository.findAll();
        }

        return countriesList.stream()
                .map(CountryMapper.INSTANCE::toCountryResponse)
                .collect(Collectors.toList());
    }


    @Override
    public CountryDetailsResponse getById(Long id) {
        Country country = countryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Country", "id", id));

        CountryDetailsResponse countryResponse = new CountryDetailsResponse(country.getId(), country.getName(), true);

        return countryResponse;
    }

    @Override
    public void deleteById(long id) {
        Country country = countryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Country", "id", id));

        // TODO: check is usage
        List<Film> byCountriesContains = filmRepository.findByCountriesContains(country);

        if (!byCountriesContains.isEmpty()) {
            List<IdNames> collect = byCountriesContains.stream()
                    .map(i -> {
                        return new IdNames(i.getId(), i.getName());
                    })
                    .collect(Collectors.toList());

            // Convert elements to strings and concatenate them, separated by commas
            String joined = collect.stream()
                    //.map(Person::getName) // This will call person.getName()
                    .map(i-> {
                        return "{" + i.getId() +":"+ i.getName()+"}";
                    })
                    .collect(Collectors.joining("; "));
            // Resource  used in
            throw  new DataBaseConstraintException("Country", "id", joined);
        }
        //countryRepository.deleteById(id);
    }


}
