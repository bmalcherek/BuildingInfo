package com.io.buildings.models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;

class FloorUnitTest {

    private List<Room> rooms = new ArrayList<>();
    private List<Room> emptyRooms = new ArrayList<>();

    private Floor floor;
    private Floor emptyFloor;

    @BeforeEach
    void setUp() {
        for (int i = 0; i < 4; i++) {
            rooms.add(new Room("Room" + String.valueOf(i),200f, 200f, 100f*(i+1), 400f));
        }
        floor = new Floor("Floor1",rooms);
        emptyFloor = new Floor("Floor2",emptyRooms);
    }

    @Test
    void testCountEmptyFloorSurface() {assertEquals(0,emptyFloor.countSurface());}

    @Test
    void testCountEmptyFloorCube() {assertEquals(0,emptyFloor.countCube()); }

    @Test
    void testCountEmptyFloorAverageLight() {assertEquals(0,emptyFloor.countAverageLight()); }

    @Test
    void testCountEmptyFloorAverageHeating() {assertEquals(0,emptyFloor.countAverageHeating());}

    @Test
    void shouldCorrectlyCountFloorSurface() {
        assertEquals(800f, floor.countSurface());
    }

    @Test
    void shouldCorrectlyCountFloorCube() {
        assertEquals(800f, floor.countCube());
    }

    @Test
    void shouldCorrectlyCountFloorLight() {
        assertEquals(2f, floor.countAverageLight());
    }

    @Test
    void shouldCorrectlyCountFloorHeating(){assertEquals(1.25f,floor.countAverageHeating());}
}