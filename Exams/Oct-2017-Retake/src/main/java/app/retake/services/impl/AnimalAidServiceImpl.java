package app.retake.services.impl;


import app.retake.domain.dto.AnimalAidJSONImportDTO;
import app.retake.domain.models.AnimalAid;
import app.retake.parser.ValidationUtil;
import app.retake.repositories.AnimalAidRepository;
import app.retake.services.api.AnimalAidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class AnimalAidServiceImpl implements AnimalAidService {

    private final AnimalAidRepository animalAidRepository;

    @Autowired
    public AnimalAidServiceImpl(AnimalAidRepository animalAidRepository) {
        this.animalAidRepository = animalAidRepository;
    }

    @Override
    public void create(AnimalAidJSONImportDTO dto) {
        if(ValidationUtil.isValid(dto)){
            AnimalAid animalAid = new AnimalAid();
            if(this.animalAidRepository.findByName(dto.getName()) != null){
                animalAid = this.animalAidRepository.findByName(dto.getName());
                if(!animalAid.getPrice().equals(dto.getPrice())){
                    animalAid.setPrice(dto.getPrice());
                    this.animalAidRepository.saveAndFlush(animalAid);
                }
            } else {
                animalAid.setName(dto.getName());
                animalAid.setPrice(dto.getPrice());
                this.animalAidRepository.saveAndFlush(animalAid);
            }
        }else {
            throw new IllegalArgumentException();
        }
    }
}
