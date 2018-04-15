package softuni.gamestore.models.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @ManyToMany
    private Set<User> users;

    public Role(String name, Set<User> users) {
        this.name = name;
        this.users = users;
    }

    public Role() {
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

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
