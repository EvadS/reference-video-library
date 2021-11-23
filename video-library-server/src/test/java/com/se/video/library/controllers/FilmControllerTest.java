package com.se.video.library.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.se.video.library.config.ControllerConstants;
import com.se.video.library.payload.request.FilmRequest;
import com.se.video.library.services.FilmService;
import com.se.video.library.utils.FilmUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@ExtendWith(SpringExtension.class)
//@SpringBootTest
@AutoConfigureMockMvc

class FilmControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FilmService filmService;

    private static ObjectMapper objectMapper;



    @BeforeAll
    static void initAll() {

        // Initialize Jackson mapper to convert response json to object
        objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
    }



//    @Test
//    @DisplayName("test constraint validation exception")
//    void testValidationException() {
//        String name = "NUMBER-1234";
//        try {
//            FilmRequest catalogueItem = FilmUtils.prepareFilmItem(name);
//
//            // Set null for few variables to throw validation exception
//            catalogueItem.setDirector("");
//            catalogueItem.setName("");
//
//            given(filmService.addFilm(catalogueItem)).willReturn(FilmUtils.prepareFilmItemResponse(name));
//
//            MvcResult result
//                    = this.mockMvc
//                    .perform(
//                            post(ControllerConstants.BASE_PATH + ControllerConstants.FILM_API_PATH)
//                                    .contentType(MediaType.APPLICATION_JSON_VALUE)
//                                    .content(objectMapper.writeValueAsString(catalogueItem))
//                    )
//                    .andExpect(status().is(400))
//                    .andReturn();
//        }
//        catch(Exception e) {
//            Assertions.fail("Error occurred while verifying validation exception", e);
//        }
//    }

}