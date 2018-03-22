package demo;

import model.*;
import product.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Demo {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("db");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        BasicIngredient mint = new Mint();
        BasicIngredient nettle = new Nettle();
        BasicChemicalIngredient ammoniumChloride = new AmoniumChloride();
        em.persist(mint);
        em.persist(nettle);
        em.persist(ammoniumChloride);
        List<BasicIngredient> basicIngredientList =  em.createQuery("SELECT i FROM BasicIngredient AS i").getResultList();
        Set<BasicIngredient> basicIngredients = new HashSet<>();
        Set<Shampoo> shampoosSet = new HashSet<>();
        for (BasicIngredient b:basicIngredientList) {
            System.out.println(b.getId() + " " + b.getName() + " " + b.getPrice());
            basicIngredients.add(b);
        }
        ClassicLabel label = new ClassicLabel("Spring Shampoo", "Nettle and Mint");
        em.persist(label);
        ProductionBatch batch = new ProductionBatch();
        em.persist(batch);
        BasicShampoo springShampoo = new SpringShampoo();
        springShampoo.setLabel(label);
        springShampoo.setBatch(batch);
        springShampoo.setIngredients(basicIngredients);
        em.persist(springShampoo);
        List<Shampoo> shampoos = em.createQuery("SELECT s FROM BasicShampoo AS s").getResultList();
        for (Shampoo s:shampoos) {
            System.out.printf("%s, $%.2f: %s - %s%n", s.getName(), s.getPrice(), s.getLabel().getTitle(), s.getLabel().getSubtitle());
        }
        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
