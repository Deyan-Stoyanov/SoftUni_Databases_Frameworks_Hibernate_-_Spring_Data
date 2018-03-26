package app.serviceImpl;

import app.model.Course;
import app.repository.CourseRepository;
import app.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {

    private CourseRepository courseRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Course> findAllByStartDate(Date date) {
        return this.courseRepository.findAllByStartDate(date);
    }

    @Override
    public void save(Course course) {
        this.courseRepository.save(course);
    }
}
