package com.se.video.library.controllers.api;

import com.se.video.library.config.constant.ControllerConstants;
import com.se.video.library.payload.request.GenreRequest;
import com.se.video.library.payload.response.GenreResponse;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public interface GenreApi {
    @PostMapping
    @ResponseBody
    ResponseEntity<GenreResponse> create(
            @Parameter(description = "genre model")
            @RequestBody @Valid GenreRequest request);

    @GetMapping(ControllerConstants.GENRE_BY_ID)
    @ResponseBody
    ResponseEntity<GenreResponse> getById(
            @Parameter(description = "unique identifier to be searched", required = true)
            @PathVariable(name = "id") Long id);

    @GetMapping("/list")
    @ResponseBody
    ResponseEntity<List<GenreResponse>> getAll(
            @Parameter(description = "search bt name condition", required = false)
            @RequestParam(required = false) String name);

    @PutMapping(ControllerConstants.GENRE_BY_ID)
    @ResponseBody
    ResponseEntity<GenreResponse> update(
            @Parameter(description = "unique identifier to be searched", required = true)
            @PathVariable(name = "id") Long id,
            @Parameter(description = "genre model")
            @RequestBody @Valid GenreRequest request);

    @DeleteMapping(ControllerConstants.GENRE_BY_ID)
    ResponseEntity<HttpStatus> delete(
            @Parameter(description = "unique identifier to be searched", required = true)
            @PathVariable("id") long id);
}
