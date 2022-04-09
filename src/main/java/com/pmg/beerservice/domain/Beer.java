package com.pmg.beerservice.domain;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Beer {

    @Id
    /*@GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )*/
    @Type(type="org.hibernate.type.UUIDCharType")
    @Column(length = 36, columnDefinition = "varchar", updatable = false, nullable = false )
    private UUID id;

    private Long version;

    private Timestamp createdDate;

    private Timestamp lastModifiedDate;

    private String beerName;

    private String beerStyle;

    private String upc;

    private BigDecimal price;

    private Integer minOnHand;

    private Integer quantityToBrew;
}
