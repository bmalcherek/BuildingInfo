package com.io.buildings.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;


/**
 * model on which based is database table. Extends Localization
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
public class Building extends Localization {

    /**
     * List of floors in building
     */
    @OneToMany()
    @JoinColumn(name = "building_id")
    private List<Floor> floors;

    public Building(String name, List<Floor> floors) {
        super(name);
        this.floors = floors;
    }

    /**
     * counts surface on building : summary of floors surface
     * @return surface
     */
    @Override
    public Float countSurface() {
        return floors.stream().map(Localization::countSurface).reduce(0f, Float::sum);
    }

    /**
     * counts cube on building : summary of floors cube
     * @return cube
     */
    @Override
    public Float countCube() {
        return floors.stream().map(Localization::countCube).reduce(0f, Float::sum);
    }

    @Override
    public Float countAverageLight() {
        return (float) floors.stream().map(Localization::countAverageLight).mapToDouble(Double::valueOf).average().orElse(0);
    }

}
