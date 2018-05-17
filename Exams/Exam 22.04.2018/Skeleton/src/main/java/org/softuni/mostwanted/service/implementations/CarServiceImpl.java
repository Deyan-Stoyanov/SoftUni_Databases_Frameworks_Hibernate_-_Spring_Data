package org.softuni.mostwanted.service.implementations;

import org.softuni.mostwanted.model.dto.importDtos.json.CarJSONImportDto;
import org.softuni.mostwanted.model.entity.Car;
import org.softuni.mostwanted.parser.ValidationUtil;
import org.softuni.mostwanted.repositories.CarRepository;
import org.softuni.mostwanted.repositories.RacerRepository;
import org.softuni.mostwanted.service.interfaces.CarService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CarServiceImpl implements CarService {
    private final RacerRepository racerRepository;
    private final CarRepository carRepository;

    public CarServiceImpl(RacerRepository racerRepository, CarRepository carRepository) {
        this.racerRepository = racerRepository;
        this.carRepository = carRepository;
    }

    @Override
    public void create(CarJSONImportDto dto) {
        if(ValidationUtil.isValid(dto)){

            if(this.carRepository.findIfDuplicateExists(dto.getBrand(), dto.getModel(), dto.getYearOfProduction()) != null){
                System.out.println("Error: Duplicate Data!");
            } else {
                Car car = new Car();
                car.setBrand(dto.getBrand());
                car.setMaxSpeed(dto.getMaxSpeed());
                car.setModel(dto.getModel());
                car.setPrice(dto.getPrice());
                car.setYearOfProduction(dto.getYearOfProduction());
                car.setZeroToSixty(dto.getZeroToSixty());
                if(this.racerRepository.findByName(dto.getRacerName()) != null){
                    car.setRacer(this.racerRepository.findByName(dto.getRacerName()));
                }
                this.carRepository.saveAndFlush(car);
            }
        } else {
            throw new IllegalArgumentException();
        }
    }
}
