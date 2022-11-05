package com.pmg.beerservice.services.inventory;

import com.pmg.brewery.model.BeerInventoryDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;


@Component
@Profile("propertyMode1") // este profile muestra como obtener el property beer.service.inventoryderviceurl con la
                          // anotacion @Value en un parametro del metodo
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
        ResponseEntity<BeerInventoryDto> beerInventoryDtoResponseEntity = restTemplate.
                getForEntity(inventoryServiceURL + beerId.toString() + "/inventory", BeerInventoryDto.class);

        return  beerInventoryDtoResponseEntity.getBody() != null
                    ? beerInventoryDtoResponseEntity.getBody().getQuantityOnHand()
                    : null;
    }
}
