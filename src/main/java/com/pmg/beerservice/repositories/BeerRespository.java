package com.pmg.beerservice.repositories;

import com.pmg.beerservice.domain.Beer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;
import java.util.UUID;


public interface BeerRespository extends PagingAndSortingRepository<Beer, UUID> {
    Optional<Beer> findByUpc(String UPC);
    Page<Beer> findAllByBeerName(String beerName, Pageable pageable);
    Page<Beer> findAllByBeerStyle(String beerStyle, Pageable pageable);
    Page<Beer> findAllByBeerNameAndAndBeerStyle(String beerName, String beerStyle, Pageable pageable);
}
