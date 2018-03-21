import entities.Address;
import entities.Employee;
import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class RemoveTowns {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String townName = reader.readLine();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<Address> addressList = em.createQuery(String.format("SELECT a FROM Address AS a WHERE a.town.name='%s'", townName)).getResultList();
        List<Employee> employees = em.createQuery(String.format("SELECT e FROM Employee AS e WHERE e.address.town.name='%s'", townName)).getResultList();
        for (Employee e:employees) {
            e.setAddress(null);
            em.persist(e);
        }
        Town town = (Town) em.createQuery(String.format("SELECT t FROM Town AS t where t.name='%s'", townName)).getSingleResult();
        int addressCount = addressList.size();
        for (Address a:addressList) {
            em.remove(a);
        }
        if(addressCount == 1){
            System.out.printf("1 address in %s deleted", townName);
        } else {
            System.out.printf("%d address in %s deleted",addressCount, townName);
        }
        em.remove(town);
        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
