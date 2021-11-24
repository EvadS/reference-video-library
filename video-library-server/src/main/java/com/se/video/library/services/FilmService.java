package com.se.video.library.services;

import com.se.video.library.payload.request.FilmRequest;
import com.se.video.library.payload.response.FilmItemResponse;
import com.se.video.library.payload.response.FilmResponse;
import com.se.video.library.payload.response.PagedResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FilmService {

    /**
     * create new file
     * @param request provide information about new film
     * @return provide information about created film
     */
    FilmItemResponse create(FilmRequest request);

    PagedResponse<FilmItemResponse> getAllPaged(int page, int size);

    FilmItemResponse update(Long id, FilmRequest request);

    void delete(Long id);

    FilmResponse getById(Long Id);

    List<FilmItemResponse> getAll();

    String storeTitle(Long id, MultipartFile file);
}
