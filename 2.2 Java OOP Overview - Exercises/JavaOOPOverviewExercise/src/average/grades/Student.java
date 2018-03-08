package average.grades;

import java.util.ArrayList;

public class Student {
    private String name;
    private ArrayList<Double> listOfGrades;
    private double averageGrade;

    public Student(String name) {
        this.name = name;
        this.listOfGrades = new ArrayList<>();
        this.averageGrade = 0;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Double> getListOfGrades() {
        return this.listOfGrades;
    }

    public void setListOfGrades(ArrayList<Double> listOfGrades) {
        this.listOfGrades = listOfGrades;
    }

    public double getAverageGrade() {
        return this.averageGrade;
    }

    public void setAverageGrade() {
        double averageGrade = this.listOfGrades.stream().mapToDouble(x -> x).average().orElse(0);
        this.averageGrade = averageGrade;
    }
}
