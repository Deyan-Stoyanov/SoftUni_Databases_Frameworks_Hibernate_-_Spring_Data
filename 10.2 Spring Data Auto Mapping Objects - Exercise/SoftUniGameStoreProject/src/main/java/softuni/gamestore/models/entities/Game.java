package softuni.gamestore.models.entities;

import softuni.gamestore.validators.URL;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "games")
public class Game {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Pattern(regexp = "^[A-Z][a-zA-Z0-9]{2,99}$")
    private String title;

    @Column
    @Pattern(regexp = "[a-zA-Z0-9]{11}")
    private String youtubeId;

    @Column
    @URL
    private String imageUrl;

    @Column(columnDefinition = "DOUBLE(10, 1) DEFAULT 0.0")
    private Double size;

    @Column(precision = 20, scale = 2)
    private BigDecimal price;

    @Column(columnDefinition = "TEXT")
    @Size(min = 20)
    private String description;

    @Column
    private Date releaseDate;

    @ManyToMany(mappedBy = "games")
    private Set<User> users;

    public Game(String title, String youtubeId, String imageUrl, Double size, BigDecimal price, String description, Date releaseDate, Set<User> users) {
        this.title = title;
        this.youtubeId = youtubeId;
        this.imageUrl = imageUrl;
        this.size = size;
        this.price = price;
        this.description = description;
        this.releaseDate = releaseDate;
        this.users = users;
    }

    public Game() {
        this.users = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYoutubeId() {
        return youtubeId;
    }

    public void setYoutubeId(String youtubeId) {
        this.youtubeId = youtubeId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
