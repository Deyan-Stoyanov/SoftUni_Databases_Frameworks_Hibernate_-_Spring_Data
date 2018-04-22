package app.exam.terminal;

import app.exam.config.Config;
import app.exam.controller.CategoryController;
import app.exam.controller.EmployeesController;
import app.exam.controller.ItemsController;
import app.exam.controller.OrdersController;
import app.exam.io.interfaces.ConsoleIO;
import app.exam.io.interfaces.FileIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class Terminal implements CommandLineRunner {

    private final FileIO fileIO;
    private final CategoryController categoryController;
    private final EmployeesController employeesController;
    private final ItemsController itemsController;
    private final OrdersController ordersController;
    private final ConsoleIO consoleIO;

    @Autowired
    public Terminal(FileIO fileIO, CategoryController categoryController, EmployeesController employeesController, ItemsController itemsController, OrdersController ordersController, ConsoleIO consoleIO) {
        this.fileIO = fileIO;
        this.categoryController = categoryController;
        this.employeesController = employeesController;
        this.itemsController = itemsController;
        this.ordersController = ordersController;
        this.consoleIO = consoleIO;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println(this.employeesController.importDataFromJSON(this.fileIO.read(Config.EMPLOYEES_IMPORT_JSON)));

        System.out.println(this.itemsController.importDataFromJSON(this.fileIO.read(Config.ITEMS_IMPORT_JSON)));

        System.out.println(this.ordersController.importDataFromXML(this.fileIO.read(Config.ORDERS_IMPORT_XML)));

        exportOrdersJson();

        exportMostPopularItemXml();
    }

    private void exportMostPopularItemXml() throws IOException {
        List<String> categories = new ArrayList<>();
        categories.add("Chicken");
        categories.add("Toys");
        categories.add("Drinks");
        String xmlOutput = this.categoryController.getCategoriesWithMostPopularItemsSorted(categories);
        this.consoleIO.write(xmlOutput);
        this.fileIO.write(xmlOutput, "/files/output/most-popular-items.xml");
    }

    private void exportOrdersJson() throws IOException {
        String jsonOutput = this.ordersController.exportOrdersByEmployeeAndOrderType("Avery Rush", "ToGo");
        this.consoleIO.write(jsonOutput);
        this.fileIO.write(jsonOutput, "/files/output/export-orders.json");
    }
}
