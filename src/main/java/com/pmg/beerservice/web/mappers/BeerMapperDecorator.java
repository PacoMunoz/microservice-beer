package com.pmg.beerservice.web.mappers;

import com.pmg.beerservice.domain.Beer;
import com.pmg.beerservice.services.inventory.BeerInventoryService;
import com.pmg.beerservice.web.model.BeerDto;
import org.springframework.beans.factory.annotation.Autowired;

public class BeerMapperDecorator implements BeerMapper{

    @Autowired
    private BeerInventoryService beerInventoryService;

    @Autowired
    private BeerMapper beerMapper;

    public BeerMapperDecorator(BeerMapper beerMapper) {
        this.beerMapper = beerMapper;
    }

    @Override
    public BeerDto beerToBeerDto(Beer beer) {
        return beerMapper.beerToBeerDto(beer);
    }

    @Override
    public Beer beerDtoToBeer(BeerDto beerDto) {
        return beerMapper.beerDtoToBeer(beerDto);
    }

    @Override
    public BeerDto beerToBeerDtoWithQuantity(Beer beer) {
        BeerDto beerDto = beerMapper.beerToBeerDto(beer);
        beerDto.setQuantityOnHand(beerInventoryService.getOnHandInventory(beerDto.getId()));
        return beerDto;
    }


}
