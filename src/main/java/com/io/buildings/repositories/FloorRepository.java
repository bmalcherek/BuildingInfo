package com.io.buildings.repositories;

import com.io.buildings.models.Floor;
import com.io.buildings.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for table Floor
 */
@Repository
public interface FloorRepository extends JpaRepository<Floor, Integer> {
    /**
     * Select method that returns all floors without assigned building
     * @return List of Floors
     */
    @Query(nativeQuery = true, value = "SELECT * FROM floor WHERE id IN ?1 AND building_id IS NULL ")
    List<Floor> findAllNotUsed(List<Integer> floorIds);
    /**
     * Select method that returns all floors with heating above limit
     * @return List of Floors
     */
    @Query(nativeQuery = true, value = "SELECT * FROM floor WHERE id IN (SELECT floor_id FROM room GROUP BY floor_id HAVING SUM(heating)/SUM(area) > ?1 )")
    List<Floor> findAllAboveHeating(Float maxHeating);
    /**
     * Select method that returns all floors with surface in range of given values
     * @return List of Floors
     */
    @Query(nativeQuery = true, value = "SELECT * FROM floor WHERE id IN (SELECT floor_id FROM room GROUP BY floor_id HAVING SUM(area) >= ?1 AND SUM(area) <=?2 )")
    List<Floor> findAllBySurface(Float leftValue,Float rightValue);
}
