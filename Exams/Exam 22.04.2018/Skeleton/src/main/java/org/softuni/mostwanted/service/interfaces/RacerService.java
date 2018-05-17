package org.softuni.mostwanted.service.interfaces;

import org.softuni.mostwanted.model.dto.exportDtos.json.RacersWithCarsJSONExportDto;
import org.softuni.mostwanted.model.dto.exportDtos.xml.MostWantedXMLExportWrapper;
import org.softuni.mostwanted.model.dto.importDtos.json.RacerJSONImportDto;

import java.util.List;

public interface RacerService {
    void create(RacerJSONImportDto dto);
    List<RacersWithCarsJSONExportDto> exportRacersWithCars();
    MostWantedXMLExportWrapper getTheMostWanted();
}
