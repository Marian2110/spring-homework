package ro.fasttrackit.springhomework.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ro.fasttrackit.springhomework.model.Country;
import ro.fasttrackit.springhomework.repository.CountryRepository;

import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class CountryService {
    private final CountryRepository countryRepository;

    public List<Country> getAllCountries(String includeNeighbour, String excludeNeighbour) {
        return countryRepository.getAllCountries(includeNeighbour, excludeNeighbour);
    }

    public List<String> getAllCountryNames() {
        return countryRepository.getAllCountryNames();
    }

    public String getCapital(String countryId) {
        return countryRepository.getCapital(countryId);
    }

    public Long getPopulation(String countryId) {
        return countryRepository.getPopulation(countryId);
    }

    public List<String> getNeighbours(String countryId) {
        return countryRepository.getNeighbours(countryId);
    }

    public Map<String, Long> getCountriesPopulation() {
        return countryRepository.getCountriesPopulation();
    }

    public Country getClientCountry() {
        return countryRepository.getClientCountry();
    }
}


