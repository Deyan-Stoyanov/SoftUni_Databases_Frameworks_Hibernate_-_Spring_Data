package app;

import app.model.Course;
import app.model.Homework;
import app.model.Student;
import app.services.CourseService;
import app.services.HomeworkService;
import app.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ConsoleRunner implements CommandLineRunner {

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private HomeworkService homeworkService;

    @Override
    public void run(String... strings) throws Exception {
        Student student = new Student();
        student.setName("Pesho");
        Course course = new Course();
        course.setName("Java");
        course.setPrice(BigDecimal.valueOf(200.05));
        Homework homework = new Homework();
        homework.setContent("asd");
        studentService.register(student);
        homeworkService.save(homework);
        courseService.save(course);

    }
}
