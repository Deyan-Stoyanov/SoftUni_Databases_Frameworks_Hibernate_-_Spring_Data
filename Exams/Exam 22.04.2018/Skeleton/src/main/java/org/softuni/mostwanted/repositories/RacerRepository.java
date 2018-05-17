package org.softuni.mostwanted.repositories;

import org.softuni.mostwanted.model.entity.Racer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RacerRepository extends JpaRepository<Racer, Integer> {
    Racer findByName(String name);
    @Query(value = "SELECT r FROM Racer AS r")
    List<Racer> findAllRacers();

    @Query(value = "SELECT r FROM Racer AS r WHERE r.id=:id")
    Racer getMostWantedRacer(@Param("id")Integer id);


}
