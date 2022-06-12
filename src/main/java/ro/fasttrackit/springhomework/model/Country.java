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
    private final String id;
    private final String name;
    private final String capital;
    private final Long population;
    private final Double area;
    private final String continent;
    private final List<String> neighbours;
}
