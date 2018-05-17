package org.softuni.mostwanted.repositories;

import org.softuni.mostwanted.model.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
    Car findByBrand(String brand);
    Car findById(Integer id);
    @Query(value = "SELECT c FROM Car AS c WHERE c.brand=:brand AND c.model=:model AND c.yearOfProduction=:year")
    Car findIfDuplicateExists(@Param("brand") String brand, @Param("model") String model, @Param("year") Integer year);

    @Query(value = "SELECT concat(c.brand, ' ', c.model, ' ', c.yearOfProduction) FROM Car AS c JOIN c.racer AS r WHERE r.id=:id ORDER BY c.maxSpeed DESC, c.price ASC ")
    List<String> findByRacerId(@Param("id") Integer id);

}
