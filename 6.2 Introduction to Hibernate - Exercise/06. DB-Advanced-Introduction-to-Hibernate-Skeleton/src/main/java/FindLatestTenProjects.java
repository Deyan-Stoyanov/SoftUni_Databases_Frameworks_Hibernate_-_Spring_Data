import entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class FindLatestTenProjects {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = emf.createEntityManager();
        List<Project> projects = em.createQuery("SELECT p FROM Project AS p ORDER BY p.startDate DESC ").getResultList();
        PrintProjects(projects);
        em.close();
        emf.close();
    }

    private static void PrintProjects(List<Project> projects) {
        int counter = 0;
        for (Project p : projects) {
            if (counter == 10) {
                break;
            }
            System.out.printf("Project name: %s%n", p.getName());
            System.out.printf("\tProject Description: %s%n", p.getDescription());
            System.out.printf("\tProject Start Date: %s%n", p.getStartDate().toString());
            if(p.getEndDate() == null){
                System.out.println("\tProject End Date: null");
            } else {
                System.out.printf("\tProject End Date: %s%n", p.getEndDate().toString());
            }
            counter++;
        }
    }
}
