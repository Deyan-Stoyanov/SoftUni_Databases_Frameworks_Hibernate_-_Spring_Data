import entities.Course;
import entities.Student;
import entities.Teacher;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("db");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Student student = new Student("Pesho", "Petrov", "088888888", 5.5, 15, new HashSet<>());
        Teacher teacher = new Teacher("Gosho", "Georgiev", "088888888", "gosho@abv.bg", BigDecimal.valueOf(25.4), new HashSet<>());
        Course course = new Course("Math", "Math classes", new Date(), new Date(), 29, new HashSet<>(), teacher);
        teacher.getCourses().add(course);
        student.getCourses().add(course);
        em.persist(student);
        em.persist(teacher);
        em.persist(course);
        em.getTransaction().commit();
        em.close();
        emf.close();

    }
}
