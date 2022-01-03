package com.pmg.beerservice.services;

import com.pmg.beerservice.domain.Beer;

import java.util.List;
import java.util.UUID;

public interface BeerService {

    List<Beer> getAllBeers();
    Beer getBeerById(UUID beerId);
    Beer getBeerByUPC(String upc);
    Beer createBeer(Beer newBeer);
    Beer updateBeer(UUID beerId, Beer beer);
}
