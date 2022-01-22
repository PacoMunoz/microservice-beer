package com.pmg.beerservice.services.inventory;

import com.pmg.beerservice.services.inventory.model.BeerInventoryDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;


@Component
public class BeerInventoryServiceRestTemplateImpl implements BeerInventoryService {

    private static final String INVENTORY_SERVICE_URL = "http://localhost:8081/api/v1/beer/";//  {beerId}/inventory";

    private final RestTemplate restTemplate;

    public BeerInventoryServiceRestTemplateImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public Integer getOnHandInventory(UUID beerId) {

        BeerInventoryDto beerInventory = restTemplate.getForObject(INVENTORY_SERVICE_URL + beerId.toString() + "/inventory"
                , BeerInventoryDto.class);

        return beerInventory.getQuantityOnHand();
    }
}
