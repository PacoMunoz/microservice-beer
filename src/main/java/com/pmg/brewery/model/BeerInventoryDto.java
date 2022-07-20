package com.pmg.brewery.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.UUID;

public class BeerInventoryDto implements Serializable {

    private static final long serialVersionUID = 4578399603011178803L;

    @Null
    private UUID id = null;

    @Null
    private Long version = null;

    @Null
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ", shape = JsonFormat.Shape.STRING)
    private OffsetDateTime createdDate;

    @Null
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ", shape = JsonFormat.Shape.STRING)
    private OffsetDateTime lastModifiedDate;

    @NotBlank
    private UUID beerId;
    @NotBlank
    private String upc;
    @Positive
    private Integer quantityOnHand;
}
