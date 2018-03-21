import entities.Address;
import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AddingNewAddressAndUpdateEmployee {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String lastNameOfEmployee = reader.readLine();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = emf.createEntityManager();
        Address address = new Address();
        address.setText("Vitoshka 15");
        Employee employee = (Employee) em.createQuery(String.format("SELECT e FROM Employee as e WHERE e.lastName='%s'", lastNameOfEmployee)).getSingleResult();
        employee.setAddress(address);
        em.persist(address);
        em.persist(employee);
        System.out.printf("%s set as address to %s %s%n", employee.getAddress().getText(), employee.getFirstName(), employee.getLastName());
        em.close();
        emf.close();
    }

}
