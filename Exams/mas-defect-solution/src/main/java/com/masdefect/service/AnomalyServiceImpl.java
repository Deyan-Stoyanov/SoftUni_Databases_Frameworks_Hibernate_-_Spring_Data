package com.masdefect.service;

import com.masdefect.domain.dto.json.AnomalyExportJSONDto;
import com.masdefect.domain.dto.json.AnomalyImportJSONDto;
import com.masdefect.domain.dto.json.AnomalyVictimsJSONDto;
import com.masdefect.domain.dto.xml.AnomaliesXMLDto;
import com.masdefect.domain.dto.xml.AnomalyXMLDto;
import com.masdefect.domain.dto.xml.VictimXMLDto;
import com.masdefect.domain.entities.Anomaly;
import com.masdefect.domain.entities.Person;
import com.masdefect.parser.ValidationUtil;
import com.masdefect.repository.AnomalyRepository;
import com.masdefect.repository.PersonRepository;
import com.masdefect.repository.PlanetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class AnomalyServiceImpl implements AnomalyService{
    private final PlanetRepository planetRepository;
    private final AnomalyRepository anomalyRepository;
    private final PersonRepository personRepository;

    @Autowired
    public AnomalyServiceImpl(PlanetRepository planetRepository, AnomalyRepository anomalyRepository, PersonRepository personRepository) {
        this.planetRepository = planetRepository;
        this.anomalyRepository = anomalyRepository;
        this.personRepository = personRepository;
    }

    @Override
    public void create(AnomalyImportJSONDto anomalyImportJSONDto) {
        if(ValidationUtil.isValid(anomalyImportJSONDto)){
            Anomaly anomaly = new Anomaly();
            if(planetRepository.findByName(anomalyImportJSONDto.getOriginPlanet()) != null && planetRepository.findByName(anomalyImportJSONDto.getTeleportPlanet()) != null){
                anomaly.setOriginPlanet(planetRepository.findByName(anomalyImportJSONDto.getOriginPlanet()));
                anomaly.setTeleportPlanet(planetRepository.findByName(anomalyImportJSONDto.getTeleportPlanet()));
                this.anomalyRepository.save(anomaly);
            }

        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void create(AnomalyVictimsJSONDto anomalyVictimsDto) {
        if(ValidationUtil.isValid(anomalyVictimsDto)){
            Anomaly anomaly = this.anomalyRepository.getOne(anomalyVictimsDto.getId());
            if(anomaly == null){
                throw new IllegalArgumentException();
            }
            Person person = this.personRepository.findByName(anomalyVictimsDto.getPerson());
            if(person == null){
                throw new IllegalArgumentException();
            }
            anomaly.getPersons().add(person);
            person.getAnomalies().add(anomaly);
            this.anomalyRepository.save(anomaly);
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void create(AnomalyXMLDto anomalyImportXMLDto) {
        Anomaly anomaly = new Anomaly();
        if(ValidationUtil.isValid(anomalyImportXMLDto)){
            if(planetRepository.findByName(anomalyImportXMLDto.getTeleportPlanet()) != null && planetRepository.findByName(anomalyImportXMLDto.getOriginPlanet()) != null){
                anomaly.setTeleportPlanet(planetRepository.findByName(anomalyImportXMLDto.getTeleportPlanet()));
                anomaly.setOriginPlanet(planetRepository.findByName(anomalyImportXMLDto.getOriginPlanet()));
                Set<Person> victims = new HashSet<>();
                for (VictimXMLDto v:anomalyImportXMLDto.getVictims()) {
                    if(this.personRepository.findByName(v.getName()) != null){
                        victims.add(this.personRepository.findByName(v.getName()));
                    }
                }
                anomaly.setPersons(victims);
                this.anomalyRepository.save(anomaly);
            }
        }else {
            throw new IllegalArgumentException();
        }

    }

    @Override
    public AnomalyExportJSONDto findMostAffectingAnomalies() {
        return this.anomalyRepository.getAnomalyWithMostVictims().get(0);
    }

    @Override
    public AnomaliesXMLDto finaAllAnomalies() {
        return new AnomaliesXMLDto(this.anomalyRepository.getAllAnomaliesSortedById());
    }
}
