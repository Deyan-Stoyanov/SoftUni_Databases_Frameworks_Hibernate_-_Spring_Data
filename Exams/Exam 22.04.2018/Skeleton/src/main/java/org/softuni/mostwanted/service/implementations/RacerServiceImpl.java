package org.softuni.mostwanted.service.implementations;

import org.softuni.mostwanted.model.dto.exportDtos.json.RacersWithCarsJSONExportDto;
import org.softuni.mostwanted.model.dto.exportDtos.xml.MostWantedXMLExportWrapper;
import org.softuni.mostwanted.model.dto.exportDtos.xml.RaceEntryXMLExportDto;
import org.softuni.mostwanted.model.dto.exportDtos.xml.RacerExportXmlDto;
import org.softuni.mostwanted.model.dto.importDtos.json.RacerJSONImportDto;
import org.softuni.mostwanted.model.entity.Racer;
import org.softuni.mostwanted.parser.ValidationUtil;
import org.softuni.mostwanted.repositories.CarRepository;
import org.softuni.mostwanted.repositories.RaceEntryRepository;
import org.softuni.mostwanted.repositories.RacerRepository;
import org.softuni.mostwanted.repositories.TownRepository;
import org.softuni.mostwanted.service.interfaces.RacerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RacerServiceImpl implements RacerService {
    private final RacerRepository racerRepository;
    private final TownRepository townRepository;
    private final CarRepository carRepository;
    private final RaceEntryRepository raceEntryRepository;

    @Autowired
    public RacerServiceImpl(RacerRepository racerRepository, TownRepository townRepository, CarRepository carRepository, RaceEntryRepository raceEntryRepository) {
        this.racerRepository = racerRepository;
        this.townRepository = townRepository;
        this.carRepository = carRepository;
        this.raceEntryRepository = raceEntryRepository;
    }

    @Override
    public void create(RacerJSONImportDto dto) {
        if(ValidationUtil.isValid(dto)){
            if(this.racerRepository.findByName(dto.getName()) != null){
                System.out.println("Error: Duplicate Data!");
            } else {
                Racer racer = new Racer();
                racer.setName(dto.getName());
                racer.setAge(dto.getAge());
                racer.setBounty(dto.getBounty());
                if(this.townRepository.findByName(dto.getHomeTown()) != null){
                    racer.setHomeTown(this.townRepository.findByName(dto.getHomeTown()));
                }
                this.racerRepository.saveAndFlush(racer);
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public List<RacersWithCarsJSONExportDto> exportRacersWithCars() {
        List<Racer> allRacers = this.racerRepository.findAllRacers();
        List<RacersWithCarsJSONExportDto> racerDtos = new ArrayList<>();
        for (Racer r:allRacers) {
            RacersWithCarsJSONExportDto dto = new RacersWithCarsJSONExportDto();
            dto.setName(r.getName());
            dto.setAge(r.getAge());
            List<String> cars = this.carRepository.findByRacerId(r.getId());
            dto.setCars(cars);
            racerDtos.add(dto);
        }
        racerDtos = racerDtos.stream().filter(x -> !x.getCars().isEmpty()).sorted((x, y) -> {
            if(y.getCars().size() - x.getCars().size() == 0){
                return x.getName().compareTo(y.getName());
            }
            return y.getCars().size() - x.getCars().size();
        }).collect(Collectors.toList());
        return racerDtos;
    }

    @Override
    public MostWantedXMLExportWrapper getTheMostWanted() {
        Integer idOfTheMostWantedRacer = this.raceEntryRepository.getRacerIdWithMostEntries().get(0);
        Racer racer = this.racerRepository.getMostWantedRacer(idOfTheMostWantedRacer);
        RacerExportXmlDto dto = new RacerExportXmlDto();
        dto.setName(racer.getName());
        List<RaceEntryXMLExportDto> entries = this.raceEntryRepository.getRaceEntriesOfTheMostWantedRacer(idOfTheMostWantedRacer);
        dto.setEntries(entries);
        MostWantedXMLExportWrapper wrapper = new MostWantedXMLExportWrapper(dto);
        return wrapper;
    }
}
