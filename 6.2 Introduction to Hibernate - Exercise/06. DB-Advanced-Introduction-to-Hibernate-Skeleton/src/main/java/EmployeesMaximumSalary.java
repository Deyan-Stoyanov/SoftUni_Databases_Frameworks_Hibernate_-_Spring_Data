import entities.Department;
import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.List;

public class EmployeesMaximumSalary {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = emf.createEntityManager();
        List<Department> departmentList = em.createQuery("SELECT d FROM Department AS d").getResultList();
        for (Department d : departmentList) {
            List<Employee> employees = em.createQuery("SELECT e FROM Employee as e WHERE e.department.id=:d ORDER BY e.salary DESC").setParameter("d", d.getId()).getResultList();
            BigDecimal maxSalary = employees.get(0).getSalary();
            if (maxSalary.compareTo(new BigDecimal(30000)) < 0 || maxSalary.compareTo(new BigDecimal(70000)) > 0) {
                System.out.printf("%s - %.2f%n", d.getName(), maxSalary);
            }
        }
        em.close();
        emf.close();
    }
}
