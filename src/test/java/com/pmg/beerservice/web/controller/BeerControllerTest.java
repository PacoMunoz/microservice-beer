package com.pmg.beerservice.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pmg.beerservice.domain.Beer;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class BeerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void listBeers() throws Exception {
        mockMvc.perform(get("/api/v1/beer"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void getBeerById() throws Exception {
        mockMvc.perform(get("/api/v1/beer/016d07ad-4ec2-4a4f-9c72-1c69e2c85e1a")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().string(Matchers.containsString("Cruzcampo")));
    }

    @Test
    void getBeerByUPC() throws Exception {
        mockMvc.perform(get("/api/v1/beerUPC/aaw")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().string(Matchers.containsString("Cruzcampo")));
    }

    @Test
    void saveNewBeer() throws Exception {

         String newBeer = new ObjectMapper().writeValueAsString(
                Beer.builder()
                        .id(UUID.randomUUID())
                        .beerName("Cruzcampo")
                        .beerStyle("Super")
                .build());


        mockMvc.perform(post("/api/v1/beer")
                .content(newBeer)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().string(Matchers.containsString("Cruzcampo")));
    }

    @Test
    void updateBeer() throws Exception {

        String updateBeer = new ObjectMapper().writeValueAsString(
                Beer.builder()
                        .id(UUID.randomUUID())
                        .beerName("Cruzcampo")
                        .beerStyle("Super")
                        .build());

        mockMvc.perform(put("/api/v1/beer/016d07ad-4ec2-4a4f-9c72-1c69e2c85e1a")
                .content(updateBeer)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(content().string(Matchers.containsString("Cruzcampo")));

    }
}
