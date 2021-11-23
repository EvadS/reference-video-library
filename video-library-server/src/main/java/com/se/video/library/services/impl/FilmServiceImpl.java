package com.se.video.library.services.impl;

import com.se.video.library.dao.models.FilmEntity;
import com.se.video.library.dao.repository.FilmRepository;
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

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class FilmServiceImpl implements FilmService {

    private final FilmRepository filmRepository;
    private  final FileStorageService fileStorageService;

    @Override
    public FilmItemResponse addFilm(FilmRequest request) {

        FilmEntity filmEntity = FilmMapper.INSTANCE.toFilmEntity(request);

        filmRepository.save(filmEntity);

        return FilmMapper.INSTANCE.toFilmItemResponse(filmEntity);
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

                    flm.setName(request.getName());
                    filmRepository.save(flm);
                    log.info("Film, id:{} updated", id);
                    return FilmMapper.INSTANCE.toFilmItemResponse(flm);
                }).orElseThrow(() -> new ResourceNotFoundException("Film", "id", id));
    }

    @Override
    public void delete(Long id) {
        FilmEntity film = filmRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Film", "id", id));

        filmRepository.delete(film);

        log.info("Film, id:{} deleted", id);
    }

    @Override
    public FilmResponse getById(Long id) {
        FilmEntity film = filmRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Film", "id", id));

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

        FilmEntity film = filmRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Film", "id", id));

        String fileName = fileStorageService.storeFile(file);
        return fileName;
    }
}
