import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class EmployeesFromDepartment {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = emf.createEntityManager();
        List<Employee> employees =
                em.createQuery("SELECT e FROM Employee AS e JOIN Department d ON e.department.id = d.id WHERE d.id=6 ORDER BY e.salary, e.id").getResultList();
        PrintEmployees(employees);
        em.close();
        emf.close();
    }

    private static void PrintEmployees(List<Employee> employees) {
        for (Employee e:employees) {
            System.out.printf("%s %s from %s - $%.2f%n", e.getFirstName(), e.getLastName(), e.getDepartment().getName(), e.getSalary());
        }
    }
}
