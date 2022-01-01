package com.pmg.beerservice.model;

import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Beer {

    private UUID id;

    private Long version;

    private Timestamp createDate;

    private Timestamp lastModifiedDate;

    private String beerName;

    private String beerStyle;

    private String upc;

    private BigDecimal price;

    private Integer minOnHand;

    private Integer quantityToBrew;
}
