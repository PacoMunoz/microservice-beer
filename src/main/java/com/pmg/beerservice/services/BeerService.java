package com.pmg.beerservice.services;

import com.pmg.beerservice.web.model.BeerDto;
import com.pmg.beerservice.web.model.BeerPagedList;

import java.util.List;
import java.util.UUID;

public interface BeerService {

    BeerPagedList getAllBeers(boolean showInventoryOnHand, Integer pageNumber, Integer pageSize);
    BeerDto getBeerById(UUID beerId, boolean showInventoryOnHand);
    BeerDto getBeerByUPC(String upc);
    BeerDto createBeer(BeerDto newBeer);
    BeerDto updateBeer(UUID beerId, BeerDto beer);
}
