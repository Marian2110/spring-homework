package ro.fasttrackit.springhomework.config;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;
import ro.fasttrackit.springhomework.config.country.CountryReader;
import ro.fasttrackit.springhomework.model.Country;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Configuration
@AllArgsConstructor
public class BaseConfiguration {
    private final CountryReader countryReader;

    @Bean
    public List<Country> readCountries() {
        return countryReader.readCountries();
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
