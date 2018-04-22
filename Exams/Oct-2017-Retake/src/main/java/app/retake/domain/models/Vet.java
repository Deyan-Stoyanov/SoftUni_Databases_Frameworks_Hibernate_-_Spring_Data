package app.retake.domain.models;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "vets")
public class Vet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    @Size(min = 3, max = 40)
    private String name;

    @Column(nullable = false)
    @Size(min = 3, max = 50)
    private String profession;

    @Column
    @Min(22)
    @Max(65)
    private Integer age;

    @Column
    private String phoneNumber;

    @OneToMany(mappedBy = "vet")
    private List<Procedure> procedures;

    public Vet(String name, String profession, Integer type, String phoneNumber, List<Procedure> procedures) {
        this.name = name;
        this.profession = profession;
        this.age = type;
        this.phoneNumber = phoneNumber;
        this.procedures = procedures;
    }

    public Vet() {
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

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Procedure> getProcedures() {
        return procedures;
    }

    public void setProcedures(List<Procedure> procedures) {
        this.procedures = procedures;
    }
}
