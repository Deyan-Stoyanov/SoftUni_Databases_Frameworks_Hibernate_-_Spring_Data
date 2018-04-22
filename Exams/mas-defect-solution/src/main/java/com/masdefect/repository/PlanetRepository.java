package com.masdefect.repository;

import com.masdefect.domain.dto.json.PlanetExportJSONDto;
import com.masdefect.domain.entities.Planet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanetRepository extends JpaRepository<Planet, Integer> {
    Planet findByName(String name);
    @Query(value = "SELECT new com.masdefect.domain.dto.json.PlanetExportJSONDto(p.name) FROM Planet  AS p WHERE p NOT IN (SELECT a.teleportPlanet FROM Anomaly AS a)")
    List<PlanetExportJSONDto> findByNoTeleports();
}
