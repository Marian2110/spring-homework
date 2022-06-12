package ro.fasttrackit.springhomework.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.fasttrackit.springhomework.model.Country;
import ro.fasttrackit.springhomework.service.CountryService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/countries")
@AllArgsConstructor
public class CountryController {
    private final CountryService countryService;

    @GetMapping
    public List<Country> getAllCountries(@RequestParam(required = false) String includeNeighbour,
                                         @RequestParam(required = false) String excludeNeighbour) {
        return countryService.getAllCountries(includeNeighbour, excludeNeighbour);
    }

    @GetMapping("/names")
    public List<String> getAllCountryNames() {
        return countryService.getAllCountryNames();
    }

    @GetMapping("/{countryId}/capital")
    public String getCapital(@PathVariable String countryId) {
        return countryService.getCapital(countryId);
    }

    @GetMapping("/{countryId}/population")
    public Long getPopulation(@PathVariable String countryId) {
        return countryService.getPopulation(countryId);
    }

    @GetMapping("/{countryId}/neighbours")
    public List<String> getNeighbours(@PathVariable String countryId) {
        return countryService.getNeighbours(countryId);
    }

    @GetMapping("/population")
    public Map<String, Long> getCountriesPopulation() {
        return countryService.getCountriesPopulation();
    }

    @GetMapping("/mine")
    public Country getClientCountry() {
        return countryService.getClientCountry();
    }
}
