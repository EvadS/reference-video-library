package com.se.video.library.controllers;

import com.se.video.library.config.constant.ControllerConstants;
import com.se.video.library.payload.request.CountryItemResponse;
import com.se.video.library.payload.request.CountryRequest;
import com.se.video.library.payload.response.CountryDetailsResponse;
import com.se.video.library.payload.response.CountryResponse;
import com.se.video.library.services.CountryService;
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
@RequestMapping(ControllerConstants.COUNTRY_API)
@Slf4j
@RequiredArgsConstructor
@Validated
public class CountryController {

    private final CountryService countryService;

    @PostMapping
    @ResponseBody
    public ResponseEntity<CountryResponse> create(
            @Parameter(description = "country model")
            @RequestBody @Valid CountryRequest request) {
        CountryResponse countryResponse = countryService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(countryResponse);
    }


    @GetMapping(ControllerConstants.COUNTRY_BY_ID)
    @ResponseBody
    public ResponseEntity<CountryDetailsResponse> getById(
            @Parameter(description = "unique identifier to be searched", required = true)
            @PathVariable(name = "id" )final Long id) {
        log.info("call get country by id: {}", id);
        CountryDetailsResponse country = countryService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(country);
    }

    @GetMapping(ControllerConstants.GENRE_LIST)
    @ResponseBody
    public ResponseEntity<List<CountryResponse>> getAll(
            @Parameter(description = "search bt name condition", required = false)
            @RequestParam(required = false) String name) {
        List<CountryResponse> all = countryService.getAll(name);
        return ResponseEntity.status(HttpStatus.OK).body(all);
    }

    @PutMapping(ControllerConstants.COUNTRY_BY_ID)
    @ResponseBody
    public ResponseEntity<CountryResponse> update(
            @Parameter(description = "unique identifier to be searched", required = true)
            @PathVariable(name = "id" )final Long id,
            @Parameter(description = "country model")
            @RequestBody @Valid CountryRequest request) {
        CountryResponse countryResponse =  countryService.update(id, request);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(countryResponse);
    }

    @DeleteMapping(ControllerConstants.COUNTRY_BY_ID)
    public ResponseEntity<HttpStatus> delete(
            @Parameter(description = "unique identifier to be searched", required = true)
            @PathVariable("id") long id) {

            countryService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);

    }
}
