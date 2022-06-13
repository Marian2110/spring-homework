package ro.fasttrackit.springhomework.config.country;

import ro.fasttrackit.springhomework.model.Country;

import java.util.List;

public interface CountryReader {
    List<Country> readCountries();
}
