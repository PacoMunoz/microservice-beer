package com.pmg.beerservice.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pmg.beerservice.services.BeerService;
import com.pmg.brewery.model.BeerDto;
import com.pmg.brewery.model.BeerPagedList;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
// change the RequestBuilder for RestDoc works.
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@SpringBootTest
//@AutoConfigureMockMvc
@WebMvcTest(BeerController.class)
@ExtendWith(RestDocumentationExtension.class)
class BeerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    BeerService beerService;

    public static final UUID BEER_1_UUID = UUID.fromString("0a818933-087d-47f2-ad83-2f986ed087eb");
    public static final UUID BEER_2_UUID = UUID.fromString("a712d914-61ea-4623-8bd0-32c0f6545bfd");
    public static final String BEER_1_UPC = "0631234200036";
    public static final String BEER_2_UPC = "0631234300019";
    public static final String BEER_1_NAME = "Cruzcampo";
    public static final String BEER_2_NAME = "Alhambra";
    public static final String BEER_1_STYLE = "Suave";
    public static final String BEER_2_STYLE = "Media";

    BeerDto beerMock;
    BeerDto beerMock1;

    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext, RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation))
                .build();

        beerMock = BeerDto.builder()
                .id(BEER_1_UUID)
                .upc(BEER_1_UPC)
                .beerName(BEER_1_NAME)
                .beerStyle(BEER_1_STYLE)
                .price(new BigDecimal(12.50))
                .createdDate(OffsetDateTime.now())
                .quantityOnHand(10)
                // .localDate(LocalDate.now())
                .build();
        beerMock1 = BeerDto.builder()
                .id(BEER_2_UUID)
                .upc(BEER_2_UPC)
                .beerName(BEER_2_NAME)
                .beerStyle(BEER_2_STYLE)
                .price(new BigDecimal(12.05))
                .quantityOnHand(10)
                .build();
    }

    @AfterEach
    void teardown(){
        reset(beerService);
    }

    @DisplayName("Testing List Methods")
    @Nested
    public class TestListOperation {

        BeerPagedList beerPagedList;

        @Captor
        ArgumentCaptor<Boolean> showInventoryOnHandCaptor;
        @Captor
        ArgumentCaptor<String> beerNameCaptor;
        @Captor
        ArgumentCaptor<String> beerStyleCaptor;
        @Captor
        ArgumentCaptor<Integer> pageCaptor;
        @Captor
        ArgumentCaptor<Integer> sizeCaptor;

        @BeforeEach
        void setUp() {
            List<BeerDto> beerDtos = new ArrayList<>();

            beerDtos.add(beerMock);
            beerDtos.add(beerMock1);

            beerPagedList = new BeerPagedList(beerDtos, PageRequest.of(0,2), 2L);

            // Init test state -> mockito return a beerPageList and is ready to test
            given(beerService.getAllBeers(showInventoryOnHandCaptor.capture(), beerNameCaptor.capture(), beerStyleCaptor.capture()
                    , pageCaptor.capture(), sizeCaptor.capture())).willReturn(beerPagedList);

        }

        @DisplayName("Test No Params")
        @Test
        void testNoParams() throws Exception{

            // test the controller
            mockMvc.perform(get("/api/v1/beer").accept(MediaType.APPLICATION_JSON))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                        .andExpect(jsonPath("$.content", hasSize(2)));

            // the controller test should boot getAllBeers from BeerService
            then(beerService).should().getAllBeers(anyBoolean(), isNull(), isNull(), anyInt(), anyInt());

            // assert default param values work fine
            assertThat(0).isEqualTo(pageCaptor.getValue());
            assertThat(2).isEqualTo(sizeCaptor.getValue());
        }

        @DisplayName("Test beer name parameter")
        @Test
        void testBeerNameParam() throws Exception {

            mockMvc.perform(get("/api/v1/beer")
                    .accept(MediaType.APPLICATION_JSON)
                    .param("beerName", BEER_1_NAME))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.content", hasSize(2)));

            then(beerService).should().getAllBeers(anyBoolean(), anyString(), isNull(), anyInt(), anyInt());

            assertThat(BEER_1_NAME).isEqualTo(beerNameCaptor.getValue());
            assertThat(0).isEqualTo(pageCaptor.getValue());
            assertThat(2).isEqualTo(sizeCaptor.getValue());

        }

        @DisplayName("Test beer style parameter")
        @Test
        void testBeerStyleParam() throws Exception {

            mockMvc.perform(get("/api/v1/beer")
                    .accept(MediaType.APPLICATION_JSON)
                    .param("beerStyle", BEER_1_STYLE))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.content", hasSize(2)));

            then(beerService).should().getAllBeers(anyBoolean(), isNull(), anyString(), anyInt(), anyInt());

            assertThat(BEER_1_STYLE).isEqualTo(beerStyleCaptor.getValue());
            assertThat(0).isEqualTo(pageCaptor.getValue());
            assertThat(2).isEqualTo(sizeCaptor.getValue());

        }
        @DisplayName("Test beer pagination parameters")
        @Test
        void testBeerPaginationParameters() throws Exception {
            mockMvc.perform(get("/api/v1/beer").accept(MediaType.APPLICATION_JSON)
                    .param("pageNumber", "2")
                    .param("pageSize", "4"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.content", hasSize(2)));

            then(beerService).should().getAllBeers(anyBoolean(), isNull(), isNull(), anyInt(), anyInt());

            assertThat(2).isEqualTo(pageCaptor.getValue());
            assertThat(4).isEqualTo(sizeCaptor.getValue());

        }

        //TODO: create test to get quantityOnHand from repository service

    }

    @Test
    void getBeerById() throws Exception {

        given(beerService.getBeerById(ArgumentMatchers.any(UUID.class), anyBoolean())).willReturn(beerMock);

        mockMvc.perform(get("/api/v1/beer/{beerId}", UUID.randomUUID()).accept(MediaType.APPLICATION_JSON))
            .andDo(document("locations", pathParameters(
                    parameterWithName("beerId").description("BeerId to search")
            )))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.beerName", is(BEER_1_NAME)))
            .andExpect(jsonPath("$.createdDate").isNotEmpty());

        then(beerService).should().getBeerById(ArgumentMatchers.any(UUID.class), anyBoolean());

    }

    @Test
    void getBeerByUPC() throws Exception {

        given(beerService.getBeerByUPC(anyString())).willReturn(beerMock1);

        mockMvc.perform(get("/api/v1/beerUPC/" + BEER_1_UPC).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(containsString("Alhambra")));
    }

    @DisplayName("Test Update Beers")
    @Nested
    public class update {

        @DisplayName("Update beer ok")
        @Test
        void updateBeer() throws Exception {

            BeerDto modifiedBeer = beerMock;
            modifiedBeer.setId(null);
            modifiedBeer.setCreatedDate(null);
            modifiedBeer.setLastModifiedDate(null);
            modifiedBeer.setBeerName("Estrella Galicia");
            String updateBeer = objectMapper.writeValueAsString(modifiedBeer);

            mockMvc.perform(put("/api/v1/beer/" + BEER_1_UUID.toString())
                    .content(updateBeer)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isOk());

            then(beerService).should().updateBeer(any(),any());
        }

        @DisplayName("Update beer bad params")
        @Test
        void updateBeerBadParam() throws Exception {

            BeerDto modifiedBeer = beerMock;
            modifiedBeer.setId(null);
            modifiedBeer.setBeerName(null);
            String updateBeer = objectMapper.writeValueAsString(modifiedBeer);

            given(beerService.createBeer(any())).willReturn(beerMock);

            mockMvc.perform(put("/api/v1/beer/" + UUID.randomUUID())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(updateBeer))
                    .andExpect(status().isBadRequest());

            then(beerService).shouldHaveNoInteractions();

        }

    }

    @DisplayName("Test Save Beers")
    @Nested
    public class save {

        @DisplayName("Save beer ok")
        @Test
        void saveBeer() throws Exception {
            //given
            BeerDto savedBeer = beerMock1;
            savedBeer.setId(null);
            String newBeer = objectMapper.writeValueAsString(savedBeer);

            given(beerService.createBeer(any())).willReturn(savedBeer);

            mockMvc.perform(post("/api/v1/beer")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(newBeer))
                    .andDo(print())
                    .andExpect(status().isCreated());

            then(beerService).should().createBeer(any());

        }

        @DisplayName("Save beer bad params")
        @Test
        void saveBeerBadParam() throws Exception {

            //given
            BeerDto badParamsBeer = beerMock1;
            badParamsBeer.setBeerName(null);
            String newBeer = objectMapper.writeValueAsString(badParamsBeer);

            given(beerService.createBeer(any())).willReturn(beerMock1);

            mockMvc.perform(post("/api/v1/beer")
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(newBeer))
                    .andDo(print())
                    .andExpect(status().isBadRequest());

            then(beerService).shouldHaveNoInteractions();

        }
    }

    // testeamos la serializacion y desserializacion de json a objetos y viceversa.
    // Utilizamos las anotaciones de jackson en la clase DTO para modelar el proceso a nuestro gusto.

    @DisplayName("Json Serialization test")
    @Test
    void testJsonSerialization() throws JsonProcessingException {
        String json = "{\"id\":\"0a818933-087d-47f2-ad83-2f986ed087eb\",\"version\":null,\"createdDate\":\"2022-05-17T06:18:53+0200\",\"lastModifiedDate\":null,\"beerName\":\"Cruzcampo\",\"beerStyle\":\"Suave\",\"upc\":\"0631234200036\",\"price\":12.5,\"quantityOnHand\":10}";
        BeerDto beerDto = objectMapper.readValue(json, BeerDto.class);
        System.out.println(beerDto);
    }

    @DisplayName("Json Deserialization")
    @Test
    void testJsonDeserialization() throws JsonProcessingException {
        String json = objectMapper.writeValueAsString(beerMock);
        System.out.println(json);

    }

}
