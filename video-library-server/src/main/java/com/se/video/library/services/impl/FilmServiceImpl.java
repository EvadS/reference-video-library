package com.se.video.library.services.impl;

import com.se.video.library.dao.models.FileItem;
import com.se.video.library.dao.repository.FileRepository;
import com.se.video.library.errors.exception.AlreadyExistException;
import com.se.video.library.errors.exception.ResourceNotFoundException;
import com.se.video.library.mappers.FilmMapper;
import com.se.video.library.dao.models.Country;
import com.se.video.library.dao.models.Film;
import com.se.video.library.dao.models.Genre;
import com.se.video.library.dao.repository.CountryRepository;
import com.se.video.library.dao.repository.FilmRepository;
import com.se.video.library.dao.repository.GenreRepository;
import com.se.video.library.dao.specification.FilmSpecification;
import com.se.video.library.payload.request.FilmRequest;
import com.se.video.library.payload.request.FilmSearchRequest;
import com.se.video.library.payload.response.FilmItemResponse;
import com.se.video.library.payload.response.FilmResponse;
import com.se.video.library.payload.response.PagedResponse;
import com.se.video.library.services.FileStorageService;
import com.se.video.library.services.FilmService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class FilmServiceImpl implements FilmService {

    private final FilmRepository filmRepository;
    private final FileStorageService fileStorageService;

    private final GenreRepository genreRepository;
    private final CountryRepository countryRepository;
    private final FileRepository fileRepository;

    @Autowired
    FilmSpecification filmSpecification;

    @Transactional
    @Override
    public FilmResponse create(FilmRequest request) {

        Optional<Film> byName = filmRepository.findByName(request.getName());
        if (byName.isPresent()) {
            throw new AlreadyExistException("Film", "name", request.getName());
        }

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
        allCountriesById.stream().forEach(i -> {
            film.addCountry(i);
        });

        List<Genre> allGenresIds = genreRepository.findAllById(request.getGenreIds());
        allGenresIds.stream().forEach(i -> {
            film.addGenre(i);
        });

        filmRepository.save(film);

        return FilmMapper.INSTANCE.toFilmItemResponse(film);
    }

     @Override
    public PagedResponse<FilmResponse> getAllPaged(int page, int size) {
        return null;
    }

    @Override
    public FilmResponse update(Long id, FilmRequest request) {

        Optional<Film> byNameAndIdNot = filmRepository.findByNameAndIdNot(request.getName(), id);
        if(byNameAndIdNot.isPresent()){
            throw new AlreadyExistException("Film", "name", request.getName());
        }

        return filmRepository.findById(id)
                .map(flm -> {

                    flm.getCountries().clear();
                    List<Country> allCountriesById = countryRepository.findAllById(request.getCountryIds());
                    allCountriesById.stream().forEach(i -> {
                        flm.addCountry(i);
                    });

                    flm.getGenres().clear();
                    List<Genre> allGenresById = genreRepository.findAllById(request.getGenreIds());
                    allGenresById.stream().forEach(i -> {
                        flm.addGenre(i);
                    });

                    flm.setName(request.getName());
                    flm.setDescription(request.getDescription());
                    flm.setSmallDescription(request.getSmallDescription());
                    flm.setDirector(request.getDirector());
                    flm.setDuration(request.getDuration());

                    filmRepository.save(flm);

                    log.info("Film, id:{} updated", id);
                    return FilmMapper.INSTANCE.toFilmItemResponse(flm);
                }).orElseThrow(() -> new ResourceNotFoundException("Film", "id", id));
    }

    @Override
    public void delete(Long id) {
        Film film = filmRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Film", "id", id));


        for (Country book : new HashSet<Country>(film.getCountries())) {
            film.removeCountry(book);
        }

        for (Genre genre : new HashSet<Genre>(film.getGenres())) {
            film.removeGenre(genre);
        }

        for (Genre genre : film.getGenres()) {
            film.removeGenre(genre);
        }

        filmRepository.delete(film);

        log.info("Film, id:{} deleted", id);
    }

    @Override
    public FilmResponse getById(Long id) {
        Film film = filmRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Film", "id", id));

        return FilmMapper.INSTANCE.toFilmItemResponse(film);
    }

    @Override
    public List<FilmResponse> getAll() {
        return filmRepository.findAll().stream()
                .map(FilmMapper.INSTANCE::toFilmItemResponse)
                .collect(Collectors.toList());
    }

    @Override
    public String storeTitle(Long id, MultipartFile file) {

        String filePath = fileStorageService.storeFile(file);


        FileItem fileEntity = new FileItem();

        fileEntity.setName("FILE_NAME");
        fileEntity.setFilePath(filePath);

        fileRepository.save(fileEntity);

        return filePath;
    }

    @Override
    public Page<FilmItemResponse> getPaged(FilmSearchRequest request, String[] sort) {
        List<Order> orders = new ArrayList<Order>();

        if (sort[0].contains(",")) {
            // will sort more than 2 fields
            // sortOrder="field, direction"
            for (String sortOrder : sort) {
                String[] _sort = sortOrder.split(",");
                orders.add(new Order(getSortDirection(_sort[1]), _sort[0]));
            }
        } else {
            // sort=[field, direction]
            orders.add(new Order(getSortDirection(sort[1]), sort[0]));
        }

        Pageable pageable = PageRequest.of(request.getPageNumber()
                , request.getPageSize(),
                Sort.by(orders));


        Page<FilmItemResponse> pagedFilmResponse = filmRepository.findAll(filmSpecification.getFilter(request),
                pageable)
                .map(FilmMapper.INSTANCE::toFilmResponse);

        return pagedFilmResponse;
    }

    private Sort.Direction getSortDirection(String direction) {
        if (direction.equals("asc")) {
            return Sort.Direction.ASC;
        } else if (direction.equals("desc")) {
            return Sort.Direction.DESC;
        }

        return Sort.Direction.ASC;
    }
}
