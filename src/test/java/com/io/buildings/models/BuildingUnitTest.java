package com.io.buildings.models;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;

class BuildingUnitTest {

    private List<Room> rooms = new ArrayList<>();

    private List<Floor> floors = new ArrayList<>();

    private List<Floor> floors2 = new ArrayList<>();

    private Building sut;

    private Building emptySut;

    @BeforeEach
    void setUp() {
        for (int i = 0; i < 4; i++) {
            rooms.add(new Room("Room" + String.valueOf(i),100f, 100f, 100f*(i+1), 200f));
        }
        for (int i = 0; i < 2; i++) {
            floors.add(new Floor("Floor" + String.valueOf(i), asList(rooms.get(2*i), rooms.get(2*i+1))));
        }
        sut = new Building("Building", floors);
        emptySut = new Building("Building2",floors2);
    }

    @Test
    void testCountSurface(){ assertEquals(0,emptySut.countSurface());}

    @Test
    void testCountCube(){ assertEquals(0,emptySut.countCube());}

    @Test
    void testCountLight(){ assertEquals(0,emptySut.countAverageLight());}

    @Test
    void testCountHeating(){assertEquals(0,emptySut.countAverageHeating());}

    @Test
    void testGetLocalizationAboveHeating(){
        List<Room> list = emptySut.getBuildingsAboveHeating(100);
        for( Room r: list){
            assertTrue(r.getHeating() > 100);
        }
    }

    @Test
    void shouldCorrectlyCountSurface() {
        assertEquals(400f, sut.countSurface());
    }

    @Test
    void shouldCorrectlyCountCube() {
        assertEquals(400f, sut.countCube());
    }

    @Test
    void shouldCorrectlyCountLight() {
        assertEquals(2f, sut.countAverageLight());
    }

    @Test
    void shouldCorrectlyCountHeating(){assertEquals(2.5f,sut.countAverageHeating());}

    @Test
    void shouldCorrectlyGetLocalizationAboveHeating(){
        List<Room> list = sut.getBuildingsAboveHeating(250);
        for( Room r: list){
            assertTrue(r.getHeating() > 100);
            System.out.println(r.getHeating()+" ");
        }
    }

}