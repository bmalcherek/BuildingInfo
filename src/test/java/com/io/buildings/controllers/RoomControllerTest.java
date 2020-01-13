package com.io.buildings.controllers;

import com.io.buildings.controllers.requests.RoomRequest;
import com.io.buildings.models.Room;
import com.io.buildings.repositories.RoomRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;


import static org.junit.jupiter.api.Assertions.*;

class RoomControllerTest {

    private RoomController controller;
    private RoomRepository roomRepo;
    private List<Room> rooms = new ArrayList<>();
    private RoomRequest roomReq;
    @BeforeEach
    void setUp() {
        for (int i = 0; i < 4; i++) {
            rooms.add(new Room("Room" + String.valueOf(i),100f*(i+1), 100f, 100f*(i+1), 1200f));
        }
        roomRepo = mock(RoomRepository.class);
        controller =  new RoomController(roomRepo);
        roomReq = new RoomRequest();
        roomReq.setName("Room");
        roomReq.setArea(100f);
        roomReq.setCube(100f);
        roomReq.setHeating(100f);
        roomReq.setLight(200f);
    }

    @Test
    void testGetAllRooms() {
        when(roomRepo.findAll()).thenReturn(rooms);
        List<Room> list = controller.getAllRooms();
        verify(roomRepo).findAll();
        for(int i = 0;i<list.size();i++){
            assertEquals(rooms.get(i),list.get(i));
        }
        assertEquals(4,list.size());
        System.out.println("getAllRooms");
        System.out.println(list);
    }

    @Test
    void testGetInfo() {
        when(roomRepo.findById(1)).thenReturn(java.util.Optional.of(new Room("Room", 100f, 200f, 300f, 400f)));
        Room room = controller.getInfo(1);
        verify(roomRepo).findById(1);
        assertEquals("Room",room.getName());
        assertEquals(100,room.getArea());
        assertEquals(200,room.getCube());
        assertEquals(300,room.getHeating());
        assertEquals(400,room.getLight());
    }

    @Test
    void testGetSurface() {
        when(roomRepo.findById(1)).thenReturn(java.util.Optional.of(new Room("Room", 100f, 200f, 300f, 400f)));
        Float surf = controller.getSurface(1);
        verify(roomRepo).findById(1);
        assertEquals(100,surf);
    }

    @Test
    void testGetCube() {
        when(roomRepo.findById(1)).thenReturn(java.util.Optional.of(new Room("Room", 100f, 200f, 300f, 400f)));
        Float cube = controller.getCube(1);
        verify(roomRepo).findById(1);
        assertEquals(200,cube);
    }

    @Test
    void testGetAverageLight() {
        when(roomRepo.findById(1)).thenReturn(java.util.Optional.of(new Room("Room", 100f, 200f, 300f, 400f)));
        Float light = controller.getAverageLight(1);
        verify(roomRepo).findById(1);
        assertEquals(4,light);
    }

    @Test
    void testGetAverageHeating() {
        when(roomRepo.findById(1)).thenReturn(java.util.Optional.of(new Room("Room", 100f, 200f, 300f, 400f)));
        Float heating = controller.getAverageHeating(1);
        verify(roomRepo).findById(1);
        assertEquals(3,heating);
    }

    @Test
    void testGetRoomsAboveHeating() {
        when(roomRepo.findAllAboveHeating(250f)).thenReturn(rooms.subList(2,4));
        List<Room> list = controller.getRoomsAboveHeating(250f);
        verify(roomRepo).findAllAboveHeating(250f);
        assertEquals(2,list.size());
        assertTrue(list.get(0).getHeating() > 250);
        assertTrue(list.get(1).getHeating() > 250);
        System.out.println("getRoomsAboveHeating");
        System.out.println(list);
    }

    @Test
    void testGetRoomsBySurface() {
        when(roomRepo.findAllBySurface(150f,350f)).thenReturn(rooms.subList(1,3));
        List<Room> list = controller.getRoomsBySurface(150f,350f);
        verify(roomRepo).findAllBySurface(150f,350f);
        assertEquals(2,list.size());
        assertTrue(list.get(0).getArea() >= 150 && list.get(0).getArea() <=350);
        assertTrue(list.get(1).getArea() >= 150 && list.get(1).getArea() <=350);
        System.out.println("getRoomsBySurface");
        System.out.println(list);
    }

    @Test
    void testCreateRoom() {
        Room test = new Room(roomReq.getName(), roomReq.getArea(), roomReq.getCube(), roomReq.getHeating(), roomReq.getLight());
        when(roomRepo.save(test)).thenReturn(test);
        Room room = controller.createRoom(roomReq);
        verify(roomRepo).save(test);
        assertEquals("Room",room.getName());
        assertEquals(100,room.getArea());
        assertEquals(100,room.getCube());
        assertEquals(100,room.getHeating());
        assertEquals(200,room.getLight());
    }

    @Test
    void editRoom() {
        Room test = new Room(roomReq.getName(), roomReq.getArea(), roomReq.getCube(), roomReq.getHeating(), roomReq.getLight());
        when(roomRepo.findById(1)).thenReturn(java.util.Optional.of(new Room("RoomTest", 300f, 300f, 300f, 600f)));
        when(roomRepo.save(test)).thenReturn(test);
        Room room = controller.editRoom(roomReq,1);
        InOrder inOrder = inOrder(roomRepo);
        inOrder.verify(roomRepo).findById(1);
        inOrder.verify(roomRepo).save(test);
        assertEquals("Room",room.getName());
        assertEquals(100,room.getArea());
        assertEquals(100,room.getCube());
        assertEquals(100,room.getHeating());
        assertEquals(200,room.getLight());
    }

    @Test
    void deleteRoom() {
        Room test = new Room(roomReq.getName(), roomReq.getArea(), roomReq.getCube(), roomReq.getHeating(), roomReq.getLight());
        ResponseEntity expected =  ResponseEntity.ok("Deleted room with Id: " + 1);
        when(roomRepo.findById(1)).thenReturn(java.util.Optional.of(test));
        ResponseEntity response = controller.deleteRoom(1);
        InOrder inOrder = inOrder(roomRepo);
        inOrder.verify(roomRepo).findById(1);
        inOrder.verify(roomRepo).delete(test);
        assertEquals(expected,response);
    }
}