import entities.Diagnose;
import entities.Medicament;
import entities.Patient;
import entities.Visitation;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;

public class Main{
    public static void main(String[] args) throws IOException, ParseException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("db");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        System.out.println("Enter patient name: ");
        String firstName = reader.readLine().split("\\s+")[0];
        String lastName = reader.readLine().split("\\s+")[1];
        System.out.println("Enter patient address: ");
        String address = reader.readLine();
        System.out.println("Enter patient email: ");
        String email = reader.readLine();
        System.out.println("Enter patient birth date(dd-mm-yyyy): ");
        String date = reader.readLine();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date dateStr = formatter.parse(date);
        boolean isInsured = false;
        System.out.println("Is patient insured?");
        if(reader.readLine().equalsIgnoreCase("yes")){
            isInsured = true;
        }
        Patient patient = new Patient(firstName, lastName, address, email, dateStr, new byte[255], isInsured, new HashSet<>(), new HashSet<>(), new HashSet<>());
        System.out.println("Enter visitation date(dd-mm-yyyy): ");
        Date visitDate = formatter.parse(reader.readLine());
        System.out.println("Enter visitation comments: ");
        String comments = reader.readLine();
        Visitation visitation = new Visitation(visitDate, comments);
        patient.getVisitations().add(visitation);
        System.out.println("Enter diagnose name: ");
        String diagnoseName = reader.readLine();
        System.out.println("Enter diagnose comments: ");
        String diagnoseComments = reader.readLine();
        Diagnose diagnose = new Diagnose(diagnoseName, diagnoseComments);
        patient.getDiagnoses().add(diagnose);
        System.out.println("Enter medicament name: ");
        String medicamentName = reader.readLine();
        Medicament medicament = new Medicament(medicamentName);
        patient.getMedicaments().add(medicament);
        em.persist(patient);
        em.persist(visitation);
        em.persist(diagnose);
        em.persist(medicament);
        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
