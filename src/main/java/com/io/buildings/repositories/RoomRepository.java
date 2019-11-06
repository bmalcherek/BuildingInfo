package com.io.buildings.repositories;

import com.io.buildings.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM room WHERE id IN ?1 AND floor_id IS NULL ")
    List<Room> findAllNotUsed(List<Integer> floorIds);
}
