package org.softuni.mostwanted.service.implementations;

import org.softuni.mostwanted.model.dto.importDtos.json.DistrictJSONImportDto;
import org.softuni.mostwanted.model.entity.District;
import org.softuni.mostwanted.parser.ValidationUtil;
import org.softuni.mostwanted.repositories.DistrictRepository;
import org.softuni.mostwanted.repositories.TownRepository;
import org.softuni.mostwanted.service.interfaces.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class DistrictServiceImpl implements DistrictService {

    private final DistrictRepository districtRepository;
    private final TownRepository townRepository;
    @Autowired
    public DistrictServiceImpl(DistrictRepository districtRepository, TownRepository townRepository) {
        this.districtRepository = districtRepository;
        this.townRepository = townRepository;
    }

    @Override
    public void create(DistrictJSONImportDto dto) {
        if(ValidationUtil.isValid(dto)){
            if(this.districtRepository.findByName(dto.getName()) != null){
                System.out.println("Error: Duplicate Data!");
            } else {
                if(this.townRepository.findByName(dto.getTownName()) != null){
                    District district = new District();
                    district.setName(dto.getName());
                    district.setTown(this.townRepository.findByName(dto.getTownName()));
                    this.districtRepository.saveAndFlush(district);
                }
            }
        } else {
            throw new IllegalArgumentException();
        }
    }
}
