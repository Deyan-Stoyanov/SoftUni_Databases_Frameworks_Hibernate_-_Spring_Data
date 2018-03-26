package app.repository;

import app.model.Course;
import app.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{
    @Query("select s.name, s.courses.size FROM Student as s JOIN s.homeworks as h")
    Student getStudentByName();

    @Query("select s.name from Student as s")
    List<Student> getAllByCoursesIsNotNullAndCourses_Empty();

    @Query("SELECT s.name FROM Student AS s")
    List<Student> getAllByCourses(Course course);
}
