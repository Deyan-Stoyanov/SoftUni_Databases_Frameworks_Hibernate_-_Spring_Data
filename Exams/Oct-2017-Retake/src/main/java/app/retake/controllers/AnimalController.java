package app.retake.controllers;

import app.retake.domain.dto.AnimalJSONImportDTO;
import app.retake.io.api.FileIO;
import app.retake.parser.interfaces.Parser;
import app.retake.services.api.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.text.ParseException;

@Controller

public class AnimalController {
    private final AnimalService animalService;
    private final FileIO fileIO;
    private final Parser jsonParser;

    @Autowired
    public AnimalController(AnimalService animalService, FileIO fileIO, @Qualifier(value = "JSONParser") Parser jsonParser) {
        this.animalService = animalService;
        this.fileIO = fileIO;
        this.jsonParser = jsonParser;
    }

    public String importDataFromJSON(String jsonContent) {
        StringBuilder sb = new StringBuilder();
        try {
            AnimalJSONImportDTO[] dtos = this.jsonParser.read(AnimalJSONImportDTO[].class, this.fileIO.read(jsonContent));
            for (AnimalJSONImportDTO dto:dtos) {
                try {
                    this.animalService.create(dto);
                    sb.append(String.format("Record %s Passport №: %s successfully imported.%n", dto.getName(), dto.getPassport().getSerialNumber()));
                }catch (IllegalArgumentException | ParseException ie){
                    sb.append(String.format("Error: Invalid data.%n"));
                }
            }
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public String exportAnimalsByOwnerPhoneNumber(String phoneNumber) {
        try {
            return this.jsonParser.write(this.animalService.findByOwnerPhoneNumber(phoneNumber));
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
}
