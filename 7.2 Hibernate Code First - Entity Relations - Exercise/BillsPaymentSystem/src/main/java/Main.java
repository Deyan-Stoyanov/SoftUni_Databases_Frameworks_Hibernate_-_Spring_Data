import entities.BankAccount;
import entities.BillingDetail;
import entities.CreditCard;
import entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("db");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        User user = new User("Pesho", "Petrov", "pesho@abv.bg", "password", new HashSet<>());
        BillingDetail creditCard = new CreditCard(123, user, "credit", 5, 2018);
        BillingDetail bankAccount = new BankAccount(123, user, "Bank", "123123");
        user.getBillingDetails().add(creditCard);
        user.getBillingDetails().add(bankAccount);
        em.persist(user);
        em.persist(creditCard);
        em.persist(bankAccount);
        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
