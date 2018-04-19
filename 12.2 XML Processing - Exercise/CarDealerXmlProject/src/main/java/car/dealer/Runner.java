package car.dealer;

import car.dealer.model.dto.exportDtos.*;
import car.dealer.model.dto.importDtos.*;
import car.dealer.model.entity.*;
import car.dealer.model.io.parser.XmlParser;
import car.dealer.model.io.reader.Reader;
import car.dealer.model.io.writer.Writer;
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
import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
@Transactional
public class Runner implements CommandLineRunner {
    private static final String CARS_JSON_INPUT = "/input/cars.xml";
    private static final String CUSTOMERS_JSON_INPUT = "/input/customers.xml";
    private static final String PARTS_JSON_INPUT = "/input/parts.xml";
    private static final String SUPPLIERS_JSON_INPUT = "/input/suppliers.xml";

    private final ModelMapper modelMapper;
    private final Gson gson;

    private final CarService carService;
    private final SupplierService supplierService;
    private final CustomerService customerService;
    private final PartService partService;
    private final SaleService saleService;

    private final Reader reader;
    private final Writer writer;
    private final XmlParser xmlParser;

    @Autowired
    public Runner(CarService carService, SupplierService supplierService, CustomerService customerService, PartService partService, SaleService saleService, Reader reader, Writer writer, XmlParser xmlParser) {
        this.carService = carService;
        this.supplierService = supplierService;
        this.customerService = customerService;
        this.partService = partService;
        this.saleService = saleService;
        this.reader = reader;
        this.writer = writer;
        this.xmlParser = xmlParser;
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
//        orderedCustomers();
//        getToyotaCars();
//        getLocalSuppliers();
//        getAllCarsAndParts();
//        getAllCustomersAndSpentMoney();
        getAllSaleData();
    }

    private void getAllSaleData() throws IOException, JAXBException {
        List<SaleViewModel> models = this.saleService.salesWithCarAndCustomerData();
        SaleExportWrapper wrapper = new SaleExportWrapper(models);
        String xmlContent = this.xmlParser.serialize(wrapper);
        this.writer.writeFile(xmlContent, "/output/all-sales.xml");
    }

    private void getAllCustomersAndSpentMoney() throws IOException, JAXBException {
        List<CustomerViewModel> models = this.saleService.allCustomersAndSpentMoney();
        CustomerSalesWrapper wrapper = new CustomerSalesWrapper(models);
        String xmlContent = this.xmlParser.serialize(wrapper);
        this.writer.writeFile(xmlContent, "/output/customers-and-spent-money.xml");

    }

    private void getAllCarsAndParts() throws IOException, JAXBException {
        List<CarViewModel> models = this.carService.findAllCarsAndParts();
        CarPartExportWrapper wrapper = new CarPartExportWrapper(models);
        String xmlContent = this.xmlParser.serialize(wrapper);
        this.writer.writeFile(xmlContent, "/output/cars-and-parts.xml");
    }

    private void getLocalSuppliers() throws IOException, JAXBException {
        List<SupplierDto> supplierDtos = this.partService.localSuppliers();
        SupplierExportWrapper wrapper = new SupplierExportWrapper(supplierDtos);
        String xmlContent = this.xmlParser.serialize(wrapper);
        this.writer.writeFile(xmlContent, "/output/local-supliers.xml");
    }

    private void getToyotaCars() throws IOException, JAXBException {
        List<CarDto> carDtos = this.carService.allToyotaCars();
        CarExportWrapper wrapper = new CarExportWrapper(carDtos);
        String xmlContent = this.xmlParser.serialize(wrapper);
        this.writer.writeFile(xmlContent, "/output/all-toyota-cars.xml");
    }

    private void orderedCustomers() throws IOException, JAXBException {
        List<Customer> customers = this.customerService.getAllCustomersSorted();
        CustomerExportWrapper wrapper = new CustomerExportWrapper(customers);
        String xmlContent = this.xmlParser.serialize(wrapper);
        this.writer.writeFile(xmlContent, "/output/customers-sorted.xml");
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

    private void seedCustomers() throws IOException, JAXBException {
        String custStream = this.reader.readAll(CUSTOMERS_JSON_INPUT);
        CustomerWrapper wrapper = this.xmlParser.deserialize(custStream, CustomerWrapper.class);
        for (CustomersXmlDto dto:wrapper.getCustomers()) {
            Customer customer = new Customer();
            customer.setName(dto.getName());
            customer.setBirthDate(dto.getBirthDate());
            customer.setYoungDriver(dto.isYoungDriver());
            this.customerService.save(customer);
        }
    }

    private void seedCars() throws IOException, JAXBException {
        String carsStream = this.reader.readAll(CARS_JSON_INPUT);
        CarWrapper wrapper = this.xmlParser.deserialize(carsStream, CarWrapper.class);
        Random random = new Random();
        for (CarXmlImportDto c:wrapper.getCars()) {
            Car car = new Car();
            car.setMake(c.getMake());
            car.setModel(c.getModel());
            car.setTravelledDistance(c.getDistanceTravelled());
            car.setParts(new ArrayList<>());
            for (int i = 0; i < 15; i++) {
                Long randomNumber = (long)random.nextInt(130);
                if(this.partService.getById(randomNumber).isPresent()){
                    Part p = this.partService.getById(randomNumber).get();
                    this.partService.save(p);
                    car.getParts().add(p);
                }
            }
            this.carService.save(car);
        }
    }

    private void seedParts() throws IOException, JAXBException {
        String partsStream = this.reader.readAll(PARTS_JSON_INPUT);
        PartWrapper partWrapper  = this.xmlParser.deserialize(partsStream, PartWrapper.class);
        Random random = new Random();
        for (PartImportDto p:partWrapper.getParts()) {
            Long thisRandom = (long) random.nextInt(30);
            if( this.supplierService.getById(thisRandom).isPresent()){
                Part part = new Part();
                part.setName(p.getName());
                part.setPrice(p.getPrice());
                part.setQuantity(p.getQuantity());
                this.partService.save(part);
                part.setSupplier(this.supplierService.getById(thisRandom).get());
                this.partService.save(part);
            }
        }
    }

    private void seedSuppliers() throws IOException, JAXBException {
        String suppliersXml = this.reader.readAll(SUPPLIERS_JSON_INPUT);
        SupplierWrapper wrapper =  this.xmlParser.deserialize(suppliersXml, SupplierWrapper.class);
        for (SupplierImportDto dto:wrapper.getSuppliers()) {
            Supplier supplier = new Supplier(dto.getName(), dto.isImporter().equalsIgnoreCase("true"));
            this.supplierService.save(supplier);
        }
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
