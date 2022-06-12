package ro.fasttrackit.springhomework.config;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;
import ro.fasttrackit.springhomework.model.Country;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

@Configuration
@AllArgsConstructor
@Slf4j
public class BaseConfiguration {

    private CountryConfiguration countryConfiguration;

    @Bean
    public List<Country> readCountriesFromFile() throws IOException {
        if (countryConfiguration.getFilePath() == null || countryConfiguration.getFilePath().isEmpty()) {
            log.error("No file path configured");
            return new ArrayList<>();
        }
        var path = Path.of(countryConfiguration.getFilePath());
        if (!Files.exists(path)) {
            log.error("File does not exist");
            return new ArrayList<>();
        }
        log.info("Reading countries from file");
        log.info("Path: {}", path);

        List<Country> countries = readCountriesFromPath(path);
        log.info("Countries: {}", countries);
        return countries;
    }

    private List<Country> readCountriesFromPath(Path path) throws IOException {
        return Files.readAllLines(path).stream()
                .map(this::parseCountry)
                .peek(country -> log.info("Country: {}", country))
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
                .neighbours(neighbors)
                .build();
    }

    @Bean(name = "headers")
    @RequestScope
    public Map<String, String> getHeadersInfo(HttpServletRequest request) {

        Map<String, String> map = new HashMap<>();

        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }
        log.info("Headers: {}", map);
        return map;
    }
}
