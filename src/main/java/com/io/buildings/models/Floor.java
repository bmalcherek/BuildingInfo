package com.io.buildings.models;

import lombok.*;

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
    @JoinColumn(name = "room_id")
    private List<Room> rooms;

    public Floor(String name, List<Room> rooms) {
        super(name);
        this.rooms = rooms;
    }

    @Override
    public Float countSurface(){
        return rooms.stream().map(Localization::countSurface).reduce(0f,Float::sum);
    }
}
