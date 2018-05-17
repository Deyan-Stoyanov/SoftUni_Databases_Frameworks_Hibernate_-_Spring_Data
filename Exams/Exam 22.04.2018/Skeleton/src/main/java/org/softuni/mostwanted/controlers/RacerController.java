package org.softuni.mostwanted.controlers;

import org.softuni.mostwanted.Config;
import org.softuni.mostwanted.io.interfaces.ConsoleIO;
import org.softuni.mostwanted.io.interfaces.FileIO;
import org.softuni.mostwanted.model.dto.importDtos.json.RacerJSONImportDto;
import org.softuni.mostwanted.parser.interfaces.Parser;
import org.softuni.mostwanted.service.interfaces.RacerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class RacerController {
    private final FileIO fileIO;
    private final Parser jsonParser;
    private final Parser xmlParser;
    private final ConsoleIO consoleIO;
    private final RacerService racerService;

    @Autowired
    public RacerController(FileIO fileIO, @Qualifier(value = "JSONParser") Parser parser, @Qualifier(value = "XMLParser") Parser xmlParser, ConsoleIO consoleIO, RacerService racerService) {
        this.fileIO = fileIO;
        this.jsonParser = parser;
        this.xmlParser = xmlParser;
        this.consoleIO = consoleIO;
        this.racerService = racerService;
    }

    public void importDataFromJSON(String jsonContent){
        try{
            RacerJSONImportDto[] dtos = this.jsonParser.read(RacerJSONImportDto[].class, this.fileIO.read(jsonContent));
            for (RacerJSONImportDto dto:dtos) {
                try {
                    this.racerService.create(dto);
                    consoleIO.write(String.format("Succesfully imported Racer - %s", dto.getName()));
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
            this.fileIO.write(this.jsonParser.write(this.racerService.exportRacersWithCars()), Config.RACERS_WITH_CARS_JSON);
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
    }

    public void exportDataToXML(){
        try {
            this.fileIO.write(this.xmlParser.write(this.racerService.getTheMostWanted()), Config.MOST_WANTED_RACER_XML);
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
    }
}
