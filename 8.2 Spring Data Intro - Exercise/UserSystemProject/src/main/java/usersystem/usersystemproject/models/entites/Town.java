package usersystem.usersystemproject.models.entites;

import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "towns")
public class Town {

    @javax.persistence.Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "country")
    private String country;

    @OneToMany(mappedBy = "bornTown", targetEntity = User.class)
    private Set<User> peopleFormTown;

    @OneToMany(mappedBy = "currentTown", targetEntity = User.class)
    private Set<User> peopleLivingInTown;

    public Town(String name, String country, Set<User> peopleFormTown, Set<User> peopleLivingInTown) {
        this.name = name;
        this.country = country;
        this.peopleFormTown = peopleFormTown;
        this.peopleLivingInTown = peopleLivingInTown;
    }

    public Town() {
        this.peopleFormTown = new HashSet<>();
        this.peopleLivingInTown = new HashSet<>();
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Set<User> getPeopleFormTown() {
        return peopleFormTown;
    }

    public void setPeopleFormTown(Set<User> peopleFormTown) {
        this.peopleFormTown = peopleFormTown;
    }

    public Set<User> getPeopleLivingInTown() {
        return peopleLivingInTown;
    }

    public void setPeopleLivingInTown(Set<User> peopleLivingInTown) {
        this.peopleLivingInTown = peopleLivingInTown;
    }
}
