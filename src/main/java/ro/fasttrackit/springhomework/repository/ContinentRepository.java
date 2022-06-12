package ro.fasttrackit.springhomework.repository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ro.fasttrackit.springhomework.model.Country;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

@Slf4j
@Repository
@AllArgsConstructor
public class ContinentRepository {

    private final List<Country> countries;

    public List<Country> getAllCountriesByContinent(String continentName, Integer minPopulation) {
        List<Country> countries = this.countries.stream()
                .filter(country -> {
                    boolean filterCondition = country.getContinent().equals(continentName);
                    if (minPopulation != null) {
                        filterCondition = filterCondition && country.getPopulation() >= minPopulation;
                    }
                    return filterCondition;
                })
                .toList();
        log.info("Found {} countries in continent {}", countries.size(), continentName);
        return countries;
    }

    public Map<String, List<Country>> getCountriesGroupedByContinent() {
        List<String> continents = this.countries.stream()
                .map(Country::getContinent)
                .distinct()
                .toList();
        return continents.stream()
                .collect(
                        toMap(
                                String::trim,
                                continent -> countries.stream()
                                        .filter(country -> country.getContinent().equals(continent))
                                        .toList()
                        )
                );

    }
}



