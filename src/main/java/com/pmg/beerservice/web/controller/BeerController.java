package com.pmg.beerservice.web.controller;

import com.pmg.beerservice.model.Beer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class BeerController {

    @GetMapping("/beer")
    public ResponseEntity<List<Beer>> listBeers() {
        Beer beer1 = Beer.builder()
                .beerName("Cruzcampo")
                .beerStyle("UTS")
                .quantityToBrew(12)
                .price(BigDecimal.valueOf(12.1))
                .createDate(Timestamp.from(Instant.now()))
                .lastModifiedDate(Timestamp.from(Instant.now()))
                .build();

        return new ResponseEntity<>(Arrays.asList(beer1), HttpStatus.OK);
    }

    @GetMapping("beer/{beerId}")
    public ResponseEntity<Beer> getBeerById(@PathVariable UUID beerId) {

        Beer beer1 = Beer.builder()
                .beerName("Cruzcampo")
                .beerStyle("UTS")
                .quantityToBrew(12)
                .price(BigDecimal.valueOf(12.1))
                .createDate(Timestamp.from(Instant.now()))
                .lastModifiedDate(Timestamp.from(Instant.now()))
                .build();

        return new ResponseEntity<>(beer1, HttpStatus.OK);
    }

    @GetMapping("beerUPC/{upc}")
    public ResponseEntity<Beer> getBeerByUPC(@PathVariable String upc) {
        Beer beer1 = Beer.builder()
                .beerName("Cruzcampo")
                .beerStyle("UTS")
                .quantityToBrew(12)
                .price(BigDecimal.valueOf(12.1))
                .createDate(Timestamp.from(Instant.now()))
                .lastModifiedDate(Timestamp.from(Instant.now()))
                .build();

        return new ResponseEntity<>(beer1, HttpStatus.OK);
    }

    @PostMapping("beer")
    public ResponseEntity<Beer> saveNewBeer(@RequestBody Beer beer) {
        Beer beer1 = Beer.builder()
                .beerName("Cruzcampo")
                .beerStyle("UTS")
                .quantityToBrew(12)
                .price(BigDecimal.valueOf(12.1))
                .createDate(Timestamp.from(Instant.now()))
                .lastModifiedDate(Timestamp.from(Instant.now()))
                .build();

        return new ResponseEntity<Beer>(beer1, HttpStatus.CREATED);
    }

    @PutMapping("beer/{beerId}")
    public ResponseEntity updateBeer(@PathVariable UUID beerId, @RequestBody Beer beer) {
        Beer beer1 = Beer.builder()
                .beerName("Cruzcampo")
                .beerStyle("UTS")
                .quantityToBrew(12)
                .price(BigDecimal.valueOf(12.1))
                .createDate(Timestamp.from(Instant.now()))
                .lastModifiedDate(Timestamp.from(Instant.now()))
                .build();

        return new ResponseEntity<Beer>(beer1, HttpStatus.NO_CONTENT);
    }


}
