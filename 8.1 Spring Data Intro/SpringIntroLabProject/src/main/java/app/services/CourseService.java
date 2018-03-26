package app.services;

import app.model.Course;
import app.model.Homework;

import java.util.Date;
import java.util.List;

public interface CourseService {
    List<Course> findAllByStartDate(Date date);
    void save(Course course);
}
