package com.io.buildings.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Floor extends Localization {

    @OneToMany
    @JoinColumn(name = "floor_id")
    private List<Room> rooms;

    public Floor(String name, List<Room> rooms) {
        super(name);
        this.rooms = rooms;
    }

    @Override
    public Float countSurface() {
        return rooms.stream().map(Localization::countSurface).reduce(0f, Float::sum);
    }

    @Override
    public Float countCube() {
        return rooms.stream().map(Localization::countCube).reduce(0f, Float::sum);
    }

    @Override
    public Float countAverageLight() {
        return (float) rooms.stream().map(Localization::countAverageLight).mapToDouble(Double::valueOf).average().orElse(0);
    }
}
