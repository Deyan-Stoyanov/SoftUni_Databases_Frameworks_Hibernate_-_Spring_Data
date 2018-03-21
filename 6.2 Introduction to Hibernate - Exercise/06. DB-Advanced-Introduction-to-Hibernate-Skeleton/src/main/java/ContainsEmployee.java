import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class ContainsEmployee {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String employee = reader.readLine();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = emf.createEntityManager();
        List<Employee> employees = em.createQuery("SELECT e FROM Employee AS e").getResultList();
        System.out.println(IsEmployeeAdded(employees, employee));
        em.close();
        emf.close();
    }

    private static String IsEmployeeAdded(List<Employee> employees, String name) {
        for (Employee e:employees) {
            if(String.format("%s %s", e.getFirstName(), e.getLastName()).equals(name)){
                return "Yes";
            }
        }
        return "No";
    }
}
