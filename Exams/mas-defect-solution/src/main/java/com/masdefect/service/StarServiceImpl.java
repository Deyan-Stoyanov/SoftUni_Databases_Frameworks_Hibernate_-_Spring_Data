package com.masdefect.service;

import com.masdefect.domain.dto.json.StarImportJSONDto;
import com.masdefect.domain.entities.Star;
import com.masdefect.parser.ValidationUtil;
import com.masdefect.repository.StarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class StarServiceImpl implements StarService{
    private final StarRepository starRepository;
    private final SolarSystemService solarSystemService;

    @Autowired
    public StarServiceImpl(StarRepository starRepository, SolarSystemService solarSystemService) {
        this.starRepository = starRepository;
        this.solarSystemService = solarSystemService;
    }

    @Override
    public void create(StarImportJSONDto starImportJSONDto) {
        if(ValidationUtil.isValid(starImportJSONDto)){
            Star star = new Star();
            star.setName(starImportJSONDto.getName());
            this.starRepository.save(star);
            if(this.solarSystemService.findByName(starImportJSONDto.getSolarSystem()) != null){
                star.setSolarSystem(this.solarSystemService.findByName(starImportJSONDto.getSolarSystem()));
                this.starRepository.save(star);
            }
        } else {
            throw new IllegalArgumentException();
        }
    }
}
