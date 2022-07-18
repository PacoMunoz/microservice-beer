package com.pmg.beerservice.web.controller;

import com.pmg.beerservice.services.BeerService;
import es.pmg.brewery.model.BeerDto;
import es.pmg.brewery.model.BeerPagedList;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@AllArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/v1")
public class BeerController {

    private static final Integer PAGE_NUMBER = 0;
    private static final Integer PAGE_SIZE = 2;
    private BeerService beerService;

    @GetMapping("/beer")
    public ResponseEntity<BeerPagedList> listBeers(@RequestParam(value = "showInventoryOnHand", required = false)
                                                               boolean showInventoryOnHand,
                                                   @RequestParam(required = false) Integer pageNumber,
                                                   @RequestParam(required = false) Integer pageSize,
                                                   @RequestParam(required = false) String beerName,
                                                   @RequestParam(required = false) String beerStyle) {
        log.debug("Getting all beers.");

        if (pageNumber == null) {
            pageNumber = PAGE_NUMBER;
        }
        if (pageSize == null) {
            pageSize = PAGE_SIZE;
        }

        return new ResponseEntity<>(beerService.getAllBeers(showInventoryOnHand,
                beerName,
                beerStyle,
                pageNumber,
                pageSize),
                HttpStatus.OK);
    }

    @GetMapping("beer/{beerId}")
    public ResponseEntity<BeerDto> getBeerById(@PathVariable UUID beerId,
                                               @RequestParam(value = "showInventoryOnHand", required = false)
                                                       boolean showInventoryOnHand) {
        log.debug("Get beer by beerId : " + beerId);
        return new ResponseEntity<>(beerService.getBeerById(beerId, showInventoryOnHand), HttpStatus.OK);
    }

    @GetMapping("beerUPC/{upc}")
    public ResponseEntity<BeerDto> getBeerByUPC(@PathVariable String upc) {
        log.debug("Get beer by upc: " + upc);
        return new ResponseEntity<BeerDto>(beerService.getBeerByUPC(upc), HttpStatus.OK);
    }

    @PostMapping("beer")
    public ResponseEntity<BeerDto> saveNewBeer(@Valid @RequestBody BeerDto beer) {
        log.debug("Save new beer: " + beer.toString());
        return new ResponseEntity<>(beerService.createBeer(beer), HttpStatus.CREATED);
    }

    @PutMapping("beer/{beerId}")
    public ResponseEntity<BeerDto> updateBeer(@PathVariable UUID beerId, @Valid @RequestBody BeerDto beer) {
        log.debug("Update beer: " + beerId + " with values: " + beer.toString());
        return new ResponseEntity<>(beerService.updateBeer(beerId, beer), HttpStatus.OK);
    }

    // catch all exceptions of class type MethodArgumentNotValidException in the actual class
    // could be replace by one controller Advice class -> see MvcExceptionHandler.class
    /*@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List> validationErrorHandler(MethodArgumentNotValidException e){
        List<String> errors = new ArrayList<>(e.getErrorCount());

        e.getBindingResult().getFieldErrors().forEach(error -> {
            errors.add(error.getField() + " : " + error.getDefaultMessage());
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }*/

}
