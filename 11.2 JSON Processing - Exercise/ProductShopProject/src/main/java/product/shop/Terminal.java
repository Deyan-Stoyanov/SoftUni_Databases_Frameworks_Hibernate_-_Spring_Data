package product.shop;

import com.google.gson.Gson;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import product.shop.model.dtos.bindings.ProductCreateBindingModel;
import product.shop.model.dtos.bindings.UserCreateBindingModel;
import product.shop.model.dtos.views.CategoryViewModel;
import product.shop.model.dtos.views.ProductViewModel;
import product.shop.model.dtos.views.UserViewModel;
import product.shop.model.dtos.views.UserWrapper;
import product.shop.model.entity.Category;
import product.shop.model.entity.User;
import product.shop.service.CategoryService;
import product.shop.service.ProductService;
import product.shop.service.UserService;

import javax.transaction.Transactional;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Random;

@Component
@Transactional
public class Terminal implements CommandLineRunner {

    private static final String USER_JSON_FILE_PATH = "/input/users.json";
    private static final String PRODUCT_JSON_FILE_PATH = "/input/products.json";
    private static final String CATEGORY_JSON_FILE_PATH = "/input/categories.json";

    private final UserService userService;
    private final CategoryService categoryService;
    private final ProductService productService;
    private final Gson gson;

    @Autowired
    public Terminal(UserService userService, CategoryService categoryService, ProductService productService, Gson gson) {
        this.userService = userService;
        this.categoryService = categoryService;
        this.productService = productService;
        this.gson = gson;
    }

    @Override
    public void run(String... args) throws Exception {
//        importUsers();
//        importCategories();
//        this.seedProducts();
//        this.extractProductsWithoutBuyers();
//        extractSoldProducts();
//        getCategoriesByProducts();
        getAllProductAndCustomerData();
    }

    private void getAllProductAndCustomerData() throws IOException {
        UserWrapper wrapper = this.userService.getAllUserProductData();
        String jsonOutput = this.gson.toJson(wrapper, UserWrapper.class);
        this.writeFile("/output/users-and-products.json", jsonOutput);
    }

    private void getCategoriesByProducts() throws IOException {
        List<CategoryViewModel> models = this.categoryService.getCategoriesByTotalProducts();
        Type listType = new TypeToken<List<CategoryViewModel>>(){}.getType();
        String jsonOutput = this.gson.toJson(models, listType);
        this.writeFile("/output/categories-and-products.json", jsonOutput);
    }

    private void extractSoldProducts() throws IOException {
        List<UserViewModel> users = this.userService.getAllBySoldItems();
        Type listType = new TypeToken<List<UserViewModel>>(){}.getType();
        String jsonOutput = this.gson.toJson(users, listType);
        this.writeFile("/output/users-and-sold-products.json", jsonOutput);
    }

    private void extractProductsWithoutBuyers() throws IOException {
        List<ProductViewModel> products = this.productService.getAllByRangeWithoutBuyer(new BigDecimal(500), new BigDecimal(1000));
        Type listType = new TypeToken<List<ProductViewModel>>(){}.getType();
        String jsonOutput = this.gson.toJson(products, listType);
        this.writeFile("/output/products-in-range.json", jsonOutput);
    }

    private void writeFile(String fileName, String source) throws IOException {
        String fullPath = System.getProperty("user.dir") + "/src/main/resources";
        FileWriter writer = new FileWriter(new File(fullPath + fileName));
        writer.write(source);
        writer.flush();
    }


    private void seedProducts() throws IOException {
        InputStream productStream = this.loadData(PRODUCT_JSON_FILE_PATH);
        String loaded = readAllData(productStream);
        Type listType = new TypeToken<List<ProductCreateBindingModel>>() {
        }.getType();
        List<ProductCreateBindingModel> products = this.gson.fromJson(loaded, listType);
        products.forEach(this::randomizeData);
        this.productService.save(products);
    }

    private void importCategories() throws IOException {
        InputStream categoryStream = this.loadData(CATEGORY_JSON_FILE_PATH);
        String categoryData = readAllData(categoryStream);
        Type listType = new TypeToken<List<Category>>(){}.getType();
        List<Category> categories = this.gson.fromJson(categoryData, listType);
        this.categoryService.save(categories);
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

    private void importUsers() throws IOException {
        InputStream usersStream = this.loadData(USER_JSON_FILE_PATH);
        String userData =  readAllData(usersStream);
        Type listType = new TypeToken<List<UserCreateBindingModel>>(){}.getType();
        List<UserCreateBindingModel> users = this.gson.fromJson(userData,listType);
        this.userService.save(users);
    }

    private InputStream loadData(String filePath){
        return Terminal.class.getResourceAsStream(filePath);
    }

    private String readAllData(InputStream stream) throws IOException {
        return StreamUtils.copyToString(stream, Charset.defaultCharset());
    }
}
