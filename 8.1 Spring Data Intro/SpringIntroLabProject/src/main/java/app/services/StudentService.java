package app.services;

import app.model.Course;
import app.model.Student;

import java.util.List;

public interface StudentService {
    void register(Student student);

    void expel(Student student);

    void expel(long id);

    Student findStudent(long id);

    List<Student> findStudentByCourse(Course course);
}
