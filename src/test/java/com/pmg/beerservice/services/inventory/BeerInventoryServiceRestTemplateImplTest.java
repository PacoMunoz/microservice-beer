package com.pmg.beerservice.services.inventory;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
class BeerInventoryServiceRestTemplateImplTest {

    @Autowired
    private BeerInventoryService beerInventoryService;

    @Test
    void getOnHandInventory() {
        Integer res = beerInventoryService.getOnHandInventory(UUID.fromString("0a818933-087d-47f2-ad83-2f986ed087eb"));
        Assertions.assertNotNull(res);
    }
}
