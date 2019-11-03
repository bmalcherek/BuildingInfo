package com.io.buildings.repositories;

import com.io.buildings.models.Building;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuildingRepository extends JpaRepository<Building, Integer> {

    @Override
    List<Building> findAll();
}
