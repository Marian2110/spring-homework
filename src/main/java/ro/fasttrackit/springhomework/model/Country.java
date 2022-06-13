package ro.fasttrackit.springhomework.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
@Builder
@Getter
@ToString
@EqualsAndHashCode
public class Country {
    private String id;
    private String name;
    private String capital;
    private Long population;
    private Double area;
    private String continent;
    private List<String> neighbors;
}
