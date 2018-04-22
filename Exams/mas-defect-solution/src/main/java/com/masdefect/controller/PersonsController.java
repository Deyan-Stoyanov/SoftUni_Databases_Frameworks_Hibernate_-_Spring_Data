package com.masdefect.controller;

import com.masdefect.domain.dto.json.PersonExportJSONDto;
import com.masdefect.domain.dto.json.PersonImportJSONDto;
import com.masdefect.parser.interfaces.FileParser;
import com.masdefect.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller
public class PersonsController {
    private final FileParser fileParser;
    private final PersonService personService;

    @Autowired
    public PersonsController(@Qualifier(value = "JSONParser") FileParser fileParser, PersonService personService) {
        this.fileParser = fileParser;
        this.personService = personService;
    }

    public String importDataFromJSON(String fileContent){
        StringBuilder sb = new StringBuilder();
        try {
            PersonImportJSONDto[] dtos = this.fileParser.read(PersonImportJSONDto[].class, fileContent);
            for (PersonImportJSONDto s:dtos) {
                try {
                    this.personService.create(s);
                    sb.append(String.format("Successfully imported Person %s.%n", s.getName()));
                }catch (IllegalArgumentException e){
                    sb.append("Error: Invalid data.").append(System.lineSeparator());
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return sb.toString();
    }
}
