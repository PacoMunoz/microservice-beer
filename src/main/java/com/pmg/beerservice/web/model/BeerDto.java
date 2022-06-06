package com.pmg.beerservice.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BeerDto implements Serializable {

    private static final long serialVersionUID = 8554261096628967088L;

    // @Null -> the value at first must be null
    // mapStruct set the value after.
    @Null
    private UUID id;

    @Null
    private Integer version;

    @Null
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ssZ", shape=JsonFormat.Shape.STRING)
    private OffsetDateTime createdDate;

    @Null
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ssZ", shape=JsonFormat.Shape.STRING)
    private OffsetDateTime lastModifiedDate;

    @NotBlank
    @JsonSerialize(using = UpperCaseSerializer.class)
    private String beerName;

    @NotNull
    private String beerStyle;

    @NotNull
    private String upc;

    // indica el tipo de dato que ira en el json, en este caso el precio sera un String en el Json
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @Positive
    @NotNull
    private BigDecimal price;

    private Integer quantityOnHand;

    // al igual que @JsonFormat, @JsonSerialize sirve para dar formato a un atributo al convertirlo en Json,
    // en este caso usamos la implementación de la clase JsonSerializer, LocalDateSerializer para formatearlo
    /*@JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate localDate;*/
}
