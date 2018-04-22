package app.retake.domain.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "animals")
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    @Size(min = 3, max = 20)
    private String name;

    @Column
    @Size(min = 3, max = 20)
    private String type;

    @Column
    @Min(1)
    private Integer age;

    @OneToOne
    private Passport passport;

    @OneToMany(mappedBy = "animal")
    private List<Procedure> procedures;

    public Animal(String name, String type, Integer age, Passport passport, List<Procedure> procedures) {
        this.name = name;
        this.type = type;
        this.age = age;
        this.passport = passport;
        this.procedures = procedures;
    }

    public Animal() {
        this.procedures = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    public List<Procedure> getProcedures() {
        return procedures;
    }

    public void setProcedures(List<Procedure> procedures) {
        this.procedures = procedures;
    }
}
