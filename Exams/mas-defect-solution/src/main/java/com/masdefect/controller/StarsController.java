package com.masdefect.controller;

import com.masdefect.domain.dto.json.StarImportJSONDto;
import com.masdefect.parser.interfaces.FileParser;
import com.masdefect.service.StarService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller
public class StarsController {
    private final StarService starService;
    private final FileParser fileParser;

    public StarsController(StarService starService, @Qualifier(value = "JSONParser") FileParser fileParser) {
        this.starService = starService;
        this.fileParser = fileParser;
    }

    public String importDataFromJSON(String fileContent){
        StringBuilder sb = new StringBuilder();
        try {
            StarImportJSONDto[] dtos = this.fileParser.read(StarImportJSONDto[].class, fileContent);
            for (StarImportJSONDto s:dtos) {
                try {
                    this.starService.create(s);
                    sb.append(String.format("Successfully imported Star %s.%n", s.getName()));
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
