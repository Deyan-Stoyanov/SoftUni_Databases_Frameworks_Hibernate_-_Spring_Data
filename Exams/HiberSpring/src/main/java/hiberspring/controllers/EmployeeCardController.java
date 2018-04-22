package hiberspring.controllers;

import hiberspring.constants.MessageConstants;
import hiberspring.dtos.imports.EmployeeCardsImportFromJsonDto;
import hiberspring.io.interfaces.FileIO;
import hiberspring.parser.interfaces.Parser;
import hiberspring.services.EmployeeCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class EmployeeCardController {
    private static final String EMPLOYEE_CARDS_JSON_INPUT_PATH = "/json/input/employee_cards.json";
    private static final String EMPLOYEE_CARDS_UNUSED_JSON_OUTPUT_PATH = "/json/output/free_cards.json";

    private final Parser jsonParser;
    private final FileIO fileIO;
    private final EmployeeCardService employeeCardService;

    @Autowired
    public EmployeeCardController(@Qualifier(value = "JSONParser") Parser jsonParser, FileIO fileIO, EmployeeCardService employeeCardService) {
        this.jsonParser = jsonParser;
        this.fileIO = fileIO;
        this.employeeCardService = employeeCardService;
    }


    public void importEmployeeCards() {
        try {
               EmployeeCardsImportFromJsonDto[] dtos = this.jsonParser.read(EmployeeCardsImportFromJsonDto[].class, this.fileIO.read(EMPLOYEE_CARDS_JSON_INPUT_PATH));
            for (EmployeeCardsImportFromJsonDto dto:dtos) {
                try {
                    this.employeeCardService.createOne(dto);
                    System.out.printf(MessageConstants.SUCCESSFULLY_IMPORTED_ENTITY_MESSAGE, dto.getNumber());
                    System.out.println();
                }catch (IllegalArgumentException ie){
                    System.out.println(MessageConstants.INVALID_INPUT_DATA_MESSAGE);
                }
            }
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
    }

    public void exportUnusedCards() {
        try {
            this.fileIO.write(this.jsonParser.write(this.employeeCardService.getUnusedCards()), EMPLOYEE_CARDS_UNUSED_JSON_OUTPUT_PATH);
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
    }
}
