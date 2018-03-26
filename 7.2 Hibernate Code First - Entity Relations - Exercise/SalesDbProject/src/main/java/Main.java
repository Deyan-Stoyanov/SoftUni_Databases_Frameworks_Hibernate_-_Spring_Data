import entites.Customer;
import entites.Product;
import entites.Sale;
import entites.StoreLocation;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("db");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Product product = new Product("Bread", 5, BigDecimal.valueOf(1.75), new HashSet<>());
        Customer customer = new Customer("Pesho", "pesho@abv.bg", "BG123456", new HashSet<>());
        StoreLocation storeLocation = new StoreLocation("Sofia", new HashSet<>());
        Sale firstSale = new Sale(product, customer, storeLocation, new Date());
        product.getSales().add(firstSale);
        customer.getSales().add(firstSale);
        storeLocation.getSales().add(firstSale);
        em.persist(product);
        em.persist(customer);
        em.persist(storeLocation);
        em.persist(firstSale);
        em.close();
        emf.close();
    }
}
