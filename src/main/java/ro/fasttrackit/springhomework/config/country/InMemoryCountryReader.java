package ro.fasttrackit.springhomework.config.country;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import ro.fasttrackit.springhomework.model.Country;

import java.util.List;

@Slf4j
@Component
@Profile({"in-memory", "!file"})
@AllArgsConstructor
public class InMemoryCountryReader implements CountryReader {
    @Override
    public List<Country> readCountries() {
        return List.of(
                Country.builder()
                        .id("1")
                        .name("Romania")
                        .capital("Bucharest")
                        .population(1_000_000L)
                        .area(1_000_000D)
                        .neighbors(List.of("HUN", "BUL", "SER", "MOL", "UKR"))
                        .build(),
                Country.builder()
                        .id("2")
                        .name("Germany")
                        .capital("Berlin")
                        .population(1_000_000L)
                        .area(1_000_000D)
                        .neighbors(List.of("BEL", "LUX", "FRA", "NED", "POL", "CZE"))
                        .build(),
                Country.builder()
                        .id("3")
                        .name("France")
                        .capital("Paris")
                        .population(1_000_000L)
                        .area(1_000_000D)
                        .neighbors(List.of("BEL", "LUX", "GER", "NED", "SPA", "ITA", "SWE"))
                        .build()
        );
    }
}
