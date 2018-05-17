package org.softuni.mostwanted.service.interfaces;

import org.softuni.mostwanted.model.dto.importDtos.xml.RaceXMLImportDto;

public interface RaceService {
    void create(RaceXMLImportDto dto);

}
