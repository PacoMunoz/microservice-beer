package com.pmg.beerservice.services;

import com.pmg.beerservice.web.model.BeerDto;
import com.pmg.beerservice.web.model.BeerPagedList;

import java.util.UUID;

public interface BeerService {

    BeerPagedList getAllBeers(boolean showInventoryOnHand, String beerName, String beerStyle, Integer pageNumber, Integer pageSize);
    BeerDto getBeerById(UUID beerId, boolean showInventoryOnHand);
    BeerDto getBeerByUPC(String upc);
    BeerDto createBeer(BeerDto newBeer);
    BeerDto updateBeer(UUID beerId, BeerDto beer);
}
