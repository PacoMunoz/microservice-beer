package com.pmg.beerservice.services;

import com.pmg.beerservice.domain.Beer;
import com.pmg.beerservice.web.mappers.BeerMapper;
import com.pmg.beerservice.web.model.BeerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class BeerServiceImpl implements BeerService {

    private final BeerMapper beerMapper;

    @Override
    public List<Beer> getAllBeers() {
        Beer beer1 = Beer.builder()
                .id(UUID.randomUUID())
                .beerName("Cruzcampo")
                .beerStyle("UTS")
                .quantityToBrew(12)
                .price(BigDecimal.valueOf(12.1))
                .createDate(Timestamp.from(Instant.now()))
                .lastModifiedDate(Timestamp.from(Instant.now()))
                .build();
        Beer beer2 = Beer.builder()
                .id(UUID.randomUUID())
                .beerName("Estrella Galicia")
                .beerStyle("UTS")
                .quantityToBrew(12)
                .price(BigDecimal.valueOf(12.1))
                .createDate(Timestamp.from(Instant.now()))
                .lastModifiedDate(Timestamp.from(Instant.now()))
                .build();

        BeerDto beerDto = beerMapper.beerToBeerDto(beer1);

        return Arrays.asList(beer1, beer2);
    }

    @Override
    public Beer getBeerById(UUID beerId) {
        Beer beer1 = Beer.builder()
                .id(UUID.randomUUID())
                .beerName("Cruzcampo")
                .beerStyle("UTS")
                .quantityToBrew(12)
                .price(BigDecimal.valueOf(12.1))
                .createDate(Timestamp.from(Instant.now()))
                .lastModifiedDate(Timestamp.from(Instant.now()))
                .build();

        return beer1;
    }

    @Override
    public Beer getBeerByUPC(String upc) {
        Beer beer1 = Beer.builder()
                .id(UUID.randomUUID())
                .beerName("Cruzcampo")
                .beerStyle("UTS")
                .quantityToBrew(12)
                .price(BigDecimal.valueOf(12.1))
                .createDate(Timestamp.from(Instant.now()))
                .lastModifiedDate(Timestamp.from(Instant.now()))
                .build();
        return beer1;
    }

    @Override
    public Beer createBeer(Beer newBeer) {
        Beer beer1 = Beer.builder()
                .id(UUID.randomUUID())
                .beerName("Cruzcampo")
                .beerStyle("UTS")
                .quantityToBrew(12)
                .price(BigDecimal.valueOf(12.1))
                .createDate(Timestamp.from(Instant.now()))
                .lastModifiedDate(Timestamp.from(Instant.now()))
                .build();
        return beer1;
    }

    @Override
    public Beer updateBeer(UUID beerId, Beer beer) {
        Beer beer1 = Beer.builder()
                .id(UUID.randomUUID())
                .beerName("Cruzcampo")
                .beerStyle("UTS")
                .quantityToBrew(12)
                .price(BigDecimal.valueOf(12.1))
                .createDate(Timestamp.from(Instant.now()))
                .lastModifiedDate(Timestamp.from(Instant.now()))
                .build();
        return beer1;
    }
}
