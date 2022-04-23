package com.pmg.beerservice.services;

import com.pmg.beerservice.domain.Beer;
import com.pmg.beerservice.repositories.BeerRespository;
import com.pmg.beerservice.web.controller.NotFoundException;
import com.pmg.beerservice.web.mappers.BeerMapper;
import com.pmg.beerservice.web.model.BeerDto;
import com.pmg.beerservice.web.model.BeerPagedList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.UUID;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Slf4j
@Service
public class BeerServiceImpl implements BeerService {

    private final BeerMapper beerMapper;

    private final BeerRespository beerRespository;

    @Override
    @Cacheable(
            value = "allBeerCache",
            condition = "!#showInventoryOnHand"
    )
    public BeerPagedList getAllBeers(boolean showInventoryOnHand, String beerName, String beerStyle, Integer pageNumber, Integer pageSize) {
        log.debug("Service getAllBeers with showInventoryOnHand = " + showInventoryOnHand );
        Page<Beer> beers;

        if (!ObjectUtils.isEmpty(beerName) && !ObjectUtils.isEmpty(beerStyle)) {
            beers = beerRespository.findAllByBeerNameAndAndBeerStyle(beerName, beerStyle, PageRequest.of(pageNumber, pageSize));
        } else if (!ObjectUtils.isEmpty(beerName)) {
            beers = beerRespository.findAllByBeerName(beerName, PageRequest.of(pageNumber, pageSize));
        } else if (!ObjectUtils.isEmpty(beerStyle)) {
            beers = beerRespository.findAllByBeerStyle(beerStyle, PageRequest.of(pageNumber, pageSize));
        } else {
            beers = beerRespository.findAll(PageRequest.of(pageNumber, pageSize));
        }

        if (showInventoryOnHand) {
            return new BeerPagedList(
                    beers
                        .getContent()
                        .stream()
                        .map(beerMapper::beerToBeerDtoWithQuantity)
                        .collect(Collectors.toList()),
                    PageRequest.of(pageNumber, pageSize),
                    beers.getTotalElements());
        } else {
            return new BeerPagedList(
                    beers
                        .getContent()
                        .stream()
                        .map(beerMapper::beerToBeerDto)
                        .collect(Collectors.toList()),
                    PageRequest.of(pageNumber, pageSize),
                    beers.getTotalElements());
        }

    }

    @Override
    @Cacheable(
            value = "beerByIdCache",
            key = "#beerId",
            condition = "!#showInventoryOnHand"
    )
    public BeerDto getBeerById(UUID beerId, boolean showInventoryOnHand) {
        log.debug("Service getBeerById with showInventoryOnHand = " + showInventoryOnHand);
        Beer beer = beerRespository.findById(beerId).orElseThrow(NotFoundException::new);
        if (showInventoryOnHand) {
            return beer != null ? beerMapper.beerToBeerDtoWithQuantity(beer) : null;
        } else {
            return beer != null ? beerMapper.beerToBeerDto(beer) : null;
        }
    }

    @Override
    public BeerDto getBeerByUPC(String upc) {
        log.debug("Service getBeerByUPC.");
        Beer beer = beerRespository.findBeerByUpc(upc).orElseThrow(NotFoundException::new);
        return beer != null ? beerMapper.beerToBeerDto(beer) : null;
    }

    @Override
    public BeerDto createBeer(BeerDto newBeer) {
        log.debug("Service Create Beer.");
        return beerMapper.beerToBeerDto(beerRespository.save(beerMapper.beerDtoToBeer(newBeer)));
    }

    @Override
    public BeerDto updateBeer(UUID beerId, BeerDto beer) {
        log.debug("Service Update Beer.");
        Beer targetBeer =  beerRespository.findById(beerId).orElseThrow(NotFoundException::new);
        targetBeer.setBeerName(beer.getBeerName());
        targetBeer.setBeerStyle(beer.getBeerStyle());
        targetBeer.setPrice(beer.getPrice());
        targetBeer.setUpc(beer.getUpc());
        return beerMapper.beerToBeerDto(beerRespository.save(targetBeer));
    }
}
