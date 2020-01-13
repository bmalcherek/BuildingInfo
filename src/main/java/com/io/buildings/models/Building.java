package com.io.buildings.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.ArrayList;
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
    /**
     * counts average light on building: summary of floors average light
     * @return average light
     */
    @Override
    public Float countAverageLight() {
        return (float) floors.stream().map(Localization::countAverageLight).mapToDouble(Double::valueOf).average().orElse(0);
    }
    /**
     * counts average heating on building: summary of floors average heating
     * @return average heating
     */
    @Override
    public Float countAverageHeating(){
        return (float) floors.stream().map(Localization::countAverageHeating).mapToDouble(Double::valueOf).average().orElse(0);
    }
    /**
     * select rooms which heating is higher than given value
     * @param value float: limit of heating
     * @return list of rooms
     */
    public List<Room> getBuildingsAboveHeating(float value){
        List<Room> list = new ArrayList<Room>();
        for(Floor tempFloor: this.floors) {
            for (Room loc : tempFloor.getRooms()) {
                if (loc.getHeating() > value) {
                    list.add(loc);
                }
            }
        }
        return list;
    }
    /**
     * select rooms which surface is in the range of given values
     * @param leftValue float: left bound
     * @param rightValue float: right bound
     * @return list of rooms
     */
    public List<Room> getBuildingsBySurface(float leftValue,float rightValue){
        List<Room> list = new ArrayList<>();
        for(Floor f: this.floors){
            for(Room r: f.getRooms()) {
                if (r.getArea() >= leftValue && r.getArea() <= rightValue) {
                    list.add(r);
                }
            }
        }
        return list;
    }
}
