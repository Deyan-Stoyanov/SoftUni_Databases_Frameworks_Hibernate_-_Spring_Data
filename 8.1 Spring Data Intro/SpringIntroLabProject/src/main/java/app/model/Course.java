package app.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "courses")
public class Course {

     @Id
     @Column(name = "id")
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;

     @Column(name = "name", nullable = false)
     private String name;

     @Column(name = "description", columnDefinition = "text")
     private String description;

     @Column(name = "start_date")
     private Date startDate;

     @Column(name = "end_date")
     private Date endDate;

     @Column(name = "price", nullable = false)
     private BigDecimal price;

     @ManyToMany
     @JoinTable(name = "courses_students",
             joinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id"),
             inverseJoinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"))
     private Set<Student> students;

     @OneToMany(mappedBy = "course")
     private Set<Homework> homeworks;

    public Course(String name, String description, Date startDate, Date endDate, BigDecimal price, Set<Student> students, Set<Homework> homeworks) {
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
        this.students = students;
        this.homeworks = homeworks;
    }

    public Course() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public Set<Homework> getHomeworks() {
        return homeworks;
    }

    public void setHomeworks(Set<Homework> homeworks) {
        this.homeworks = homeworks;
    }
}
