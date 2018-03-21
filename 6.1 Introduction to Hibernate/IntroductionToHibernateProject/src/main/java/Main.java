import entities.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.configure();
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Student pesho = new Student("Pesho", LocalDate.parse("2005-05-06"));
        session.save(pesho);
        Student gosho = new Student("Gosho", LocalDate.parse("1995-03-08"));
        session.save(gosho);
        Student ivan = new Student("Ivan", LocalDate.parse("2001-12-25"));
        session.save(ivan);
        Student maria = new Student("Maria", LocalDate.parse("2018-01-31"));
        session.save(maria);
        List<Student> studentList = session.createQuery("FROM Student").list();
        for (Student s:studentList) {
            System.out.println(s.toString());
        }
        session.getTransaction().commit();
        session.close();

    }
}