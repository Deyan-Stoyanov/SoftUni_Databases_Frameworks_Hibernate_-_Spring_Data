package car.dealer;

import car.dealer.model.dto.*;
import car.dealer.model.entity.*;
import car.dealer.service.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import javax.transaction.Transactional;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Random;

@Component
@Transactional
public class Runner implements CommandLineRunner {
    private static final String CARS_JSON_INPUT = "/input/cars.json";
    private static final String CUSTOMERS_JSON_INPUT = "/input/customers.json";
    private static final String PARTS_JSON_INPUT = "/input/parts.json";
    private static final String SUPPLIERS_JSON_INPUT = "/input/suppliers.json";

    private final ModelMapper modelMapper;
    private final Gson gson;

    private final CarService carService;
    private final SupplierService supplierService;
    private final CustomerService customerService;
    private final PartService partService;
    private final SaleService saleService;

    @Autowired
    public Runner(CarService carService, SupplierService supplierService, CustomerService customerService, PartService partService, SaleService saleService) {
        this.carService = carService;
        this.supplierService = supplierService;
        this.customerService = customerService;
        this.partService = partService;
        this.saleService = saleService;
        this.modelMapper = new ModelMapper();
        this.gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .create();
    }

    @Override
    public void run(String... args) throws Exception {
//        seedSuppliers();
//        seedParts();
//        seedCars();
//        seedCustomers();
//        seedSales();
//        partsAndCars();
//        orderedCustomers();
//        getToyotaCars();
//        getLocalSuppliers();
//        getAllCarsAndParts();
//        getAllCustomersAndSpentMoney();
        getAllSaleData();
    }

    private void getAllSaleData() throws IOException {
        List<SaleViewModel> models = this.saleService.salesWithCarAndCustomerData();
        Type listType = new TypeToken<List<SaleViewModel>>(){}.getType();
        String jsonOutput = this.gson.toJson(models, listType);
        this.writeFile("/output/saleCustomerCarData.json", jsonOutput);
    }

    private void getAllCustomersAndSpentMoney() throws IOException {
        List<CustomerViewModel> models = this.saleService.allCustomersAndSpentMoney();
        Type listType = new TypeToken<List<CustomerViewModel>>(){}.getType();
        String jsonOutput = this.gson.toJson(models, listType);
        this.writeFile("/output/customersAndSpentMoney.json", jsonOutput);

    }

    private void getAllCarsAndParts() throws IOException {
        List<CarViewModel> models = this.carService.findAllCarsAndParts();
        Type listType = new TypeToken<List<CarViewModel>>(){}.getType();
        String jsonOutput = this.gson.toJson(models, listType);
        this.writeFile("/output/carParts.json", jsonOutput);
    }

    private void getLocalSuppliers() throws IOException {
        List<SupplierDto> models = this.partService.localSuppliers();
        Type listType = new TypeToken<List<SupplierDto>>(){}.getType();
        String jsonOutput = this.gson.toJson(models, listType);
        this.writeFile("/output/localSuppliers.json", jsonOutput);
    }

    private void getToyotaCars() throws IOException {
        List<CarDto> cars = this.carService.allToyotaCars();
        Type listType = new TypeToken<List<CarDto>>(){}.getType();
        String jsonOutput = this.gson.toJson(cars, listType);
        this.writeFile("/output/toyotaCars.json", jsonOutput);
    }

    private void orderedCustomers() throws IOException {
        List<Customer> customers = this.customerService.getAllCustomersSorted();
        Type listType = new TypeToken<List<Customer>>(){}.getType();
        String jsonOutput = this.gson.toJson(customers, listType);
        this.writeFile("/output/orderedCustomers.json", jsonOutput);
    }

    private void partsAndCars(){
        List<Part> allParts = this.partService.getAllParts();
        Random random = new Random();
        for (Part p:allParts) {
            Integer randomNum = random.nextInt(357);
            if(this.carService.getById((long) randomNum).isPresent()){
                p.getCars().add(this.carService.getById((long)randomNum).get());
                if(p != null){
                    this.partService.save(p);
                }
            }
        }
    }
    private void seedSales(){
        Random carRandom = new Random();
        Random customerRandom = new Random();
        Double[] discounts = new Double[]{0.5, 0.6, 0.7, 0.8, 0.85, 0.9, 0.95, 1.0};
        Random discountRandom = new Random();
        for (int i = 0; i < 100; i++) {
            Sale sale = new Sale();
            Integer randomNumber1 = customerRandom.nextInt(29);
            if(this.customerService.getById((long)randomNumber1).isPresent()){
                sale.setCustomer(this.customerService.getById((long)randomNumber1).get());
            }
            Integer randomNumber2 = carRandom.nextInt(357);
            if(this.carService.getById((long)randomNumber2).isPresent()){
                sale.setCar(this.carService.getById((long)randomNumber2).get());
            }
            sale.setDiscount(discounts[discountRandom.nextInt(6)]);
            this.saleService.save(sale);
        }
    }

    private void seedCustomers() throws IOException {
        InputStream custStream = this.loadData(CUSTOMERS_JSON_INPUT);
        String custData = readAllData(custStream);
        Type listType = new TypeToken<List<Customer>>(){}.getType();
        List<Customer> customers = this.gson.fromJson(custData, listType);
        this.customerService.save(customers);
    }
    private void seedCars() throws IOException {
        InputStream carsStream = this.loadData(CARS_JSON_INPUT);
        String carsData = readAllData(carsStream);
        Type listType = new TypeToken<List<Car>>(){}.getType();
        List<Car> cars = this.gson.fromJson(carsData, listType);
        Random random = new Random();
        for (Car c:cars) {
            for (int i = 0; i < 15; i++) {
                Long randomNumber = (long)random.nextInt(130);
                if(this.partService.getById(randomNumber).isPresent()){
                    Part p = this.partService.getById(randomNumber).get();
                    c.getParts().add(p);
                }
            }
        }
        this.carService.save(cars);
    }

    private void seedParts() throws IOException {
        InputStream partsStream = this.loadData(PARTS_JSON_INPUT);
        String partsData =  readAllData(partsStream);
        Type listType = new TypeToken<List<Part>>(){}.getType();
        List<Part> parts = this.gson.fromJson(partsData,listType);
        Random random = new Random();
        for (Part p:parts) {
            Long thisRandom = (long) random.nextInt(30);
            if( this.supplierService.getById(thisRandom).isPresent()){
                Supplier supplier = this.supplierService.getById(thisRandom).get();
                p.setSupplier(supplier);
            }
        }
        this.partService.save(parts);
    }

    private void seedSuppliers() throws IOException {
        InputStream supplierStream = this.loadData(SUPPLIERS_JSON_INPUT);
        String supplierData =  readAllData(supplierStream);
        Type listType = new TypeToken<List<Supplier>>(){}.getType();
        List<Supplier> suppliers = this.gson.fromJson(supplierData,listType);
        this.supplierService.save(suppliers);
    }

    private void writeFile(String fileName, String source) throws IOException {
        String fullPath = System.getProperty("user.dir") + "/src/main/resources";
        FileWriter writer = new FileWriter(new File(fullPath + fileName));
        writer.write(source);
        writer.flush();
    }

    private InputStream loadData(String filePath){
        return Runner.class.getResourceAsStream(filePath);
    }

    private String readAllData(InputStream stream) throws IOException {
        return StreamUtils.copyToString(stream, Charset.defaultCharset());
    }
}
