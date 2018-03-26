import Entities.WizardDeposits;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("db");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        WizardDeposits wp = new WizardDeposits();
        wp.setFirst_name("Pesho");
        wp.setLast_name("Petrov");
        wp.setAge(25);
        wp.setDepositGroup("ads");
        wp.setDepositExpired(false);
        em.persist(wp);
        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
