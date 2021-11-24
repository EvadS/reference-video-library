package com.se.video.library.services.impl;

import com.se.video.library.dao.models.Country;
import com.se.video.library.dao.models.Genre;
import com.se.video.library.dao.repository.GenreRepository;
import com.se.video.library.errors.exception.AlreadyExistException;
import com.se.video.library.mappers.CountryMapper;
import com.se.video.library.mappers.GenreMapper;
import com.se.video.library.payload.request.GenreItemResponse;
import com.se.video.library.payload.request.GenreRequest;
import com.se.video.library.payload.response.GenreResponse;
import com.se.video.library.services.GenreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Slf4j
@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final  GenreRepository repository;

    @Override
    public GenreResponse create(GenreRequest genreRequest) {
        Optional<Genre> first = repository.findAllByName(genreRequest.getName()).stream()
                .findFirst();

        if(first.isPresent()){
            throw  new AlreadyExistException("Genre", "name", genreRequest.getName());
        }

        Genre genre = new Genre();
        genre.setName(genreRequest.getName());

        repository.save(genre);
        return GenreMapper.INSTANCE.toGenreResponse(genre);

    }

    @Override
    public List<GenreItemResponse> getAll() {
        return repository.findAll().stream()
                .map(GenreMapper.INSTANCE::toGenreItemResponse)
                .collect(Collectors.toList());
    }
}
