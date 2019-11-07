package com.io.buildings.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;

class BuildingUnitTest {

    private List<Room> rooms = new ArrayList<>();

    private List<Floor> floors = new ArrayList<>();

    private Building sut;

    @BeforeEach
    void setUp() {
        for (int i = 0; i < 4; i++) {
            rooms.add(new Room("Room" + String.valueOf(i),100f, 100f, 100f, 100f));
        }
        for (int i = 0; i < 2; i++) {
            floors.add(new Floor("Floor" + String.valueOf(i), asList(rooms.get(2*i), rooms.get(2*i+1))));
        }
        sut = new Building("Building", floors);
    }

    @Test
    void shouldCorrectlyCountSurface() {
        assertEquals(400f, sut.countSurface());
    }

    @Test
    void countCube() {
        assertEquals(400f, sut.countCube());
    }

}