package org.softuni.mostwanted.controlers;

import org.softuni.mostwanted.io.interfaces.ConsoleIO;
import org.softuni.mostwanted.io.interfaces.FileIO;
import org.softuni.mostwanted.model.dto.importDtos.xml.RaceEntryXmlImportDto;
import org.softuni.mostwanted.model.dto.importDtos.xml.RaceEntryXmlWrapper;
import org.softuni.mostwanted.parser.interfaces.Parser;
import org.softuni.mostwanted.service.interfaces.RaceEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class RaceEntryController {
    private final FileIO fileIO;
    private final Parser parser;
    private final ConsoleIO consoleIO;
    private final RaceEntryService raceEntryService;

    @Autowired
    public RaceEntryController(FileIO fileIO, @Qualifier(value = "XMLParser") Parser parser, ConsoleIO consoleIO, RaceEntryService raceEntryService) {
        this.fileIO = fileIO;
        this.parser = parser;
        this.consoleIO = consoleIO;
        this.raceEntryService = raceEntryService;
    }

    public void importDataFromXML(String xmlContent){
        try{
            RaceEntryXmlWrapper wrapper = this.parser.read(RaceEntryXmlWrapper.class, this.fileIO.read(xmlContent));
            for (RaceEntryXmlImportDto dto:wrapper.getRaceEntries()) {
                try {
                    this.raceEntryService.create(dto);
                } catch (IllegalArgumentException ie){
                    consoleIO.write("Error: Incorrect Data!");
                }
            }
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
    }

}
