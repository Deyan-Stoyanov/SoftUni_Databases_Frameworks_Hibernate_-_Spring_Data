package org.softuni.mostwanted.controlers;

import org.softuni.mostwanted.io.interfaces.ConsoleIO;
import org.softuni.mostwanted.io.interfaces.FileIO;
import org.softuni.mostwanted.model.dto.importDtos.json.CarJSONImportDto;
import org.softuni.mostwanted.parser.interfaces.Parser;
import org.softuni.mostwanted.service.interfaces.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class CarController {
    private final FileIO fileIO;
    private final Parser parser;
    private final ConsoleIO consoleIO;
    private final CarService carService;

    @Autowired
    public CarController(FileIO fileIO, @Qualifier(value = "JSONParser") Parser parser, ConsoleIO consoleIO, CarService carService) {
        this.fileIO = fileIO;
        this.parser = parser;
        this.consoleIO = consoleIO;
        this.carService = carService;
    }

    public void importDataFromJSON(String jsonContent){
        try{
            CarJSONImportDto[] dtos = this.parser.read(CarJSONImportDto[].class, this.fileIO.read(jsonContent));
            for (CarJSONImportDto dto:dtos) {
                try {
                    this.carService.create(dto);
                    consoleIO.write(String.format("Succesfully imported Car - %s %s @ %d", dto.getBrand(), dto.getModel(), dto.getYearOfProduction()));
                } catch (IllegalArgumentException ie){
                    consoleIO.write("Error: Incorrect Data!");
                }
            }
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
    }
}
