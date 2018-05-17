package org.softuni.mostwanted.controlers;

import org.softuni.mostwanted.Config;
import org.softuni.mostwanted.io.interfaces.ConsoleIO;
import org.softuni.mostwanted.io.interfaces.FileIO;
import org.softuni.mostwanted.model.dto.importDtos.json.TownJSONImportDto;
import org.softuni.mostwanted.parser.interfaces.Parser;
import org.softuni.mostwanted.service.interfaces.TownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class TownController {
    private final TownService townService;
    private final FileIO fileIO;
    private final Parser jsonParser;
    private final Parser xmlParser;
    private final ConsoleIO consoleIO;

    @Autowired
    public TownController(TownService townService, FileIO fileIO, @Qualifier(value = "JSONParser") Parser parser, @Qualifier(value = "XMLParser") Parser xmlParser, ConsoleIO consoleIO) {
        this.townService = townService;
        this.fileIO = fileIO;
        this.jsonParser = parser;
        this.xmlParser = xmlParser;
        this.consoleIO = consoleIO;
    }

    public void importDataFromJSON(String jsonContent){
        try{
            TownJSONImportDto[] dtos = this.jsonParser.read(TownJSONImportDto[].class, this.fileIO.read(jsonContent));
            for (TownJSONImportDto dto:dtos) {
                try {
                    this.townService.create(dto);
                    consoleIO.write(String.format("Succesfully imported Town - %s", dto.getName()));
                } catch (IllegalArgumentException ie){
                    consoleIO.write("Error: Incorrect Data!");
                }
            }
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
    }

    public void exportDataToJSON(){
        try {
            String jsonContent = this.jsonParser.write(this.townService.allTownsWithRacers());
            this.fileIO.write(jsonContent, Config.TOWNS_WITH_RACERS_JSON);
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
    }
}
