package org.softuni.mostwanted.service.implementations;

import org.softuni.mostwanted.model.dto.exportDtos.json.TownsWithRacersJSONExport;
import org.softuni.mostwanted.model.dto.importDtos.json.TownJSONImportDto;
import org.softuni.mostwanted.model.entity.Town;
import org.softuni.mostwanted.parser.ValidationUtil;
import org.softuni.mostwanted.repositories.TownRepository;
import org.softuni.mostwanted.service.interfaces.TownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TownServiceImpl implements TownService {

    private final TownRepository townRepository;

    @Autowired
    public TownServiceImpl(TownRepository townRepository) {
        this.townRepository = townRepository;
    }

    @Override
    public void create(TownJSONImportDto dto) {
        if(ValidationUtil.isValid(dto)){
            if(this.townRepository.findByName(dto.getName()) != null){
                System.out.println("Error: Duplicate Data!");
            } else {
                Town town = new Town();
                town.setName(dto.getName());
                this.townRepository.saveAndFlush(town);
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public List<TownsWithRacersJSONExport> allTownsWithRacers() {
        List<TownsWithRacersJSONExport> towns = this.townRepository.findTownsWithRacers();
        towns = towns.stream().sorted((x, y) -> {
            if(y.getRacers() - x.getRacers() == 0){
                return x.getName().compareTo(y.getName());
            }
            return (int)(y.getRacers() - x.getRacers());
        }).collect(Collectors.toList());
        return towns;
    }
}
