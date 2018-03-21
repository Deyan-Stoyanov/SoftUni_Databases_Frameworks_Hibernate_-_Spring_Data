import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class EmployeeWithSalaryOverFiftyThousand {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = emf.createEntityManager();
        List<Employee> employees = em.createQuery("SELECT e FROM Employee AS e WHERE  e.salary > 50000").getResultList();
        PrintEmployees(employees);
        em.close();
        emf.close();
    }

    private static void PrintEmployees(List<Employee> employees) {
        for (Employee e : employees) {
            System.out.println(e.getFirstName());
        }
    }
}
