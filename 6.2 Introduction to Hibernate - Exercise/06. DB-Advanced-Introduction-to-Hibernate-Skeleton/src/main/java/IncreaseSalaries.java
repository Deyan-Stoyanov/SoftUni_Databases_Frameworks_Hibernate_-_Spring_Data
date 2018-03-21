import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.List;

public class IncreaseSalaries {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = emf.createEntityManager();
        List<Employee> employeeList = em.createQuery("SELECT e FROM Employee AS e WHERE e.department.id IN (1, 2, 4, 11)").getResultList();
        for (Employee e:employeeList) {
            e.setSalary(e.getSalary().add(e.getSalary().multiply(new BigDecimal(0.12))));
            System.out.printf("%s %s (%.2f)%n", e.getFirstName(), e.getLastName(), e.getSalary());
            em.persist(e);
        }
        em.close();
        emf.close();
    }
}
