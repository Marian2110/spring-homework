package ro.fasttrackit.springhomework.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties("country")
public class CountryConfiguration {
    private String filePath;
}
