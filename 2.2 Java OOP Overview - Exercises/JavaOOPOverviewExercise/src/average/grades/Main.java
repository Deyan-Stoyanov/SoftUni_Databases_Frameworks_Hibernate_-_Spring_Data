package average.grades;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        List<Student> listOfStudents = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] data = reader.readLine().split(" ");
            String name = data[0];
            Student student = new Student(name);
            for (int j = 1; j < data.length; j++) {
                student.getListOfGrades().add(Double.parseDouble(data[j]));
            }
            student.setAverageGrade();
            listOfStudents.add(student);
        }

        listOfStudents
                .stream()
                .filter(x -> x.getAverageGrade() >= 5.0)
                .sorted((o1, o2) -> {
                    if (o1.getName().compareTo(o2.getName()) == 0) {
                        return Double.compare(o2.getAverageGrade(), o1.getAverageGrade());
                    } else {
                        return o1.getName().compareTo(o2.getName());
                    }
                })
                        .forEach(x -> System.out.printf("%s -> %.2f%n", x.getName(), x.getAverageGrade()));
    }
}
