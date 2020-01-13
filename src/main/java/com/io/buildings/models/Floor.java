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
public class Floor extends Localization {

    /**
     * List of rooms in floor
     */
    @OneToMany
    @JoinColumn(name = "floor_id")
    private List<Room> rooms;

    public Floor(String name, List<Room> rooms) {
        super(name);
        this.rooms = rooms;
    }

    /**
     * counts surface on floor : summary of rooms surface
     * @return surface
     */
    @Override
    public Float countSurface() {
        return rooms.stream().map(Localization::countSurface).reduce(0f, Float::sum);
    }
    /**
     * counts cube on floor : summary of rooms cube
     * @return cube
     */
    @Override
    public Float countCube() {
        return rooms.stream().map(Localization::countCube).reduce(0f, Float::sum);
    }
    /**
     * counts average light on floor : summary of rooms average light
     * @return average light
     */
    @Override
    public Float countAverageLight() {
        return (float) rooms.stream().map(Localization::countAverageLight).mapToDouble(Double::valueOf).average().orElse(0);
    }
    /**
     * counts average heating on floor : summary of rooms average heating
     * @return average heating
     */
    @Override
    public Float countAverageHeating(){
        return (float) rooms.stream().map(Localization::countAverageHeating).mapToDouble(Double::valueOf).average().orElse(0);
    }
}
