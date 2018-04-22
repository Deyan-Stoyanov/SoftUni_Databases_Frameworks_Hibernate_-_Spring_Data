package app.retake.controllers;

import app.retake.domain.dto.ProcedureWrapperXMLImportDTO;
import app.retake.domain.dto.ProcedureXMLImportDTO;
import app.retake.io.api.FileIO;
import app.retake.parser.interfaces.Parser;
import app.retake.services.api.ProcedureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.text.ParseException;

@Controller
public class ProcedureController {

    private final ProcedureService procedureService;
    private final FileIO fileIO;
    private final Parser xmlParser;

    @Autowired
    public ProcedureController(ProcedureService procedureService, FileIO fileIO, @Qualifier(value = "XMLParser") Parser xmlParser) {
        this.procedureService = procedureService;
        this.fileIO = fileIO;
        this.xmlParser = xmlParser;
    }

    public String importDataFromXML(String xmlContent){
        StringBuilder sb = new StringBuilder();
        try{
            ProcedureWrapperXMLImportDTO wrapper = this.xmlParser.read(ProcedureWrapperXMLImportDTO.class, this.fileIO.read(xmlContent));
            for (ProcedureXMLImportDTO dto:wrapper.getProcedures()) {
                try{
                    this.procedureService.create(dto);
                    sb.append("Record successfully imported.").append(System.lineSeparator());
                } catch (ParseException | IllegalArgumentException e) {
                    sb.append("Error: Invalid data.").append(System.lineSeparator());
                }
            }
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public String exportProcedures() throws IOException, JAXBException {
        return this.xmlParser.write(this.procedureService.exportProcedures());
    }
}
