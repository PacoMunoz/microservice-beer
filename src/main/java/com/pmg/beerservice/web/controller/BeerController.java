package com.pmg.beerservice.web.controller;

import com.pmg.beerservice.services.BeerService;
import com.pmg.beerservice.web.model.BeerDto;
import com.pmg.beerservice.web.model.BeerPagedList;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/v1")
public class BeerController {

    private static final Integer DEFAULT_PAGE_NUMBER = 0;
    private static final Integer DEFAULT_PAGE_SIZE = 25;
    private static final String DEFAULT_SORT_BY = "id";

    private BeerService beerService;

    @GetMapping("/beer")
    public ResponseEntity<BeerPagedList> listBeers(@RequestParam(value = "showInventoryOnHand", required = false) boolean showInventoryOnHand,
                                                   @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                                   @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                                   @RequestParam(value = "sortBy", required = false) String sortBy) {
        log.debug("Getting all beers.");

        if (pageNumber == null) {
            pageNumber = DEFAULT_PAGE_NUMBER;
        }
        if (pageSize == null) {
            pageSize = DEFAULT_PAGE_SIZE;
        }
        if (sortBy == null) {
            sortBy = DEFAULT_SORT_BY;
        }

        return new ResponseEntity<>(beerService.getAllBeers(showInventoryOnHand, PageRequest.of(pageNumber, pageSize, Sort.by(sortBy))), HttpStatus.OK);
    }

    @GetMapping("beer/{beerId}")
    public ResponseEntity<BeerDto> getBeerById(@PathVariable UUID beerId,
                                               @RequestParam(value = "showInventoryOnHand", required = false) boolean showInventoryOnHand) {
        log.debug("Get beer by beerId : " + beerId);
        return new ResponseEntity<>(beerService.getBeerById(beerId, showInventoryOnHand), HttpStatus.OK);
    }

    @GetMapping("beerUPC/{upc}")
    public ResponseEntity<BeerDto> getBeerByUPC(@PathVariable String upc) {
        log.debug("Get beer by upc: " + upc);
        return new ResponseEntity<BeerDto>(beerService.getBeerByUPC(upc), HttpStatus.OK);
    }

    @PostMapping("beer")
    public ResponseEntity<BeerDto> saveNewBeer(@RequestBody BeerDto beer) {
        log.debug("Save new beer: " + beer.toString());
        return new ResponseEntity<BeerDto>(beerService.createBeer(beer), HttpStatus.CREATED);
    }

    @PutMapping("beer/{beerId}")
    public ResponseEntity updateBeer(@PathVariable UUID beerId, @RequestBody BeerDto beer) {
        log.debug("Update beer: " + beerId + " with values: " + beer.toString());
        return new ResponseEntity<BeerDto>(beerService.updateBeer(beerId, beer), HttpStatus.NO_CONTENT);
    }

}
