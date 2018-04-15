package car.dealer.service;

import car.dealer.model.dto.CarDto;
import car.dealer.model.dto.CarViewModel;
import car.dealer.model.entity.Car;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface CarService {
    void save(Car car);

    void save(List<Car> cars);

    Optional<Car> getById(Long id);

    List<CarDto> allToyotaCars();

    List<CarViewModel> findAllCarsAndParts();
}
