package com.se.video.library.controllers;

import com.se.video.library.config.constant.ControllerConstants;
import com.se.video.library.constraint.GeneralConstants;
import com.se.video.library.errors.models.ErrorDetail;
import com.se.video.library.payload.request.CountryRequest;
import com.se.video.library.payload.response.CountryDetailsResponse;
import com.se.video.library.payload.response.CountryResponse;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.Explode;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

public interface CountryApi {

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = GeneralConstants.HTTP_OK_SUCCESS),
            @ApiResponse(
                    responseCode = "400",
                    description = GeneralConstants.HTTP_BAD_REQUEST,
                    content = @Content(schema = @Schema(implementation = ErrorDetail.class))),
            @ApiResponse(
                    responseCode = "401",
                    description = GeneralConstants.HTTP_UNAUTHORIZED,
                    content = @Content(schema = @Schema(implementation = ErrorDetail.class))),
            @ApiResponse(
                    responseCode = "500",
                    description = GeneralConstants.HTTP_INTERNAL_ERROR,
                    content = @Content(schema = @Schema(implementation = ErrorDetail.class)))
    })
    @PostMapping
    @ResponseBody
    ResponseEntity<CountryResponse> create(
            @Parameter(description = "country model",schema = @Schema(implementation = CountryRequest.class))
            @RequestBody @Valid CountryRequest request);

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = GeneralConstants.HTTP_OK_SUCCESS,
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = CountryDetailsResponse.class)))
                    }),
            @ApiResponse(
                    responseCode = "400",
                    description = GeneralConstants.HTTP_BAD_REQUEST,
                    content = @Content(schema = @Schema(implementation = ErrorDetail.class))),
            @ApiResponse(
                    responseCode = "401",
                    description = GeneralConstants.HTTP_UNAUTHORIZED,
                    content = @Content(schema = @Schema(implementation = ErrorDetail.class))),
            @ApiResponse(
                    responseCode = "500",
                    description = GeneralConstants.HTTP_INTERNAL_ERROR,
                    content = @Content(schema = @Schema(implementation = ErrorDetail.class)))
    })
    @Operation(summary = "list of countries", description = "", tags = {"countries"})
    @GetMapping(ControllerConstants.COUNTRY_BY_ID)
    @ResponseBody
    ResponseEntity<CountryDetailsResponse> getById(
            @Parameter(in = ParameterIn.PATH, description = "unique identifier", required = true,
                    schema = @Schema(implementation = Long.class))
            @PathVariable(name = "id") Long id);

    @GetMapping(ControllerConstants.GENRE_LIST)
    @ResponseBody
    ResponseEntity<List<CountryResponse>> getAll(
            @Parameter(description = "search bt name condition", required = false)
            @RequestParam(required = false) String name);

    @PutMapping(ControllerConstants.COUNTRY_BY_ID)
    @ResponseBody
    ResponseEntity<CountryResponse> update(
            @Parameter(description = "unique identifier to be searched", required = true)
            @PathVariable(name = "id") Long id,
            @Parameter(description = "country model")
            @RequestBody @Valid CountryRequest request);

    @DeleteMapping(ControllerConstants.COUNTRY_BY_ID)
    ResponseEntity<HttpStatus> delete(
            @Parameter(description = "unique identifier to be searched", required = true)
            @PathVariable("id") long id);
}
