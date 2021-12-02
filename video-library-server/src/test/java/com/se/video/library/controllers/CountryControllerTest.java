package com.se.video.library.controllers;

import com.se.video.library.dao.repository.CountryRepository;
import com.se.video.library.dao.repository.FilmRepository;
import com.se.video.library.services.CountryService;
import com.se.video.library.services.impl.CountryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(SpringExtension.class)
@Import(CountryServiceImpl.class)
public class CountryControllerTest {


    @MockBean
    CountryService countryService;

    @MockBean
    private CountryRepository countryRepository;

    @MockBean
    private  FilmRepository filmRepository;

    @BeforeEach
    public void init (){
        countryService = new CountryServiceImpl(countryRepository, filmRepository);
    }


    private static final Long countryId= 1000l;


    @Test
    public void canRetrieveByIdWhenExists() throws Exception {
//        Mockito.doReturn()
//
//
//        String skuNumber = "SKUNUMBER-1234";
//        try {
//            CatalogueItem catalogueItem = prepareCatalogueItem(skuNumber);
//            given(catalogueCrudService.getCatalogueItem(skuNumber)).willReturn(catalogueItem);
//
//            MvcResult result
//                    = this.mockMvc
//                    .perform(get(CatalogueControllerAPIPaths.BASE_PATH + CatalogueControllerAPIPaths.GET_ITEM, skuNumber))
//                    .andExpect(status().isOk())
//                    .andReturn();
//
//            // Compare response object skuNumber to the expected one
//            Assertions.assertEquals(
//                    objectMapper.readValue(result.getResponse().getContentAsString(), CatalogueItem.class).getSku(),
//                    skuNumber
//            );
//        }
//        catch(Exception e) {
//            Assertions.fail("Error occurred while getting catalogue items", e);
//        }
    }
}