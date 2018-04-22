package com.masdefect.controller;

import com.masdefect.domain.dto.json.AnomalyImportJSONDto;
import com.masdefect.domain.dto.xml.AnomaliesXMLDto;
import com.masdefect.domain.dto.xml.AnomalyXMLDto;
import com.masdefect.parser.XMLParser;
import com.masdefect.parser.interfaces.FileParser;
import com.masdefect.service.AnomalyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class AnomalyController {
    private final FileParser fileParser;
    private final XMLParser xmlParser;
    private final AnomalyService anomalyService;

    @Autowired
    public AnomalyController(@Qualifier(value = "JSONParser") FileParser fileParser, @Qualifier(value = "XMLParser") XMLParser xmlElement, AnomalyService anomalyService) {
        this.fileParser = fileParser;
        this.xmlParser = xmlElement;
        this.anomalyService = anomalyService;
    }

    public String importDataFromJSON(String fileContent) {
        StringBuilder sb = new StringBuilder();
        try {
            AnomalyImportJSONDto[] dtos = this.fileParser.read(AnomalyImportJSONDto[].class, fileContent);
            for (AnomalyImportJSONDto s:dtos) {
                try {
                    this.anomalyService.create(s);
                    sb.append(String.format("Successfully imported Anomaly from %s.%n", s.getOriginPlanet()));
                }catch (IllegalArgumentException e){
                    sb.append("Error: Invalid data.").append(System.lineSeparator());
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return sb.toString();
    }

    public String importDataFromXML(String fileContent) {
        StringBuilder sb = new StringBuilder();
        try {
            AnomaliesXMLDto anomalies = xmlParser.read(AnomaliesXMLDto.class, fileContent);
            for (AnomalyXMLDto dto:anomalies.getAnomalies()) {
                try {
                    this.anomalyService.create(dto);
                    sb.append(String.format("Successfully imported Anomaly from %s.%n", dto.getOriginPlanet()));
                }catch (IllegalArgumentException e){
                    sb.append("Error: Invalid data.").append(System.lineSeparator());
                }
            }
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public String findAnomalyWithMostVictims() {
        try {
            return this.fileParser.write(this.anomalyService.findMostAffectingAnomalies(), "");
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String exportAnomaliesOrdered() {
        try {
            return this.xmlParser.write(this.anomalyService.finaAllAnomalies(), "");
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
}
