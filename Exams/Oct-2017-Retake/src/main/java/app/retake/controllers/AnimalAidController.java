package app.retake.controllers;

import app.retake.Config;
import app.retake.domain.dto.AnimalAidJSONImportDTO;
import app.retake.io.api.FileIO;
import app.retake.parser.interfaces.Parser;
import app.retake.services.api.AnimalAidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class AnimalAidController {

    private final AnimalAidService animalAidService;
    private final FileIO fileIO;
    private final Parser jsonParser;

    @Autowired
    public AnimalAidController(AnimalAidService animalAidRepository, FileIO fileIO, @Qualifier(value = "JSONParser") Parser jsonParser) {
        this.animalAidService = animalAidRepository;
        this.fileIO = fileIO;
        this.jsonParser = jsonParser;
    }

    public String importDataFromJSON(String jsonContent) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            AnimalAidJSONImportDTO[] dtos = this.jsonParser.read(AnimalAidJSONImportDTO[].class, this.fileIO.read(jsonContent));
            for (AnimalAidJSONImportDTO dto:dtos) {
                try {
                    this.animalAidService.create(dto);
                    stringBuilder.append(String.format("Record %s successfully imported.%n", dto.getName()));
                } catch (IllegalArgumentException ie){
                    stringBuilder.append(String.format("Error: Invalid data.%n"));
                }
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}
