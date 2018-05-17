package org.softuni.mostwanted.repositories;

import org.softuni.mostwanted.model.dto.exportDtos.json.TownsWithRacersJSONExport;
import org.softuni.mostwanted.model.entity.Town;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TownRepository extends JpaRepository<Town, Integer> {
    Town findByName(String name);
    @Query(value = "SELECT new org.softuni.mostwanted.model.dto.exportDtos.json.TownsWithRacersJSONExport(t.name, (SELECT count(rc.id) FROM Racer AS rc JOIN rc.homeTown AS hm WHERE hm.id=t.id)) FROM Town AS t WHERE t.id IN (SELECT h.id FROM Racer AS r JOIN r.homeTown AS h)")
    List<TownsWithRacersJSONExport> findTownsWithRacers();
}
