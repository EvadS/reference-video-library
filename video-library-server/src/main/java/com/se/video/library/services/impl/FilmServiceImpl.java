package com.se.video.library.services.impl;

import com.se.video.library.model.Country;
import com.se.video.library.model.Film;
import com.se.video.library.model.repository.CountryRepository;
import com.se.video.library.model.repository.FilmRepository;
import com.se.video.library.model.repository.GenreRepository;
import com.se.video.library.errors.exception.ResourceNotFoundException;
import com.se.video.library.mappers.FilmMapper;
import com.se.video.library.payload.request.FilmRequest;
import com.se.video.library.payload.response.FilmItemResponse;
import com.se.video.library.payload.response.FilmResponse;
import com.se.video.library.payload.response.PagedResponse;
import com.se.video.library.services.FileStorageService;
import com.se.video.library.services.FilmService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class FilmServiceImpl implements FilmService {

    private final FilmRepository filmRepository;
    private final FileStorageService fileStorageService;

    private final GenreRepository genreRepository;
    private final CountryRepository countryRepository;



    @Transactional
    @Override
    public FilmItemResponse create(FilmRequest request) {

        //TODO: check name is exists
        // check is genre exists
        request.getGenreIds().stream().forEach(i -> {
            if (!genreRepository.existsById(i)) {
                throw new ResourceNotFoundException("Genre", "id", i);
            }
        });

        // check is country exists
        request.getCountryIds().stream().forEach(i -> {
            if (!countryRepository.existsById(i)) {
                throw new ResourceNotFoundException("Country", "id", i);
            }
        });

        Film film = new Film();
        film.setName(request.getName());
        film.setDescription(request.getDescription());
        film.setSmallDescription(request.getSmallDescription());

        film.setDirector(request.getDirector());
        film.setYear(request.getYear());
        film.setDuration(request.getDuration());

        List<Country> allCountriesById = countryRepository.findAllById(request.getCountryIds());

        allCountriesById.stream().forEach(i->{
            film.addChild(i);
        });

        filmRepository.save(film);

        return FilmMapper.INSTANCE.toFilmItemResponse(film);
    }

    // Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "createdAt");
    @Override
    public PagedResponse<FilmItemResponse> getAllPaged(int page, int size) {
        return null;
    }

    @Override
    public FilmItemResponse update(Long id, FilmRequest request) {

        return filmRepository.findById(id)
                .map(flm -> {

                    flm.getCountries().clear();

                    List<Country> allCountriesById = countryRepository.findAllById(request.getCountryIds());
                    allCountriesById.stream().forEach(i->{
                        flm.addChild(i);
                    });


                    flm.setName(request.getName());

                    filmRepository.save(flm);
                    log.info("Film, id:{} updated", id);
                    return FilmMapper.INSTANCE.toFilmItemResponse(flm);
                }).orElseThrow(() -> new ResourceNotFoundException("Film", "id", id));
    }

    @Override
    public void delete(Long id) {
        Film film = filmRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Film", "id", id));

        filmRepository.delete(film);

        log.info("Film, id:{} deleted", id);
    }

    @Override
    public FilmResponse getById(Long id) {
        Film film = filmRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Film", "id", id));


//        List<CountryFilm> allByFilmId = countryFilmRepository.getAllByFilmId(id);

        return FilmMapper.INSTANCE.toFilmResponse(film);
    }

    @Override
    public List<FilmItemResponse> getAll() {
        return filmRepository.findAll().stream()
                .map(FilmMapper.INSTANCE::toFilmItemResponse)
                .collect(Collectors.toList());
    }

    @Override
    public String storeTitle(Long id, MultipartFile file) {

        Film film = filmRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Film", "id", id));

        String fileName = fileStorageService.storeFile(file);
        return fileName;
    }
}
