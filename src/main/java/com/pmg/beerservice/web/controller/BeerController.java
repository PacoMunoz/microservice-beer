package com.pmg.beerservice.web.controller;

import com.pmg.beerservice.services.BeerService;
import com.pmg.beerservice.web.model.BeerDto;
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
    public ResponseEntity<List<BeerDto>> listBeers() {
        return new ResponseEntity<List<BeerDto>>(beerService.getAllBeers(), HttpStatus.OK);
    }

    @GetMapping("beer/{beerId}")
    public ResponseEntity<BeerDto> getBeerById(@PathVariable UUID beerId) {
        return new ResponseEntity<>(beerService.getBeerById(beerId), HttpStatus.OK);
    }

    @GetMapping("beerUPC/{upc}")
    public ResponseEntity<BeerDto> getBeerByUPC(@PathVariable String upc) {
        return new ResponseEntity<BeerDto>(beerService.getBeerByUPC("123"), HttpStatus.OK);
    }

    @PostMapping("beer")
    public ResponseEntity<BeerDto> saveNewBeer(@RequestBody BeerDto beer) {
        return new ResponseEntity<BeerDto>(beerService.createBeer(beer), HttpStatus.CREATED);
    }

    @PutMapping("beer/{beerId}")
    public ResponseEntity updateBeer(@PathVariable UUID beerId, @RequestBody BeerDto beer) {
        return new ResponseEntity<BeerDto>(beerService.updateBeer(beerId, beer), HttpStatus.NO_CONTENT);
    }

}
