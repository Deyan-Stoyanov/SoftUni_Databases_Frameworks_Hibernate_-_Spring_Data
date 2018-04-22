package hiberspring.controllers;

import hiberspring.constants.MessageConstants;
import hiberspring.dtos.imports.product.ProductImportFromXmlDto;
import hiberspring.dtos.imports.product.ProductsImportFromXmlDto;
import hiberspring.io.interfaces.FileIO;
import hiberspring.parser.interfaces.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import hiberspring.services.ProductService;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class ProductController {
    private static final String PRODUCTS_XML_INPUT_PATH = "/xml/input/products.xml";

    private final Parser xmlParser;
    private final FileIO fileIO;
    private final ProductService productService;

    @Autowired
    public ProductController(@Qualifier(value = "XMLParser") Parser xmlParser, FileIO fileIO, ProductService productService) {
        this.xmlParser = xmlParser;
        this.fileIO = fileIO;
        this.productService = productService;
    }

    public void importProducts() {

        try {
            ProductImportFromXmlDto wrapper = this.xmlParser.read(ProductImportFromXmlDto.class, this.fileIO.read(PRODUCTS_XML_INPUT_PATH));
            for (ProductsImportFromXmlDto dto:wrapper.getProducts()) {
                try {
                    this.productService.createOne(dto);
                    System.out.printf(MessageConstants.SUCCESSFULLY_IMPORTED_ENTITY_MESSAGE, dto.getName(), dto.getClients());
                    System.out.println();
                } catch (IllegalArgumentException ie){
                    System.out.println(MessageConstants.INVALID_INPUT_DATA_MESSAGE);
                }
            }
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
    }
}
