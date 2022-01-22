package com.pmg.beerservice.web.mappers;

import com.pmg.beerservice.domain.Beer;
import com.pmg.beerservice.web.model.BeerDto;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
@DecoratedWith(BeerMapperDecorator.class)
public interface BeerMapper {

    BeerDto beerToBeerDto(Beer beer);
    Beer beerDtoToBeer(BeerDto beerDto);
    BeerDto beerToBeerDtoWithQuantity(Beer beer);
}
