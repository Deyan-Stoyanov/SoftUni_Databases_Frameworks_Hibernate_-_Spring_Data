package app.retake.controllers;

import app.retake.domain.dto.VetWrapperXMLImportDTO;
import app.retake.domain.dto.VetXMLImportDTO;
import app.retake.io.api.FileIO;
import app.retake.parser.interfaces.Parser;
import app.retake.services.api.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller

public class VetController {

    private final VetService vetService;
    private final FileIO fileIO;
    private final Parser xmlParser;

    @Autowired
    public VetController(VetService vetService, FileIO fileIO, @Qualifier(value = "XMLParser") Parser xmlParser) {
        this.vetService = vetService;
        this.fileIO = fileIO;
        this.xmlParser = xmlParser;
    }

    public String importDataFromXML(String xmlContent){
        StringBuilder stringBuilder = new StringBuilder();
        try {
            VetWrapperXMLImportDTO wrapper = this.xmlParser.read(VetWrapperXMLImportDTO.class, this.fileIO.read(xmlContent));
            for (VetXMLImportDTO dto:wrapper.getVets()) {
                try {
                    this.vetService.create(dto);
                    stringBuilder.append(String.format("Record %s successfully imported.%n", dto.getName()));
                } catch (IllegalArgumentException ie){
                    stringBuilder.append(String.format("Error: Invalid data.%n"));
                }
            }
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}
