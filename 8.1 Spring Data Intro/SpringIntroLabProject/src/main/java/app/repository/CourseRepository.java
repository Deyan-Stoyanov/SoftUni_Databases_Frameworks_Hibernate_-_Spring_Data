package app.repository;

import app.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    @Query("SELECT c.name, c.description, c.startDate FROM Course as c")
    List<Course>  findAllByStartDate(Date startDate);
}
