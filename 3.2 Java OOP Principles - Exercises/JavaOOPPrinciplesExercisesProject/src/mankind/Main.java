package mankind;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] studentData = reader.readLine().split("\\s+");
        String[] workerData = reader.readLine().split("\\s+");
        try {
            Student student = new Student(studentData[0], studentData[1],studentData[2]);
            Worker worker = new Worker(workerData[0], workerData[1], Double.parseDouble(workerData[2]), Double.parseDouble(workerData[3]));
            System.out.printf("First Name: %s%nLast Name: %s%nFaculty number: %s%n", student.getFirstName(), student.getLastName(), student.getFacultyNumber());
            System.out.println();
            System.out.printf("First Name: %s%nLast Name: %s%nWeek Salary: %.2f%nHours per day: %.2f%nSalary per hour: %.2f%n", worker.getFirstName(), worker.getLastName(), worker.getWeekSalary(), worker.getWorkingHours(), (worker.getWeekSalary() / 7) / worker.getWorkingHours());
        } catch (IllegalArgumentException ie){
            System.out.println(ie.getMessage());
        }
    }
}
