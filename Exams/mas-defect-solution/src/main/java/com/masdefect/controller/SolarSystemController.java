package com.masdefect.controller;

import com.masdefect.domain.dto.json.SolarSystemImportJSONDto;
import com.masdefect.parser.interfaces.FileParser;
import com.masdefect.service.SolarSystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.Arrays;

@Controller
public class SolarSystemController {

    private final SolarSystemService solarSystemService;
    private final FileParser fileParser;

    @Autowired
    public SolarSystemController(SolarSystemService solarSystemService, @Qualifier(value = "JSONParser") FileParser fileParser) {
        this.solarSystemService = solarSystemService;
        this.fileParser = fileParser;
    }

    public String importDataFromJSON(String fileContent){
        StringBuilder sb = new StringBuilder();
        try {
            SolarSystemImportJSONDto[] dto = this.fileParser.read(SolarSystemImportJSONDto[].class, fileContent);
            for (SolarSystemImportJSONDto solarSystemImportJSONDto:dto) {
                try {
                    this.solarSystemService.create(solarSystemImportJSONDto);
                    sb.append(String.format("Successfully imported Solar System %s.%n", solarSystemImportJSONDto.getName()));
                }catch (IllegalArgumentException e){
                    sb.append("Error: Invalid data.%n").append(System.lineSeparator());
                }
            }
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
