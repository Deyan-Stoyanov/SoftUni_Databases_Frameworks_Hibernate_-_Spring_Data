package app.exam.controller;

import app.exam.domain.dto.json.EmployeeJSONImportDTO;
import app.exam.parser.interfaces.Parser;
import app.exam.service.api.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class EmployeesController {
    private final Parser jsonParser;
    private final EmployeeService employeeService;

    @Autowired
    public EmployeesController(@Qualifier(value = "JSONParser") Parser jsonParser, EmployeeService employeeService) {
        this.jsonParser = jsonParser;
        this.employeeService = employeeService;
    }

    public String importDataFromJSON(String jsonContent){
        StringBuilder sb = new StringBuilder();
        try {
            EmployeeJSONImportDTO[] dtos = this.jsonParser.read(EmployeeJSONImportDTO[].class, jsonContent);
            for (EmployeeJSONImportDTO employee:dtos) {
                try {
                    this.employeeService.create(employee);
                    sb.append(String.format("Record %s successfully imported.%n",employee.getName()));
                }catch (IllegalArgumentException e){
                    sb.append("Error: Invalid data.").append(System.lineSeparator());
                }
            }
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
