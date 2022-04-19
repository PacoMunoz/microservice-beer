package com.pmg.beerservice.services.inventory;

import com.pmg.beerservice.services.inventory.model.BeerInventoryDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;


@Component
@Profile("propertyMode1")
public class BeerInventoryServiceRestTemplateImpl implements BeerInventoryService {

    // private static final String INVENTORY_SERVICE_URL = "http://localhost:8081/api/v1/beer/";//  {beerId}/inventory";
    private final String inventoryServiceURL;

    private final RestTemplate restTemplate;

    public BeerInventoryServiceRestTemplateImpl(RestTemplateBuilder restTemplateBuilder,
                                                @Value("${beer.service.inventoryserviceurl}") String inventoryServiceURL) {
        this.inventoryServiceURL = inventoryServiceURL;
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public Integer getOnHandInventory(UUID beerId) {

        BeerInventoryDto beerInventory = restTemplate.getForObject(inventoryServiceURL + beerId.toString() + "/inventory"
                , BeerInventoryDto.class);

        return beerInventory.getQuantityOnHand();
    }
}
