package com.pmg.beerservice.services.inventory.model;

import lombok.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BeerInventoryDto {
    private UUID id;
    private OffsetDateTime createDate;
    private OffsetDateTime lastModifiedDate;
    private UUID beerId;
    private String upc;
    private Integer quantityOnHand;
}
