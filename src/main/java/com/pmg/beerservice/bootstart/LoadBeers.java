package com.pmg.beerservice.bootstart;

import com.pmg.beerservice.domain.Beer;
import com.pmg.beerservice.repositories.BeerRespository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;

@AllArgsConstructor
@Component
public class LoadBeers implements CommandLineRunner {

    public static final String BEER_1_UPC = "0631234200036";
    public static final String BEER_2_UPC = "0631234300019";
    public static final String BEER_3_UPC = "0083783375213";
    public static final UUID BEER_1_UUID = UUID.fromString("0a818933-087d-47f2-ad83-2f986ed087eb");
    public static final UUID BEER_2_UUID = UUID.fromString("a712d914-61ea-4623-8bd0-32c0f6545bfd");
    public static final UUID BEER_3_UUID = UUID.fromString("026cc3c8-3a0c-4083-a05b-e908048c1b08");

    private final BeerRespository beerRespository;

    @Override
    public void run(String... args) throws Exception {
       loadBeerData();
    }

    private void loadBeerData() {
        beerRespository.save(
            Beer.builder()
            .id(BEER_1_UUID)
            .beerName("Cruzcampo")
            .beerStyle("Floja")
            .minOnHand(12)
            .quantityToBrew(50)
            .upc(BEER_1_UPC)
            .build()
        );
        beerRespository.save(
                Beer.builder()
                        .id(BEER_2_UUID)
                        .beerName("San Miguel")
                        .beerStyle("Media")
                        .minOnHand(20)
                        .quantityToBrew(70)
                        .upc(BEER_2_UPC)
                        .build()
        );
        beerRespository.save(
                Beer.builder()
                        .id(BEER_3_UUID)
                        .beerName("Mahou")
                        .beerStyle("Fuerte")
                        .minOnHand(30)
                        .quantityToBrew(100)
                        .upc(BEER_3_UPC)
                        .build()
        );



    }
}
