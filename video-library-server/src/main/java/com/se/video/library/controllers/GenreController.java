package com.se.video.library.controllers;

import com.se.video.library.payload.request.GenreItemResponse;
import com.se.video.library.payload.request.GenreRequest;
import com.se.video.library.payload.response.GenreResponse;
import com.se.video.library.services.GenreService;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/genre")
@RequiredArgsConstructor
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

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<GenreItemResponse>> getAll() {
        List<GenreItemResponse> all = genreService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(all);
    }
}
