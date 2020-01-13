package com.io.buildings.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * model on which based is database table. Extends Localization
 */
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
    /**
     * Method for printing objects of this class
     * @return String
     */
    public String toString(){
        return "name:"+super.getName()+" area:"+area+" cube:"+cube+" heating:"+heating+" light:"+light+" ";
    }
    /**
     * Method for comparison objects of this class
     * @param obj Object: object for comparison
     * @return boolean
     */
    public boolean equals(Object obj) {
        return super.getName() == ((Room)obj).getName() && this.area == ((Room)obj).getArea() && this.cube == ((Room)obj).getCube() &&
                this.heating == ((Room)obj).getHeating() && this.light == ((Room)obj).getLight();
    }
    /**
     * returns surface of room
     * @return surface
     */
    @Override
    public Float countSurface() {
        return area;
    }
    /**
     * returns cube of room
     * @return cube
     */
    @Override
    public Float countCube() {
        return cube;
    }
    /**
     * returns average light of room
     * @return average light
     */
    @Override
    public Float countAverageLight() {
        return light/area;
    }
    /**
     * returns average heating of room
     * @return average heating
     */
    @Override
    public Float countAverageHeating(){return heating/area;}
}
