package ro.fasttrackit.springhomework.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ro.fasttrackit.springhomework.exception.CustomEntityNotFoundException;
import ro.fasttrackit.springhomework.model.Country;
import ro.fasttrackit.springhomework.util.reader.CountryReader;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

@Repository
@AllArgsConstructor
public class CountryRepository {
    private final CountryReader countryReader;

    @Resource(name = "headers")
    private Map<String, String> headers;

    private boolean isIdEquals(String countryId, Country country) {
        return country.getId().equals(countryId);
    }

    public List<Country> getAllCountries(String includeNeighbour, String excludeNeighbour) {
        return readCountries().stream()
                .filter(country -> country.getNeighbors().contains(includeNeighbour))
                .filter(country -> !country.getNeighbors().contains(excludeNeighbour))
                .toList();
    }

    public List<String> getAllCountryNames() {
        return readCountries().stream()
                .map(Country::getName)
                .toList();
    }

    public String getCapital(String countryId) {
        return readCountries().stream()
                .filter(country -> isIdEquals(countryId, country))
                .findFirst()
                .map(Country::getCapital)
                .orElseThrow(() -> new CustomEntityNotFoundException(Country.class.getName(), countryId));
    }

    public Long getPopulation(String countryId) {
        return readCountries().stream()
                .filter(country -> isIdEquals(countryId, country))
                .findFirst()
                .map(Country::getPopulation)
                .orElseThrow(() -> new CustomEntityNotFoundException(Country.class.getName(), countryId));
    }

    public List<String> getNeighbours(String countryId) {
        return readCountries().stream()
                .filter(country -> isIdEquals(countryId, country))
                .findFirst()
                .map(Country::getNeighbors)
                .orElseThrow(() -> new CustomEntityNotFoundException(Country.class.getName(), countryId));
    }

    public Map<String, Long> getCountriesPopulation() {
        return readCountries().stream()
                .collect(
                        toMap(
                                Country::getName,
                                Country::getPopulation
                        )
                );
    }

    public Country getClientCountry() {
        String headerKey = "x-country";
        if (!headers.containsKey(headerKey)) {
            throw new IllegalArgumentException("X-Country header not found");
        }
        return readCountries().stream()
                .filter(country -> country.getName().equalsIgnoreCase(headers.get(headerKey)))
                .findFirst()
                .orElseThrow(() -> new CustomEntityNotFoundException(Country.class.getName(), headers.get(headerKey)));
    }

    private List<Country> readCountries() {
        return countryReader.readCountries();
    }
}
