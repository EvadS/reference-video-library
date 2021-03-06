package com.se.video.library.services;

import com.se.video.library.payload.request.GenreItemResponse;
import com.se.video.library.payload.request.GenreRequest;
import com.se.video.library.payload.response.GenreResponse;

import java.util.List;

public interface GenreService {
    GenreResponse create(GenreRequest genreRequest);

    List<GenreResponse> getAll(String name);

    GenreResponse getById(Long id);

    GenreResponse update(Long id, GenreRequest request);

    void delete(long id);
}
