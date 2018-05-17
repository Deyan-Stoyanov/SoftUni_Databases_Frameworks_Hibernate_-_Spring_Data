package org.softuni.mostwanted.service.interfaces;

import org.softuni.mostwanted.model.dto.importDtos.json.CarJSONImportDto;

public interface CarService {
    void create(CarJSONImportDto dto);

}
