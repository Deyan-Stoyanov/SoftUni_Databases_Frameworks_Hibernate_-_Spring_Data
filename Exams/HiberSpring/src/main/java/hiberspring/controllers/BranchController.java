package hiberspring.controllers;

import hiberspring.constants.MessageConstants;
import hiberspring.dtos.imports.BranchImportFromJsonDto;
import hiberspring.io.interfaces.FileIO;
import hiberspring.parser.interfaces.Parser;
import hiberspring.services.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class BranchController {
    private static final String BRANCHES_JSON_INPUT_PATH = "/json/input/branches.json";
    private static final String TOP_BRANCHES_XML_OUTPUT_PATH = "/xml/output/top-branches.xml";

    private final BranchService branchService;
    private final Parser jsonParser;
    private final Parser xmlParser;
    private final FileIO fileIO;

    @Autowired
    public BranchController(BranchService branchService, @Qualifier(value = "JSONParser") Parser jsonParser, @Qualifier(value = "XMLParser") Parser xmlParser, FileIO fileIO) {
        this.branchService = branchService;
        this.jsonParser = jsonParser;
        this.xmlParser = xmlParser;
        this.fileIO = fileIO;
    }


    public void importBranches() {
        try {
            BranchImportFromJsonDto[] dtos = this.jsonParser.read(BranchImportFromJsonDto[].class, this.fileIO.read(BRANCHES_JSON_INPUT_PATH));
            for (BranchImportFromJsonDto dto:dtos) {
                try {
                    this.branchService.createOne(dto);
                    System.out.printf(MessageConstants.SUCCESSFULLY_IMPORTED_ENTITY_MESSAGE, dto.getName(), dto.getName());
                    System.out.println();
                } catch (IllegalArgumentException ie){
                    System.out.println(MessageConstants.INVALID_INPUT_DATA_MESSAGE);
                }
            }
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
    }

    public void exportBranches() {
        try {
            this.fileIO.write(this.xmlParser.write(this.branchService.getTopBranches()), TOP_BRANCHES_XML_OUTPUT_PATH);
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
    }
}
