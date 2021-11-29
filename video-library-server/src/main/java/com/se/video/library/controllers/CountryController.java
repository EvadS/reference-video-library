package com.se.video.library.controllers;

import com.se.video.library.config.constant.ControllerConstants;
import com.se.video.library.payload.request.CountryItemResponse;
import com.se.video.library.payload.request.CountryRequest;
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

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<CountryItemResponse>> getAll() {
        List<CountryItemResponse> all = countryService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(all);
    }

    @GetMapping(ControllerConstants.COUNTRY_BY_ID)
    @ResponseBody
    public ResponseEntity<CountryResponse> getById(
            @Parameter(description = "unique identifier to be searched", required = true)
            @PathVariable(name = "id" )final Long id) {
        log.info("call get country by id: {}", id);
        CountryResponse country = countryService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(country);
    }
}
