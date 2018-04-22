package hiberspring.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import hiberspring.dtos.views.town.TownViewDto;
import hiberspring.parser.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hiberspring.dtos.imports.TownImportFromJsonDto;
import hiberspring.dtos.views.town.TownsViewDto;
import hiberspring.entities.Town;
import hiberspring.repositories.TownRepository;
import hiberspring.services.TownService;

@Transactional
@Service
public class TownServiceImpl implements TownService {

    private final TownRepository townRepository;

    @Autowired
    public TownServiceImpl(TownRepository townRepository) {
        this.townRepository = townRepository;
    }

    @Override
    public void createOne(TownImportFromJsonDto townImportFromJsonDto) {
        if(ValidationUtil.isValid(townImportFromJsonDto)){
            Town town = new Town();
            town.setName(townImportFromJsonDto.getName());
            town.setPopulation(townImportFromJsonDto.getPopulation());
            this.townRepository.save(town);
        }else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public TownsViewDto getTowns() {
        List<TownViewDto> viewDtos = this.townRepository.getTownsAndClientsCount();
        viewDtos.forEach(x -> {
            if(x.getClients() == null){
                x.setClients(0L);
            }
        });
        viewDtos = viewDtos.stream().sorted((x, y) -> (int)(y.getClients() - x.getClients())).collect(Collectors.toList());
        return new TownsViewDto(viewDtos);
    }
}
