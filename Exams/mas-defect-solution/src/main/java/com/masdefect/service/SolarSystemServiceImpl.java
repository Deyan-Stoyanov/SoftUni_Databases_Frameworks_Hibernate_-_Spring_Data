package com.masdefect.service;

import com.masdefect.domain.dto.json.SolarSystemImportJSONDto;
import com.masdefect.domain.entities.SolarSystem;
import com.masdefect.parser.ValidationUtil;
import com.masdefect.parser.interfaces.ModelParser;
import com.masdefect.repository.SolarSystemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class SolarSystemServiceImpl implements SolarSystemService{
    private final SolarSystemRepository solarSystemRepository;
    private final ModelParser parser;

    @Autowired
    public SolarSystemServiceImpl(SolarSystemRepository solarSystemRepository, ModelParser parser) {
        this.solarSystemRepository = solarSystemRepository;
        this.parser = parser;
    }

    @Override
    public void create(SolarSystemImportJSONDto solarSystemImportJSONDto) {
        if(ValidationUtil.isValid(solarSystemImportJSONDto)){
            SolarSystem solarSystem = this.parser.convert(solarSystemImportJSONDto, SolarSystem.class);
            this.solarSystemRepository.saveAndFlush(solarSystem);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public SolarSystem findByName(String name){
        return this.solarSystemRepository.findByName(name);
    }
}
