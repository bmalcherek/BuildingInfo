package com.io.buildings.controllers;

import com.io.buildings.models.Building;
import com.io.buildings.models.Floor;
import com.io.buildings.models.Localization;
import com.io.buildings.models.Room;
import com.io.buildings.repositories.FloorRepository;
import com.io.buildings.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.io.buildings.controllers.requests.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/floors")
public class FloorController {

    private static final String FLOOR_NOT_FOUND = "Floor with given Id doesn't exists";
    private final FloorRepository floorRepository;
    private final RoomRepository roomRepository;

    @Autowired
    public FloorController(FloorRepository floorRepository, RoomRepository roomRepository) {
        this.floorRepository = floorRepository;
        this.roomRepository = roomRepository;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping
    public List<Floor> getAllFloors() {
        return floorRepository.findAll();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/{id}")
    public Floor getInfo(@PathVariable Integer id) {
        return floorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(FLOOR_NOT_FOUND));
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/{id}/surface")
    public Float getSurface(@PathVariable Integer id) {
        return floorRepository.findById(id).map(Localization::countSurface
        ).orElseThrow(() -> new ResourceNotFoundException(FLOOR_NOT_FOUND));
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping
    public Floor createFloor(@RequestBody @Valid FloorRequest floorRequest) {
        List<Room> rooms = roomRepository.findAllNotUsed(floorRequest.getRoomIds());
        if (rooms.size() != floorRequest.getRoomIds().size()) {
            throw new ResourceNotFoundException("There are rooms that are not found");
        }
        return floorRepository.save(new Floor(floorRequest.getName(), rooms));
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PatchMapping("/{id}/addRooms")
    public Floor addRooms(@RequestBody List<Integer> roomIds, @PathVariable Integer id) {
        Floor floor = floorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(FLOOR_NOT_FOUND));
        List<Room> rooms = roomRepository.findAllNotUsed(roomIds);
        if (rooms.size() != roomIds.size()) {
            throw new ResourceNotFoundException("There are rooms that are not found");
        }
        floor.getRooms().addAll(rooms);
        return floorRepository.save(floor);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/{id}")
    public ResponseEntity deleteFloor(@PathVariable Integer id) {
        return floorRepository.findById(id).map(floor -> {
            floorRepository.delete(floor);
            return ResponseEntity.ok("Deleted floor with Id: " + id.toString());
        }).orElseThrow(() -> new ResourceNotFoundException(FLOOR_NOT_FOUND));
    }

}
