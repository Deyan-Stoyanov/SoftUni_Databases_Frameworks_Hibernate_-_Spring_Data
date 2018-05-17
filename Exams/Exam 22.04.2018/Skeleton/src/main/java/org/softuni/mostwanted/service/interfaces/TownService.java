package org.softuni.mostwanted.service.interfaces;

import org.softuni.mostwanted.model.dto.exportDtos.json.TownsWithRacersJSONExport;
import org.softuni.mostwanted.model.dto.importDtos.json.TownJSONImportDto;

import java.util.List;

public interface TownService {
    void create(TownJSONImportDto dto);
    List<TownsWithRacersJSONExport> allTownsWithRacers();
}
