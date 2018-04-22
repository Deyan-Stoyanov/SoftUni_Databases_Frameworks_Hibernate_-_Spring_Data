package hiberspring.controllers;

import hiberspring.constants.MessageConstants;
import hiberspring.dtos.imports.employee.EmployeeImportFromXmlDto;
import hiberspring.dtos.imports.employee.EmployeesImportFromXmlDto;
import hiberspring.io.interfaces.FileIO;
import hiberspring.parser.interfaces.Parser;
import hiberspring.services.EmployeeService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class EmployeeController {
    private static final String EMPLOYEES_XML_INPUT_PATH = "/xml/input/employees.xml";
    private static final String EMPLOYEES_PRODUCTIVE_JSON_OUTPUT_PATH = "/json/output/productive-employees.json";

    private final FileIO fileIO;
    private final EmployeeService employeeService;
    private final Parser xmlParser;
    private final Parser jsonParser;

    public EmployeeController(FileIO fileIO, EmployeeService employeeService, @Qualifier(value = "XMLParser") Parser xmlParser, @Qualifier(value = "JSONParser") Parser jsonParser) {
        this.fileIO = fileIO;
        this.employeeService = employeeService;
        this.xmlParser = xmlParser;
        this.jsonParser = jsonParser;
    }

    public void importEmployees() {
        try{
            EmployeesImportFromXmlDto wrapper = this.xmlParser.read(EmployeesImportFromXmlDto.class, this.fileIO.read(EMPLOYEES_XML_INPUT_PATH));
            for (EmployeeImportFromXmlDto dto:wrapper.getEmployees()) {
                try {
                    this.employeeService.createOne(dto);
                    System.out.printf(MessageConstants.SUCCESSFULLY_IMPORTED_ENTITY_MESSAGE, dto.getFirstName(), dto.getLastName());
                    System.out.println();
                } catch (IllegalArgumentException ie){
                    System.out.println(MessageConstants.INVALID_INPUT_DATA_MESSAGE);
                }
            }
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
    }


    public void exportProductiveEmployees() {
        try {
            this.fileIO.write(this.jsonParser.write(this.employeeService.getProductiveEmployees()), EMPLOYEES_PRODUCTIVE_JSON_OUTPUT_PATH);
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
    }
}
