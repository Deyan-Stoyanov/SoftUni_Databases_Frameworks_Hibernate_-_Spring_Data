package photography.workshop.model.entity;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "photographers")
public class Photographer {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic(optional = false)
    private String firstName;

    @Column(nullable = false)
    @Size(min = 2, max = 50)
    private String lastName;

    @Pattern(regexp = "^\\+[0-9]{1,3}\\/[0-9]{8,10}$")
    private String phone;

    @OneToOne(targetEntity = BasicCamera.class, optional = false)
    private BasicCamera primaryCamera;

    @OneToOne(targetEntity = BasicCamera.class, optional = false)
    private BasicCamera secondaryCamera;

    @OneToMany(mappedBy = "owner",cascade = CascadeType.ALL)
    private List<Lens> lenses;

    @OneToMany(mappedBy = "owner",cascade = CascadeType.ALL)
    private List<Accessory> accessories;

    public Photographer(String firstName, String lastName, String phone, BasicCamera primaryCamera, BasicCamera secondaryCamera, List<Lens> lenses, List<Accessory> accessories) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.primaryCamera = primaryCamera;
        this.secondaryCamera = secondaryCamera;
        this.lenses = lenses;
        this.accessories = accessories;
    }

    public Photographer() {
        this.lenses = new ArrayList<>();
        this.accessories = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public BasicCamera getPrimaryCamera() {
        return primaryCamera;
    }

    public void setPrimaryCamera(BasicCamera primaryCamera) {
        this.primaryCamera = primaryCamera;
    }

    public BasicCamera getSecondaryCamera() {
        return secondaryCamera;
    }

    public void setSecondaryCamera(BasicCamera secondaryCamera) {
        this.secondaryCamera = secondaryCamera;
    }

    public List<Lens> getLenses() {
        return lenses;
    }

    public void setLenses(List<Lens> lenses) {
        this.lenses = lenses;
    }

    public List<Accessory> getAccessories() {
        return accessories;
    }

    public void setAccessories(List<Accessory> accessories) {
        this.accessories = accessories;
    }
}
