package com.pmg.beerservice.web.mappers;

import com.pmg.beerservice.domain.Beer;
import com.pmg.beerservice.web.model.BeerDto;
import org.mapstruct.Mapper;

@Mapper
public interface BeerMapper {

    BeerDto beerToBeerDto(Beer beer);
    Beer beerDtoToBeer(BeerDto beerDto);
}
