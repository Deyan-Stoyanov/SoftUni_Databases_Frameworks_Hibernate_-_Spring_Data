package entities;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "patients")
public class Patient {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Basic
    private String firstName;

    @Basic
    private String lastName;

    @Basic
    private String address;

    @Basic
    private String email;

    @Basic
    private Date dateOfBirth;

    @Basic
    private byte[] picture;

    @Basic
    private boolean isInsured;

    @OneToMany(targetEntity = Visitation.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Visitation> visitations;

    @OneToMany(targetEntity = Diagnose.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Diagnose> diagnoses;

    @OneToMany(targetEntity = Medicament.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Medicament> medicaments;

    public Patient(String firstName, String lastName, String address, String email, Date dateOfBirth, byte[] picture, boolean isInsured, Set<Visitation> visitations, Set<Diagnose> diagnoses, Set<Medicament> medicaments) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.picture = picture;
        this.isInsured = isInsured;
        this.visitations = visitations;
        this.diagnoses = diagnoses;
        this.medicaments = medicaments;
    }

    public Patient() {
    }

    public int getId() {
        return id;
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

    public void setLasName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public boolean isInsured() {
        return isInsured;
    }

    public void setInsured(boolean insured) {
        isInsured = insured;
    }

    public Set<Visitation> getVisitations() {
        return visitations;
    }

    public void setVisitations(Set<Visitation> visitations) {
        this.visitations = visitations;
    }

    public Set<Diagnose> getDiagnoses() {
        return diagnoses;
    }

    public void setDiagnoses(Set<Diagnose> diagnoses) {
        this.diagnoses = diagnoses;
    }

    public Set<Medicament> getMedicaments() {
        return medicaments;
    }

    public void setMedicaments(Set<Medicament> medicaments) {
        this.medicaments = medicaments;
    }
}
