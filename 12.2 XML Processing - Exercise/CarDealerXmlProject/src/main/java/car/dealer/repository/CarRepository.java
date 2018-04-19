package car.dealer.repository;

import car.dealer.model.dto.exportDtos.CarDto;
import car.dealer.model.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    @Query(value = "SELECT new car.dealer.model.dto.exportDtos.CarDto(c.id, c.make, c.model, c.travelledDistance) FROM Car AS c WHERE c.make LIKE '%Toyota%' ORDER BY c.model ASC, c.travelledDistance DESC ")
    List<CarDto> allCarsOfToyota();

}
