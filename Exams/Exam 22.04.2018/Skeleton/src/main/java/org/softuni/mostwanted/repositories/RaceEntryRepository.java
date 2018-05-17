package org.softuni.mostwanted.repositories;

import org.softuni.mostwanted.model.dto.exportDtos.xml.RaceEntryXMLExportDto;
import org.softuni.mostwanted.model.entity.RaceEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RaceEntryRepository extends JpaRepository<RaceEntry, Integer> {
    RaceEntry findById(Integer id);

    @Query(value = " SELECT ra.id FROM RaceEntry AS r JOIN r.racer AS ra GROUP BY ra.id ORDER BY count(ra.id) DESC")
    List<Integer> getRacerIdWithMostEntries();

    @Query(value = " SELECT new org.softuni.mostwanted.model.dto.exportDtos.xml.RaceEntryXMLExportDto(r.finishTime, concat(c.brand, ' ', c.model, ' @ ', c.yearOfProduction)) FROM RaceEntry AS r JOIN r.car AS c JOIN r.racer AS ra WHERE ra.id=:id ORDER BY r.finishTime ASC")
    List<RaceEntryXMLExportDto> getRaceEntriesOfTheMostWantedRacer(@Param("id") Integer id);
}
