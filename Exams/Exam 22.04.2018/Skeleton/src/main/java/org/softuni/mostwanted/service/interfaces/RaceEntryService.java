package org.softuni.mostwanted.service.interfaces;

import org.softuni.mostwanted.model.dto.importDtos.xml.RaceEntryXmlImportDto;
import org.softuni.mostwanted.model.entity.RaceEntry;

public interface RaceEntryService {
    void create(RaceEntryXmlImportDto dto);
}
