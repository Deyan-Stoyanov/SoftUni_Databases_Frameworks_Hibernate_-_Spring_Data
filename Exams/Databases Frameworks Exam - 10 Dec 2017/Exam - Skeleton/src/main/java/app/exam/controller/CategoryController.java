package app.exam.controller;

import app.exam.parser.interfaces.Parser;
import app.exam.service.api.CategoryService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

@Controller
public class CategoryController {
    private final CategoryService categoryService;
    private final Parser xmlParser;

    public CategoryController(CategoryService categoryService, @Qualifier(value = "XMLParser") Parser xmlParser) {
        this.categoryService = categoryService;
        this.xmlParser = xmlParser;
    }

    public String getCategoriesWithMostPopularItemsSorted(List<String> categoryNames){
        try {
            return this.xmlParser.write(this.categoryService.getCategoriesWithMostPopularItems(categoryNames));
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
}
