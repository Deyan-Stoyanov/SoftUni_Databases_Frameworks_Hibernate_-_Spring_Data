package com.masdefect.service;

import com.masdefect.domain.dto.json.SolarSystemImportJSONDto;
import com.masdefect.domain.entities.SolarSystem;

public interface SolarSystemService {
    SolarSystem findByName(String name);
    void create(SolarSystemImportJSONDto solarSystemImportJSONDto);
}
