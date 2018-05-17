package org.softuni.mostwanted.service.implementations;

import org.softuni.mostwanted.model.dto.importDtos.xml.RaceEntryXmlImportDto;
import org.softuni.mostwanted.model.entity.RaceEntry;
import org.softuni.mostwanted.parser.ValidationUtil;
import org.softuni.mostwanted.repositories.CarRepository;
import org.softuni.mostwanted.repositories.RaceEntryRepository;
import org.softuni.mostwanted.repositories.RacerRepository;
import org.softuni.mostwanted.service.interfaces.RaceEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class RaceEntryServiceImpl implements RaceEntryService {
    private final RacerRepository racerRepository;
    private final CarRepository carRepository;
    private final RaceEntryRepository raceEntryRepository;

    @Autowired
    public RaceEntryServiceImpl(RacerRepository racerRepository, CarRepository carRepository, RaceEntryRepository raceEntryRepository) {
        this.racerRepository = racerRepository;
        this.carRepository = carRepository;
        this.raceEntryRepository = raceEntryRepository;
    }

    @Override
    public void create(RaceEntryXmlImportDto dto) {
        if(ValidationUtil.isValid(dto)){
            RaceEntry raceEntry = new RaceEntry();
            raceEntry.setHasFinished(dto.getHasFinished());
            raceEntry.setFinishTime(dto.getFinishTime());
            if(this.racerRepository.findByName(dto.getRacer()) != null){
                raceEntry.setRacer(this.racerRepository.findByName(dto.getRacer()));
            }
            if(this.carRepository.findById(dto.getCarId()) != null){
                raceEntry.setCar(this.carRepository.findById(dto.getCarId()));
            }
            this.raceEntryRepository.saveAndFlush(raceEntry);
            System.out.println(String.format("Succesfully imported Race Entry - %s", raceEntry.getId()));
        } else {
            throw new IllegalArgumentException();
        }
    }
}
