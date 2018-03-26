package app.serviceImpl;

import app.model.Course;
import app.model.Student;
import app.repository.StudentRepository;
import app.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public void register(Student student) {
        this.studentRepository.save(student);
    }

    @Override
    public void expel(Student student) {
        this.studentRepository.delete(student);
    }

    @Override
    public void expel(long id) {
        this.studentRepository.delete(id);
    }

    @Override
    public Student findStudent(long id) {
        return this.studentRepository.findOne(id);
    }

    @Override
    public List<Student> findStudentByCourse(Course course) {
        return this.studentRepository.getAllByCourses(course);
    }
}
