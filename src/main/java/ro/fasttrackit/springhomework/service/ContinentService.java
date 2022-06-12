package ro.fasttrackit.springhomework.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ro.fasttrackit.springhomework.model.Country;
import ro.fasttrackit.springhomework.repository.ContinentRepository;

import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class ContinentService {

    private final ContinentRepository continentRepository;

    public List<Country> getAllCountriesByContinent(String continentName, Integer minPopulation) {
        return continentRepository.getAllCountriesByContinent(continentName, minPopulation);
    }

    public Map<String, List<Country>> getCountriesGroupedByContinent() {
        return continentRepository.getCountriesGroupedByContinent();
    }
}
