package com.io.buildings.models;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Room extends Localization {

    @Column(nullable = false)
    private Float area;

    @Column(nullable = false)
    private Float cube;

    @Column(nullable = false)
    private Float heating;

    @Column(nullable = false)
    private Float light;

    public Room(String name, Float area, Float cube, Float heating, Float light) {
        super(name);
        this.area = area;
        this.cube = cube;
        this.heating = heating;
        this.light = light;
    }

    @Override
    public Float countSurface() {
        return area;
    }

    @Override
    public Float countCube() {
        return cube;
    }

    @Override
    public Float countAverageLight() {
        return light/area;
    }
}
