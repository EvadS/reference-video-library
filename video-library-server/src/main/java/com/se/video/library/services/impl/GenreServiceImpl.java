package com.se.video.library.services.impl;

import com.se.video.library.dao.models.Film;
import com.se.video.library.dao.models.Genre;
import com.se.video.library.dao.repository.FilmRepository;
import com.se.video.library.dao.repository.GenreRepository;
import com.se.video.library.errors.exception.AlreadyExistException;
import com.se.video.library.errors.exception.DataBaseConstraintException;
import com.se.video.library.errors.exception.ResourceNotFoundException;
import com.se.video.library.mappers.GenreMapper;
import com.se.video.library.payload.IdNames;
import com.se.video.library.payload.request.GenreRequest;
import com.se.video.library.payload.response.GenreResponse;
import com.se.video.library.services.GenreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Slf4j
@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;
    private final FilmRepository filmRepository;

    @Override
    public GenreResponse create(GenreRequest genreRequest) {
        Optional<Genre> first = genreRepository.findAllByName(genreRequest.getName()).stream()
                .findFirst();

        if (first.isPresent()) {
            throw new AlreadyExistException("Genre", "name", genreRequest.getName());
        }

        Genre genre = new Genre();
        genre.setName(genreRequest.getName());
        genre.setEnabled(true);

        genreRepository.save(genre);
        return GenreMapper.INSTANCE.toGenreResponse(genre);

    }

    @Override
    public List<GenreResponse> getAll(String name) {
        List<Genre> genres = new ArrayList<>();

        if (!StringUtils.isEmpty(name)) {
            genres = genreRepository.findByNameContaining(name);
        } else {
            genres = genreRepository.findAll();
        }

        return genres.stream()
                .map(GenreMapper.INSTANCE::toGenreResponse)
                .collect(Collectors.toList());
    }

    @Override
    public GenreResponse getById(Long id) {
        Genre genre = genreRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Genre", "id", id));

        return GenreMapper.INSTANCE.toGenreResponse(genre);
    }

    @Override
    public GenreResponse update(Long id, GenreRequest request) {
        // validate name
        List<Genre> byNameAndIdNot = genreRepository.findByNameAndIdNot(request.getName(), id);
        if (!byNameAndIdNot.isEmpty()) {
            throw new AlreadyExistException("Genre", "name", request.getName());
        }

        Genre genre = genreRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Genre", "id", id));

        genre.setName(request.getName());
        genre.setEnabled(request.getEnabled());
        genreRepository.save(genre);

        return GenreMapper.INSTANCE.toGenreResponse(genre);
    }

    @Override
    public void delete(long id) {
        Genre genre = genreRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Genre", "id", id));

        // TODO: check is usage
        List<Film> byCountriesContains = filmRepository.findByGenresContains(genre);

        if (!byCountriesContains.isEmpty()) {
            List<IdNames> collect = byCountriesContains.stream()
                    .map(i -> {
                        return new IdNames(i.getId(), i.getName());
                    })
                    .collect(Collectors.toList());

            // Convert elements to strings and concatenate them, separated by commas
            String joined = collect.stream()
                    //.map(Person::getName) // This will call person.getName()
                    .map(i -> {
                        return "{" + i.getId() + ":" + i.getName() + "}";
                    })
                    .collect(Collectors.joining("; "));
            // Resource  used in
            throw new DataBaseConstraintException("Genre", "id", joined);
        }
    }
}
