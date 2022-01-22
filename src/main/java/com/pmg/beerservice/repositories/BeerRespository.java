package com.pmg.beerservice.repositories;

import com.pmg.beerservice.domain.Beer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;


public interface BeerRespository extends JpaRepository<Beer, UUID> {
    Optional<Beer> findByUpc(String upc);
}
