import entities.Employee;
import entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class GetEmployeeWithProject {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int employeeId = Integer.parseInt(reader.readLine());
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = emf.createEntityManager();
        Employee employee = (Employee) em.createQuery("SELECT e FROM Employee as e WHERE e.id=" + employeeId).getSingleResult();
        List<Project> projects = em.createQuery("SELECT p FROM Project AS p ORDER BY p.name").getResultList();
        System.out.printf("%s %s - %s%n", employee.getFirstName(), employee.getLastName(), employee.getJobTitle());
        for (Project p:projects) {
            if(p.getEmployees().contains(employee)){
                System.out.printf("\t%s%n", p.getName());
            }
        }
        em.close();
        emf.close();
    }
}
