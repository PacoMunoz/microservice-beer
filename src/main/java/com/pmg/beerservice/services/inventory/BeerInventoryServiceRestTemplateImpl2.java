package com.pmg.beerservice.services.inventory;

import com.pmg.brewery.model.BeerInventoryDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Setter
@Getter
@Component
@ConfigurationProperties(value = "beer.service", ignoreUnknownFields = false)
@Profile("propertyMode2") // este perfil muestra como obtener el valor del property beer.service.inventoryderviceurl
                          // con la anotacion @ConfigurationProperties
public class BeerInventoryServiceRestTemplateImpl2 implements BeerInventoryService {

    private final RestTemplate restTemplate;
    private String inventoryserviceurl;

    public BeerInventoryServiceRestTemplateImpl2(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public Integer getOnHandInventory(UUID beerId) {
        ResponseEntity<BeerInventoryDto> beerInventoryDtoResponseEntity = restTemplate.
                getForEntity(inventoryserviceurl + beerId.toString() + "/inventory", BeerInventoryDto.class);

        return  beerInventoryDtoResponseEntity.getBody() != null
                ? beerInventoryDtoResponseEntity.getBody().getQuantityOnHand()
                : null;
    }
}
