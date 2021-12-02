package com.se.video.library.controllers;

import com.se.video.library.config.constant.ControllerConstants;
import com.se.video.library.payload.request.CountryRequest;
import com.se.video.library.payload.request.GenreItemResponse;
import com.se.video.library.payload.request.GenreRequest;
import com.se.video.library.payload.response.CountryResponse;
import com.se.video.library.payload.response.GenreResponse;
import com.se.video.library.services.GenreService;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(ControllerConstants.GENRE_API)
@Slf4j
@RequiredArgsConstructor
@Validated
public class GenreController {

    private final GenreService genreService;

    @PostMapping
    @ResponseBody
    public ResponseEntity<GenreResponse> create(
            @Parameter(description = "genre model")
            @RequestBody @Valid GenreRequest request) {
        GenreResponse genreResponse = genreService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(genreResponse);
    }


    @GetMapping(ControllerConstants.GENRE_BY_ID)
    @ResponseBody
    public ResponseEntity<GenreResponse> getById(
            @Parameter(description = "unique identifier to be searched", required = true)
            @PathVariable(name = "id" )final Long id) {
        log.info("call get genre by id: {}", id);
        GenreResponse genreResponse = genreService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(genreResponse);
    }

    @GetMapping("/list")
    @ResponseBody
    public ResponseEntity<List<GenreResponse>> getAll(
            @Parameter(description = "search bt name condition", required = false)
            @RequestParam(required = false) String name) {
        List<GenreResponse> all = genreService.getAll(name);
        return ResponseEntity.status(HttpStatus.OK).body(all);
    }


    @PutMapping(ControllerConstants.GENRE_BY_ID)
    @ResponseBody
    public ResponseEntity<GenreResponse> update(
            @Parameter(description = "unique identifier to be searched", required = true)
            @PathVariable(name = "id" )final Long id,
            @Parameter(description = "genre model")
            @RequestBody @Valid GenreRequest request) {
        GenreResponse genreResponse =  genreService.update(id, request);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(genreResponse);
    }

    @DeleteMapping(ControllerConstants.GENRE_BY_ID)
    public ResponseEntity<HttpStatus> delete(
            @Parameter(description = "unique identifier to be searched", required = true)
            @PathVariable("id") long id) {

        genreService.delete(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}