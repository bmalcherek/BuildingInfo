package com.io.buildings.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.websocket.OnError;
import java.util.ArrayList;
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
   /**
     * select rooms which heating is higher than given value
     * @param value float: limit of heating
     * @return list of rooms
     */
  /*  public List<Room> getFloorsAboveHeating(float value){
        List<Room> list = new ArrayList<Room>();
        for(Room loc: this.rooms){
            if (loc.getHeating() > value) {
                list.add(loc);
            }
        }
        return list;
    }*/
    /**
     * select rooms which surface is in the range of given values
     * @param leftValue float: left bound
     * @param rightValue float: right bound
     * @return list of rooms
     */
   /* public List<Room> getFloorsBySurface(float leftValue,float rightValue){
        List<Room> list = new ArrayList<>();
        for(Room r: this.rooms) {
            if (r.getArea() >= leftValue && r.getArea() <= rightValue) {
                list.add(r);
            }
        }
        return list;
    }*/
}
