package com.pmg.beerservice.web.controller;

import com.pmg.beerservice.domain.Beer;
import com.pmg.beerservice.services.BeerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class BeerController {

    private BeerService beerService;

    @GetMapping("/beer")
    public ResponseEntity<List<Beer>> listBeers() {
        return new ResponseEntity<>(beerService.getAllBeers(), HttpStatus.OK);
    }

    @GetMapping("beer/{beerId}")
    public ResponseEntity<Beer> getBeerById(@PathVariable UUID beerId) {
        return new ResponseEntity<>(beerService.getBeerById(UUID.randomUUID()), HttpStatus.OK);
    }

    @GetMapping("beerUPC/{upc}")
    public ResponseEntity<Beer> getBeerByUPC(@PathVariable String upc) {
        return new ResponseEntity<>(beerService.getBeerByUPC("123"), HttpStatus.OK);
    }

    @PostMapping("beer")
    public ResponseEntity<Beer> saveNewBeer(@RequestBody Beer beer) {
        return new ResponseEntity<Beer>(beerService.createBeer(beer), HttpStatus.CREATED);
    }

    @PutMapping("beer/{beerId}")
    public ResponseEntity updateBeer(@PathVariable UUID beerId, @RequestBody Beer beer) {
        return new ResponseEntity<Beer>(beerService.updateBeer(beerId, beer), HttpStatus.NO_CONTENT);
    }

}
