import entities.Address;
import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class AddressWithEmployeeCount {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = emf.createEntityManager();
        List<Address> addresses = em.createQuery("SELECT a FROM Address AS a ORDER BY a.employees.size DESC , a.town.id ASC").getResultList();
        List<Employee> employees = em.createQuery("SELECT e FROM Employee AS e").getResultList();
        int counter = 0;
        for (Address a:addresses) {
            if(counter == 10){
                break;
            }
            int employeesCount = 0;
            for (Employee e:employees) {
                if(e.getAddress().getId().equals(a.getId())){
                    employeesCount++;
                }
            }
            System.out.printf("%s, %s - %d employees%n", a.getText(), a.getTown().getName(), employeesCount);
            counter++;
        }
        em.close();
        emf.close();
    }
}
