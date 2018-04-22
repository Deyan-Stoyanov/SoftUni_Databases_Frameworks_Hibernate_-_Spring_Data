package com.masdefect.controller;

import com.masdefect.domain.dto.json.AnomalyImportJSONDto;
import com.masdefect.domain.dto.json.AnomalyVictimsJSONDto;
import com.masdefect.parser.interfaces.FileParser;
import com.masdefect.service.AnomalyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller
public class AnomalyVictimsController {
    private final FileParser fileParser;
    private final AnomalyService anomalyService;

    @Autowired
    public AnomalyVictimsController(@Qualifier(value = "JSONParser") FileParser fileParser, AnomalyService anomalyService) {
        this.fileParser = fileParser;
        this.anomalyService = anomalyService;
    }

    public String importDataFromJSON(String fileContent){
        StringBuilder sb = new StringBuilder();
        try {
            AnomalyVictimsJSONDto[] dtos = this.fileParser.read(AnomalyVictimsJSONDto[].class, fileContent);
            for (AnomalyVictimsJSONDto s:dtos) {
                try {
                    this.anomalyService.create(s);
                    sb.append(String.format("Successfully imported Anomaly from %s.%n", s.getId()));
                }catch (IllegalArgumentException e){
                    sb.append("Error: Invalid data.").append(System.lineSeparator());
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return sb.toString();
    }
}
