package entities;

import java.time.LocalDate;
import java.util.Date;

public class Student {
    private Integer id;
    private String name;
    private LocalDate registrationDate;

    public Student() { }

    public Student(String name, LocalDate registrationDate) {
        this.name = name;
        this.registrationDate = registrationDate;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("Student: id = %d, name = %s, registration date = %s", getId(), getName(), getRegistrationDate().toString());
    }
}