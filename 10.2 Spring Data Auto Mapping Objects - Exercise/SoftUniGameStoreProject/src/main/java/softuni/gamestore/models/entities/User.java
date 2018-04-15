package softuni.gamestore.models.entities;

import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Email
    private String email;

    @Column
    private String password;

    @Column
    private String fullName;

    @ManyToMany
    private Set<Game> games;

    @ManyToMany(mappedBy = "users")
    private Set<Role> roles;

    public User(String email, String password, String fullName, Set<Game> games, Set<Role> roles) {
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.games = games;
        this.roles = roles;
    }

    public User() {
        this.games = new HashSet<>();
        this.roles = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Set<Game> getGames() {
        return games;
    }

    public void setGames(Set<Game> games) {
        this.games = games;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

}
