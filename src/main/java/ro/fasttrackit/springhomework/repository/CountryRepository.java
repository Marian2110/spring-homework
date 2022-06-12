package ro.fasttrackit.springhomework.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ro.fasttrackit.springhomework.model.Country;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

@Repository
@AllArgsConstructor
public class CountryRepository {
    private final List<Country> countries;

    @Resource(name = "headers")
    private Map<String, String> headers;

    public List<Country> getAllCountries(String includeNeighbour, String excludeNeighbour) {
        return this.countries.stream()
                .filter(country -> {
                    boolean filterCondition = true;
                    if (includeNeighbour != null) {
                        filterCondition = country.getNeighbours().contains(includeNeighbour);
                    }
                    if (excludeNeighbour != null) {
                        filterCondition = filterCondition && !country.getNeighbours().contains(excludeNeighbour);
                    }
                    return filterCondition;
                })
                .toList();
    }

    public List<String> getAllCountryNames() {
        return countries.stream()
                .map(Country::getName)
                .toList();
    }

    public String getCapital(String countryId) {
        return countries.stream()
                .filter(country -> country.getId().equals(countryId))
                .findFirst()
                .map(Country::getCapital)
                .orElseThrow(() -> new IllegalArgumentException("Country not found"));
    }

    public Long getPopulation(String countryId) {
        return countries.stream()
                .filter(country -> country.getId().equals(countryId))
                .findFirst()
                .map(Country::getPopulation)
                .orElseThrow(() -> new IllegalArgumentException("Country not found"));
    }

    public List<String> getNeighbours(String countryId) {
        return countries.stream()
                .filter(country -> country.getId().equals(countryId))
                .findFirst()
                .map(Country::getNeighbours)
                .orElseThrow(() -> new IllegalArgumentException("Country not found"));
    }

    public Map<String, Long> getCountriesPopulation() {
        return countries.stream()
                .collect(
                        toMap(
                                Country::getName,
                                Country::getPopulation
                        )
                );
    }

    public Country getClientCountry() {
        if (!headers.containsKey("x-country")) {
            throw new IllegalArgumentException("X-Country header not found");
        }
        return countries.stream()
                .filter(country -> country.getName().equalsIgnoreCase(headers.get("x-country")))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Country not found"));
    }
}
