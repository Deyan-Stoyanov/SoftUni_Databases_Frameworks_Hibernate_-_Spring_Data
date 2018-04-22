package com.masdefect.service;

import com.masdefect.domain.dto.json.PlanetExportJSONDto;
import com.masdefect.domain.dto.json.PlanetImportJSONDto;
import com.masdefect.domain.entities.Planet;
import com.masdefect.parser.ValidationUtil;
import com.masdefect.repository.PlanetRepository;
import com.masdefect.repository.SolarSystemRepository;
import com.masdefect.repository.StarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PlanetServiceImpl implements PlanetService {
    private final PlanetRepository planetRepository;
    private final StarRepository starRepository;
    private final SolarSystemRepository solarSystemRepository;

    @Autowired
    public PlanetServiceImpl(PlanetRepository planetRepository, StarRepository starRepository, SolarSystemRepository solarSystemRepository) {
        this.planetRepository = planetRepository;
        this.starRepository = starRepository;
        this.solarSystemRepository = solarSystemRepository;
    }

    @Override
    public void create(PlanetImportJSONDto planetImportJSONDto) {
        if(ValidationUtil.isValid(planetImportJSONDto)){
            Planet planet = new Planet();
            planet.setName(planetImportJSONDto.getName());
            this.planetRepository.save(planet);
            if(this.starRepository.findByName(planetImportJSONDto.getSun()) != null){
                planet.setSun(this.starRepository.findByName(planetImportJSONDto.getSun()));
                this.planetRepository.save(planet);
            }
            if(this.solarSystemRepository.findByName(planetImportJSONDto.getSolarSystem()) != null){
                planet.setSolarSystem(this.solarSystemRepository.findByName(planetImportJSONDto.getSolarSystem()));
                this.planetRepository.save(planet);
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public List<PlanetExportJSONDto> findAllPlanetsWithoutPeopleTeleportedFromThem() {
        return this.planetRepository.findByNoTeleports();
    }
}
