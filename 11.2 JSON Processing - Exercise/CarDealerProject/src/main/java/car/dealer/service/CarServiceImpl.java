package car.dealer.service;

import car.dealer.model.dto.CarDto;
import car.dealer.model.dto.CarViewModel;
import car.dealer.model.dto.PartDto;
import car.dealer.model.dto.PartViewModel;
import car.dealer.model.entity.Car;
import car.dealer.model.entity.Part;
import car.dealer.repository.CarRepository;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public void save(Car car) {
        this.carRepository.saveAndFlush(car);
    }

    @Override
    public void save(List<Car> cars) {
        this.carRepository.saveAll(cars);
    }

    @Override
    public Optional<Car> getById(Long id) {
        return this.carRepository.findById(id);
    }

    @Override
    public List<CarDto> allToyotaCars() {
        return this.carRepository.allCarsOfToyota();
    }

    @Override
    public List<CarViewModel> findAllCarsAndParts() {
        List<Car> allCars = this.carRepository.findAll();
        Converter<Car, CarViewModel> con = new AbstractConverter<Car, CarViewModel>() {
            @Override
            protected CarViewModel convert(Car src) {
                CarViewModel c = new CarViewModel();
                c.setMake(src.getMake());
                c.setModel(src.getModel());
                c.setTravelledDistance(src.getTravelledDistance());
                List<PartViewModel> partViewModels = new ArrayList<>();
                for (Part p : src.getParts()) {
                    partViewModels.add(modelMapper.map(p, PartViewModel.class));
                }
                c.setParts(partViewModels);
                return c;
            }
        };
        this.modelMapper.addConverter(con);
        List<CarViewModel> models = new ArrayList<>();
        for (Car c:allCars) {
            models.add(this.modelMapper.map(c, CarViewModel.class));
        }
        return models;
    }
}
