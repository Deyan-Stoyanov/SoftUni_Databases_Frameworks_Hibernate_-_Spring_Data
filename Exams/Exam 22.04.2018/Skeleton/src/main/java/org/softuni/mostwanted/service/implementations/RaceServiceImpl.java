package org.softuni.mostwanted.service.implementations;

import org.softuni.mostwanted.model.dto.importDtos.xml.EntryIdXMLImport;
import org.softuni.mostwanted.model.dto.importDtos.xml.RaceXMLImportDto;
import org.softuni.mostwanted.model.entity.Race;
import org.softuni.mostwanted.model.entity.RaceEntry;
import org.softuni.mostwanted.parser.ValidationUtil;
import org.softuni.mostwanted.repositories.DistrictRepository;
import org.softuni.mostwanted.repositories.RaceEntryRepository;
import org.softuni.mostwanted.repositories.RaceRepository;
import org.softuni.mostwanted.service.interfaces.RaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class RaceServiceImpl implements RaceService {
    private final RaceEntryRepository raceEntryRepository;
    private final RaceRepository raceRepository;
    private final DistrictRepository districtRepository;

    @Autowired
    public RaceServiceImpl(RaceEntryRepository raceEntryRepository, RaceRepository raceRepository, DistrictRepository districtRepository) {
        this.raceEntryRepository = raceEntryRepository;
        this.raceRepository = raceRepository;
        this.districtRepository = districtRepository;
    }

    @Override
    public void create(RaceXMLImportDto dto) {
        if(ValidationUtil.isValid(dto)){
            Race race = new Race();
            race.setLaps(dto.getLaps());
            if(this.districtRepository.findByName(dto.getDistrictName()) != null){
                race.setDistrict(this.districtRepository.findByName(dto.getDistrictName()));
            }
            List<RaceEntry> entries = new ArrayList<>();
            this.raceRepository.save(race);
            for (EntryIdXMLImport entryId:dto.getEntries()) {
                if(this.raceEntryRepository.findById(entryId.getId()) != null){
                    RaceEntry raceEntry = this.raceEntryRepository.findById(entryId.getId());
                    raceEntry.setRace(race);
                    this.raceEntryRepository.saveAndFlush(raceEntry);
                    entries.add(this.raceEntryRepository.findById(entryId.getId()));
                }
            }
            race.setEntries(entries);
            this.raceRepository.saveAndFlush(race);
            System.out.println(String.format("Succesfully imported Race - %s", race.getId()));
        } else {
            throw new IllegalArgumentException();
        }
    }
}
