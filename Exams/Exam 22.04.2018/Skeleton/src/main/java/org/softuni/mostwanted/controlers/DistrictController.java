package org.softuni.mostwanted.controlers;

import org.softuni.mostwanted.io.interfaces.ConsoleIO;
import org.softuni.mostwanted.io.interfaces.FileIO;
import org.softuni.mostwanted.model.dto.importDtos.json.DistrictJSONImportDto;
import org.softuni.mostwanted.parser.interfaces.Parser;
import org.softuni.mostwanted.service.interfaces.DistrictService;
import org.softuni.mostwanted.service.interfaces.TownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class DistrictController {

    private final TownService townService;
    private final DistrictService districtService;
    private final FileIO fileIO;
    private final Parser parser;
    private final ConsoleIO consoleIO;

    @Autowired
    public DistrictController(TownService townService, DistrictService districtService, FileIO fileIO, @Qualifier(value = "JSONParser") Parser parser, ConsoleIO consoleIO) {
        this.townService = townService;
        this.districtService = districtService;
        this.fileIO = fileIO;
        this.parser = parser;
        this.consoleIO = consoleIO;
    }

    public void importDataFromJSON(String jsonContent){
        try{
            DistrictJSONImportDto[] dtos = this.parser.read(DistrictJSONImportDto[].class, this.fileIO.read(jsonContent));
            for (DistrictJSONImportDto dto:dtos) {
                try {
                    this.districtService.create(dto);
                    consoleIO.write(String.format("Succesfully imported District - %s", dto.getName()));
                } catch (IllegalArgumentException ie){
                    consoleIO.write("Error: Incorrect Data!");
                }
            }
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
    }
}
