package hiberspring.controllers;

import hiberspring.constants.MessageConstants;
import hiberspring.dtos.imports.TownImportFromJsonDto;
import hiberspring.io.interfaces.FileIO;
import hiberspring.parser.interfaces.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import hiberspring.services.TownService;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class TownController {
    private static final String TOWNS_JSON_INPUT_PATH = "/json/input/towns.json";
    private static final String TOWNS_XML_OUTPUT_PATH = "/xml/output/towns.xml";

    private final Parser jsonParser;
    private final Parser xmlParser;
    private final TownService townService;
    private final FileIO fileIO;

    @Autowired
    public TownController(@Qualifier(value = "JSONParser") Parser jsonParser, @Qualifier(value = "XMLParser") Parser xmlParser, TownService townService, FileIO fileIO) {
        this.jsonParser = jsonParser;
        this.xmlParser = xmlParser;
        this.townService = townService;
        this.fileIO = fileIO;
    }


    public void importTowns() {
        try {
            TownImportFromJsonDto[] dtos = this.jsonParser.read(TownImportFromJsonDto[].class, this.fileIO.read(TOWNS_JSON_INPUT_PATH));
            for (TownImportFromJsonDto dto:dtos) {
                try{
                    this.townService.createOne(dto);
                    System.out.printf(MessageConstants.SUCCESSFULLY_IMPORTED_ENTITY_MESSAGE,"town", dto.getName());
                    System.out.println();
                }catch (IllegalArgumentException ie){
                    System.out.println(MessageConstants.INVALID_INPUT_DATA_MESSAGE);
                }
            }
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
    }

    public void exportTowns() {
        try {
            this.fileIO.write(this.xmlParser.write(this.townService.getTowns()), TOWNS_XML_OUTPUT_PATH);
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
    }
}
