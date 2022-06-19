package ro.fasttrackit.springhomework.repository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ro.fasttrackit.springhomework.model.Country;
import ro.fasttrackit.springhomework.util.reader.CountryReader;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Repository
@AllArgsConstructor
public class ContinentRepository {
    private final CountryReader countryReader;

    public List<Country> getAllCountriesByContinent(String continentName, Integer minPopulation) {
        return readCountries().stream()
                .filter(country -> country.getContinent().equals(continentName))
                .filter(country -> minPopulation == null || country.getPopulation() >= minPopulation)
                .toList();
    }

    public Map<String, List<Country>> getCountriesGroupedByContinent() {
        return readCountries().stream()
                .collect(Collectors.groupingBy(Country::getContinent));
    }

    private List<Country> readCountries() {
        return countryReader.readCountries();
    }
}



