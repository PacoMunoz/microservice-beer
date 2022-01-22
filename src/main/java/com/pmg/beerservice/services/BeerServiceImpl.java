package com.pmg.beerservice.services;

import com.pmg.beerservice.domain.Beer;
import com.pmg.beerservice.repositories.BeerRespository;
import com.pmg.beerservice.web.mappers.BeerMapper;
import com.pmg.beerservice.web.model.BeerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class BeerServiceImpl implements BeerService {

    private final BeerMapper beerMapper;

    private final BeerRespository beerRespository;

    @Override
    public List<BeerDto> getAllBeers() {
        List<BeerDto> beerDtos = new ArrayList<>();
        beerRespository.findAll().forEach(beer -> {
            beerDtos.add(beerMapper.beerToBeerDto(beer));
        } );

        return beerDtos;
    }

    @Override
    public BeerDto getBeerById(UUID beerId) {
        Beer beer = beerRespository.findById(beerId).orElse(null);
        //return beer != null ? beerMapper.beerToBeerDto(beer) : null;
        return beer != null ? beerMapper.beerToBeerDtoWithQuantity(beer) : null;
    }

    @Override
    public BeerDto getBeerByUPC(String upc) {
        Beer beer = beerRespository.findByUpc(upc).orElse(null);
        return beer != null ? beerMapper.beerToBeerDto(beer) : null;
    }

    @Override
    public BeerDto createBeer(BeerDto newBeer) {
        return beerMapper.beerToBeerDto(beerRespository.save(beerMapper.beerDtoToBeer(newBeer)));
    }

    @Override
    public BeerDto updateBeer(UUID beerId, BeerDto beer) {
        Beer targetBeer =  beerRespository.findById(beerId).orElseThrow(RuntimeException::new);
        targetBeer.setBeerName(beer.getBeerName());
        targetBeer.setBeerStyle(beer.getBeerStyle());
        targetBeer.setPrice(beer.getPrice());
        targetBeer.setUpc(beer.getUpc());

        return beerMapper.beerToBeerDto(beerRespository.save(targetBeer));
    }
}
