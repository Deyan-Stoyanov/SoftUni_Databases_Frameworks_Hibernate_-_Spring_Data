package app.exam.controller;

import app.exam.domain.dto.json.ItemJSONImportDTO;
import app.exam.parser.interfaces.Parser;
import app.exam.service.api.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class ItemsController {
    private final Parser jsonParser;
    private final ItemsService itemsService;

    @Autowired
    public ItemsController(@Qualifier(value = "JSONParser") Parser jsonParser, ItemsService itemsService) {
        this.jsonParser = jsonParser;
        this.itemsService = itemsService;
    }

    public String importDataFromJSON(String jsonContent) {
        StringBuilder sb = new StringBuilder();
        try {
            ItemJSONImportDTO[] dtos = this.jsonParser.read(ItemJSONImportDTO[].class, jsonContent);
            for (ItemJSONImportDTO dto:dtos) {
                try {
                    this.itemsService.create(dto);
                    sb.append(String.format("Record %s successfully imported.%n",dto.getName()));
                }catch (IllegalArgumentException ie){
                    sb.append("Error: Invalid data.").append(System.lineSeparator());
                }
            }
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
