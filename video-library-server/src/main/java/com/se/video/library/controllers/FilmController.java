package com.se.video.library.controllers;


import com.se.video.library.config.constant.ControllerConstants;
import com.se.video.library.constraint.GeneralConstants;
import com.se.video.library.errors.models.ErrorDetail;
import com.se.video.library.payload.request.FilmSearchRequest;
import com.se.video.library.payload.request.FilmRequest;
import com.se.video.library.payload.response.FilmItemResponse;
import com.se.video.library.payload.response.FilmResponse;
import com.se.video.library.services.FilmService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/film")
@RequiredArgsConstructor
public class FilmController {
    //401

    private final FilmService filmService;

    // get by id block -->
    @Operation(
            summary = "Get  By Id",
            description = "Allow to get Item by Id",
            method = "GET",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved Product data",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = FilmItemResponse.class))),
                    @ApiResponse(responseCode = "404",
                            description = GeneralConstants.NOT_FOUND,
                            content = @Content(schema = @Schema(implementation = ErrorDetail.class))),
                    @ApiResponse(responseCode = "415",
                            description = GeneralConstants.INCORRECT_MEDIA_TYPE,
                            content = @Content(schema = @Schema(implementation = ErrorDetail.class))),
                    @ApiResponse(responseCode = "500",
                            description = GeneralConstants.HTTP_INTERNAL_ERROR,
                            content = @Content(schema = @Schema(implementation = ErrorDetail.class))),
            })
    @GetMapping("/{id}")
    @ResponseBody
    public FilmResponse getOne(
            @Parameter(description = "unique identifier to be searched")
            @PathVariable(name = "id") final Long id) {

        return filmService.getById(id);
    }


    @PatchMapping("/file/{id}")
    public ResponseEntity<String> uploadImage(
            @RequestParam("file") MultipartFile file,
            @Parameter(description = "unique identifier for film")
            @PathVariable(name = "id") final Long id) {

        String fileName = filmService.storeTitle(id, file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/" + ControllerConstants.FILES_URI+ "/")
                .path(fileName)
                .toUriString();

        return ResponseEntity.ok(fileDownloadUri);
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<FilmResponse> create(
            @Parameter(description = "film model")
            @RequestBody @Valid FilmRequest request) {
        FilmResponse filmResponse = filmService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(filmResponse);
    }


    @PutMapping("/{id}")
    public ResponseEntity<FilmResponse> update(
            @Parameter(description = "unique identifier to be searched")
            @PathVariable(name = "id") final Long id,
            @Parameter(description = "file model")
            @RequestBody @Valid FilmRequest request) {

        FilmResponse filmResponse = filmService.update(id, request);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(filmResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity create(
            @Parameter(description = "unique identifier to be searched")
            @PathVariable(name = "id") final Long id) {
        filmService.delete(id);
        return new ResponseEntity(id, HttpStatus.ACCEPTED);
    }

    //?sort=column1,direction1&sort=column2,direction2
    //sort=duration,asc&name,desc
    @PostMapping("/search")
    @ResponseBody
    public Page<FilmItemResponse> getAll(
            @RequestParam(defaultValue = "id,desc") String[] sort,
            @RequestBody @Valid FilmSearchRequest request) {

        Page<FilmItemResponse> filmResponses = filmService.getPaged(request,sort);

        return filmResponses;
    }

    @GetMapping("/list")
    @ResponseBody
    public List<FilmResponse> getAll() {

        List<FilmResponse> filmResponses = filmService.getAll();

        return filmResponses;
    }
    // <<-- get by id block
}
