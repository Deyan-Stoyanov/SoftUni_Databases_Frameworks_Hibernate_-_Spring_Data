package org.softuni.mostwanted.controlers;

import org.softuni.mostwanted.io.interfaces.ConsoleIO;
import org.softuni.mostwanted.io.interfaces.FileIO;
import org.softuni.mostwanted.model.dto.importDtos.xml.RaceXMLImportDto;
import org.softuni.mostwanted.model.dto.importDtos.xml.RaceXMLWrapper;
import org.softuni.mostwanted.parser.interfaces.Parser;
import org.softuni.mostwanted.service.interfaces.RaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class RaceController {
    private final FileIO fileIO;
    private final Parser parser;
    private final ConsoleIO consoleIO;
    private final RaceService raceService;

    @Autowired
    public RaceController(FileIO fileIO, @Qualifier(value = "XMLParser") Parser parser, ConsoleIO consoleIO, RaceService raceService) {
        this.fileIO = fileIO;
        this.parser = parser;
        this.consoleIO = consoleIO;
        this.raceService = raceService;
    }
    public void importDataFromXML(String xmlContent){
        try{
            RaceXMLWrapper wrapper = this.parser.read(RaceXMLWrapper.class, this.fileIO.read(xmlContent));
            for (RaceXMLImportDto dto:wrapper.getRaces()) {
                try {
                    this.raceService.create(dto);
                } catch (IllegalArgumentException ie){
                    consoleIO.write("Error: Incorrect Data!");
                }
            }
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
    }

}
