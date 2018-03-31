package usersystem.usersystemproject.models.entites;

import usersystem.usersystemproject.annotations.Email;
import usersystem.usersystemproject.annotations.Password;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false)
    @Size(min = 4, max = 30)
    private String username;

    @Password
    @Column(name = "password", nullable = false)
    private String password;

    @Email
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "profile_picture")
    private byte[] profilePicture;

    @Column(name = "registered_on")
    private Date registeredOn;

    @Column(name = "last_time_logged_in")
    private Date lastTimeLoggedIn;

    @Column(name = "age")
    @Max(value = 120)
    private Integer age;

    @Column(name = "id_deleted")
    private Boolean isDeleted;

    @ManyToOne
    @JoinColumn(name = "born_town_id", referencedColumnName = "id")
    private Town bornTown;

    @ManyToOne
    @JoinColumn(name = "current_town_id", referencedColumnName = "id")
    private Town currentTown;

    @ManyToMany
    @JoinTable(name = "users_users",
            joinColumns = @JoinColumn(name = "first_user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "second_user_id", referencedColumnName = "id"))
    private Set<User> friends;

    @Column(name = "first_name")
    private String firstName = "";

    @Column(name = "last_name")
    private String lastName = "";

    private String fullName = firstName + " " + lastName;

    public User(String username, String password, String email, byte[] profilePicture, Date registeredOn, Date lastTimeLoggedIn, Integer age, Boolean isDeleted, Town bornTown, Town currentTown, Set<User> friends, String firstName, String lastName, String fullName) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.profilePicture = profilePicture;
        this.registeredOn = registeredOn;
        this.lastTimeLoggedIn = lastTimeLoggedIn;
        this.age = age;
        this.isDeleted = isDeleted;
        this.bornTown = bornTown;
        this.currentTown = currentTown;
        this.friends = friends;
        this.firstName = firstName;
        this.lastName = lastName;
        this.fullName = fullName;
    }

    public User() {
        this.friends = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public byte[] getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(byte[] profilePicture) {
        this.profilePicture = profilePicture;
    }

    public Date getRegisteredOn() {
        return registeredOn;
    }

    public void setRegisteredOn(Date registeredOn) {
        this.registeredOn = registeredOn;
    }

    public Date getLastTimeLoggedIn() {
        return lastTimeLoggedIn;
    }

    public void setLastTimeLoggedIn(Date lastTimeLoggedIn) {
        this.lastTimeLoggedIn = lastTimeLoggedIn;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public Town getBornTown() {
        return bornTown;
    }

    public void setBornTown(Town bornTown) {
        this.bornTown = bornTown;
    }

    public Town getCurrentTown() {
        return currentTown;
    }

    public void setCurrentTown(Town currentTown) {
        this.currentTown = currentTown;
    }

    public Set<User> getFriends() {
        return friends;
    }

    public void setFriends(Set<User> friends) {
        this.friends = friends;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
