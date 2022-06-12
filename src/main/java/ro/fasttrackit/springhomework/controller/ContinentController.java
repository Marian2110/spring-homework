package ro.fasttrackit.springhomework.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.fasttrackit.springhomework.model.Country;
import ro.fasttrackit.springhomework.service.ContinentService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/continents")
@AllArgsConstructor
public class ContinentController {
    private final ContinentService continentService;

    @GetMapping("/{continentName}/countries")
    public List<Country> getAllCountriesByContinent(@PathVariable String continentName,
                                                    @RequestParam(required = false) Integer minPopulation) {
        return continentService.getAllCountriesByContinent(continentName, minPopulation);
    }

    @GetMapping("/countries")
    public Map<String, List<Country>> getCountriesGroupedByContinent() {
        return continentService.getCountriesGroupedByContinent();
    }
}

