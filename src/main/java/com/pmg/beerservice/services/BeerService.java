package com.pmg.beerservice.services;

import com.pmg.beerservice.web.model.BeerDto;

import java.util.List;
import java.util.UUID;

public interface BeerService {

    List<BeerDto> getAllBeers();
    BeerDto getBeerById(UUID beerId);
    BeerDto getBeerByUPC(String upc);
    BeerDto createBeer(BeerDto newBeer);
    BeerDto updateBeer(UUID beerId, BeerDto beer);
}
