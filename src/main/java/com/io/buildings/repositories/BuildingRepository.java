package com.io.buildings.repositories;

import com.io.buildings.models.Building;
import com.io.buildings.models.Floor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for table Building
 */
@Repository
public interface BuildingRepository extends JpaRepository<Building, Integer> {

    /**
     * Select method that returns all Buildings
     * @return List of Buildings
     */
    @Override
    List<Building> findAll();
}
