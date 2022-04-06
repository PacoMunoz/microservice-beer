package com.pmg.beerservice.repositories;

import com.pmg.beerservice.domain.Beer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;
import java.util.UUID;


public interface BeerRespository extends PagingAndSortingRepository<Beer, UUID> {
    Optional<Beer> findByUpc(String upc);
}
