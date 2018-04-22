package com.masdefect.repository;

import com.masdefect.domain.dto.json.AnomalyExportJSONDto;
import com.masdefect.domain.dto.xml.AnomalyXMLDto;
import com.masdefect.domain.entities.Anomaly;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnomalyRepository extends JpaRepository<Anomaly, Integer> {

    @Query(value = "SELECT new com.masdefect.domain.dto.json.AnomalyExportJSONDto(a.id, a.originPlanet, a.teleportPlanet, count (p.id)) FROM Anomaly AS a JOIN a.persons AS p GROUP BY a.id ORDER BY count(p.id) DESC")
    List<AnomalyExportJSONDto> getAnomalyWithMostVictims();

    @Query(value = "SELECT new com.masdefect.domain.dto.xml.AnomalyXMLDto(o.name, t.name) FROM Anomaly AS a JOIN a.teleportPlanet AS t JOIN a.originPlanet AS o GROUP BY a.id ORDER BY a.id ASC")
    List<AnomalyXMLDto> getAllAnomaliesSortedById();
}
