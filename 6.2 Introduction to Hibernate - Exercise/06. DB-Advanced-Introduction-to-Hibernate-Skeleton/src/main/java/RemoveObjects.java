import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class RemoveObjects {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = emf.createEntityManager();
        List<Town> townList = em.createQuery("SELECT t FROM Town AS t").getResultList();
        for (Town t:townList) {
            if(t.getName().length() > 5){
                em.detach(t);
            } else {
                t.setName(t.getName().toLowerCase());
                em.persist(t);
            }
        }
        em.close();
        emf.close();
    }
}
