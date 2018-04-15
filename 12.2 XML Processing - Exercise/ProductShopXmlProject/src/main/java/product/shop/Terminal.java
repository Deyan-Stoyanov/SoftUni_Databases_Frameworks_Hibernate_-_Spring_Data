package product.shop;

import com.google.gson.Gson;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import product.shop.io.parser.XmlParser;
import product.shop.io.reader.Reader;
import product.shop.io.writer.Writer;
import product.shop.model.dtos.bindings.*;
import product.shop.model.dtos.views.*;
import product.shop.model.entity.Category;
import product.shop.service.CategoryService;
import product.shop.service.ProductService;
import product.shop.service.UserService;

import javax.transaction.Transactional;
import javax.xml.bind.JAXBException;
import java.io.*;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Random;

@Component
@Transactional
public class Terminal implements CommandLineRunner {

    private static final String USER_XML_FILE_PATH = "/input/users.xml";
    private static final String PRODUCT_XML_FILE_PATH = "/input/products.xml";
    private static final String CATEGORY_XML_FILE_PATH = "/input/categories.xml";

    private static final String PRODUCTS_WITHOUT_BUYERS_XML_FILE_PATH = "/output/prodicts-without-buyers.xml";
    private static final String SOLD_PRODUCTS_XML_FILE_PATH = "/output/sold-products.xml";

    private final UserService userService;
    private final CategoryService categoryService;
    private final ProductService productService;
    private final Gson gson;

    private final Reader reader;
    private final Writer writer;
    private final XmlParser xmlParser;

    @Autowired
    public Terminal(UserService userService, CategoryService categoryService, ProductService productService, Gson gson, Reader reader, Writer writer, XmlParser xmlParser) {
        this.userService = userService;
        this.categoryService = categoryService;
        this.productService = productService;
        this.gson = gson;
        this.reader = reader;
        this.writer = writer;
        this.xmlParser = xmlParser;
    }

    @Override
    public void run(String... args) throws Exception {
//
//        importUsers();
//        importCategories();
//        seedProducts();

//        this.extractProductsWithoutBuyers();
//        extractSoldProducts();
//        getCategoriesByProducts();
        getAllUserData();
    }

    private void getAllUserData() throws JAXBException, IOException {
        UsersDataWrapper wrapper = this.userService.getUserData();
        String xmlContent = this.xmlParser.serialize(wrapper);
        this.writeFile("/output/users-and-products.xml", xmlContent);
    }

    private void getCategoriesByProducts() throws IOException, JAXBException {
        List<CategoryViewModel> models = this.categoryService.getCategoriesByTotalProducts();
        CategoriesWrapper wrapper = new CategoriesWrapper(models);
        String xmlContent = this.xmlParser.serialize(wrapper);
        this.writeFile("/output/categories-and-products.xml", xmlContent);
    }

    private void extractSoldProducts() throws IOException, JAXBException {
        List<UserViewModel> users = this.userService.getAllBySoldItems();
        UsersWrapper wrapper = new UsersWrapper(users);
        String xmlContent = this.xmlParser.serialize(wrapper);
        this.writer.writeFile(xmlContent, SOLD_PRODUCTS_XML_FILE_PATH);
    }

    private void extractProductsWithoutBuyers() throws IOException, JAXBException {
        List<ProductViewModel> products = this.productService.getAllByRangeWithoutBuyer(new BigDecimal(500), new BigDecimal(1000));
        ProductInRangeWrapper wrapper = new ProductInRangeWrapper(products);
        String xmlContent = this.xmlParser.serialize(wrapper);
        this.writer.writeFile(xmlContent, PRODUCTS_WITHOUT_BUYERS_XML_FILE_PATH);



//        Type listType = new TypeToken<List<ProductViewModel>>(){}.getType();
//        String jsonOutput = this.gson.toJson(products, listType);
//        this.writeFile("/output/products-in-range.json", jsonOutput);
    }

    private void writeFile(String fileName, String source) throws IOException {
        String fullPath = System.getProperty("user.dir") + "/src/main/resources";
        FileWriter writer = new FileWriter(new File(fullPath + fileName));
        writer.write(source);
        writer.flush();
    }

    private void seedProducts() throws IOException, JAXBException {
        String productsXml = this.reader.readAll(PRODUCT_XML_FILE_PATH);
        ProductCreateWrapper productWrapper = this.xmlParser.deserialize(productsXml, ProductCreateWrapper.class);
        productWrapper.getProducts().forEach(this::randomizeData);
        this.productService.save(productWrapper.getProducts());
    }

    private void importCategories() throws IOException, JAXBException {
        String categoryXml = this.reader.readAll(CATEGORY_XML_FILE_PATH);
        CategoryCreateWrapper categoriesWrapper = this.xmlParser.deserialize(categoryXml, CategoryCreateWrapper.class);
        this.categoryService.save(categoriesWrapper.getCategories());
    }

    private void randomizeData(ProductCreateBindingModel model) {
        Random random = new Random();
        int buyer = random.nextInt(69);
        if (buyer < 56) model.setBuyer(buyer);
        int seller = 0;
        do {
            seller = random.nextInt(56);
            model.setSeller(seller);
        } while (seller == buyer);
        int category = random.nextInt(10);
    }

    private void importUsers() throws IOException, JAXBException {
        String usersXml = this.reader.readAll(USER_XML_FILE_PATH);
        UserCreateWrapper usersWrapper =  this.xmlParser.deserialize(usersXml, UserCreateWrapper.class);
        this.userService.save(usersWrapper.getUserCreateBindingModels());
    }

    private InputStream loadData(String filePath){
        return Terminal.class.getResourceAsStream(filePath);
    }

    private String readAllData(InputStream stream) throws IOException {
        return StreamUtils.copyToString(stream, Charset.defaultCharset());
    }
}
