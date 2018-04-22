package com.masdefect.controller;

import com.masdefect.domain.dto.json.PlanetImportJSONDto;
import com.masdefect.parser.interfaces.FileParser;
import com.masdefect.service.PlanetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class PlanetsController {

    private final FileParser fileParser;
    private final PlanetService planetService;

    @Autowired
    public PlanetsController(@Qualifier(value = "JSONParser") FileParser fileParser, PlanetService planetService) {
        this.fileParser = fileParser;
        this.planetService = planetService;
    }

    public String importDataFromJSON(String fileContent){
        StringBuilder sb = new StringBuilder();
        try {
            PlanetImportJSONDto[] dto = this.fileParser.read(PlanetImportJSONDto[].class, fileContent);
            for (PlanetImportJSONDto p:dto) {
                try {
                    this.planetService.create(p);
                    sb.append(String.format("Successfully imported Planet %s.%n", p.getName()));
                }catch (IllegalArgumentException e){
                    sb.append("Error: Invalid data.%n").append(System.lineSeparator());
                }
            }
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public String planetsWithNoPeopleTeleportedToThem(){

        try {
            return this.fileParser.write(this.planetService.findAllPlanetsWithoutPeopleTeleportedFromThem(), "");
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
}
