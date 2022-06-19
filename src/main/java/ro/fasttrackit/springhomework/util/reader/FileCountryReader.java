package ro.fasttrackit.springhomework.util.reader;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ro.fasttrackit.springhomework.config.country.CountryConfiguration;
import ro.fasttrackit.springhomework.exception.MissingFileException;
import ro.fasttrackit.springhomework.model.Country;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
public class FileCountryReader implements CountryReader {
    private final CountryConfiguration countryConfiguration;

    @Override
    public List<Country> readCountries() {
        if (countryConfiguration.getFilePath() == null || countryConfiguration.getFilePath().isEmpty()) {
            log.error("No file path configured");
            return new ArrayList<>();
        }
        var path = Path.of(countryConfiguration.getFilePath());
        if (!Files.exists(path)) {
            log.error("File does not exist");
            return new ArrayList<>();
        }
        log.debug("Reading countries from file");
        log.debug("Path: {}", path);

        List<Country> countries;
        try {
            countries = readCountriesFile(path);
        } catch (IOException e) {
            throw new MissingFileException("File not found or not readable " + path);
        }
        log.debug("Countries: {}", countries);
        return countries;
    }

    private List<Country> readCountriesFile(Path path) throws IOException {
        return Files.readAllLines(path).stream()
                .map(this::parseCountry)
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    private Country parseCountry(String line) {
        List<String> neighbors = line.split("\\|").length == 6 ? List.of(line.split("\\|")[5].split("~")) : new ArrayList<>();
        return Country.builder()
                .id(UUID.randomUUID().toString())
                .name(line.split("\\|")[0])
                .capital(line.split("\\|")[1])
                .population(Long.parseLong(line.split("\\|")[2]))
                .area(Double.parseDouble(line.split("\\|")[3]))
                .continent(line.split("\\|")[4])
                .neighbors(neighbors)
                .build();
    }
}
