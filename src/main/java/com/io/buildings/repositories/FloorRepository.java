package com.io.buildings.repositories;

import com.io.buildings.models.Floor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FloorRepository extends JpaRepository<Floor, Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM floor WHERE id IN ?1 AND building_id IS NULL ")
    List<Floor> findAllNotUsed(List<Integer> floorIds);
}
