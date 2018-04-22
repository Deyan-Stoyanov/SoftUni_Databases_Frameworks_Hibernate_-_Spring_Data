package app.retake.domain.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "passports")
public class Passport implements Serializable {
    @Id
    private String serialNumber;

    @OneToOne(mappedBy = "passport")
    private Animal animal;

    @Column(nullable = false)
    private String ownerPhoneNumber;

    @Column
    private String ownerName;

    @Column(name = "registered_on")
    private Date registrationDate;

    public Passport() {
    }

    public Passport(String serialNumber, Animal animal, String ownerPhoneNumber, String ownerName, Date registrationDate) {
        this.serialNumber = serialNumber;
        this.animal = animal;
        this.ownerPhoneNumber = ownerPhoneNumber;
        this.ownerName = ownerName;
        this.registrationDate = registrationDate;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public String getOwnerPhoneNumber() {
        return ownerPhoneNumber;
    }

    public void setOwnerPhoneNumber(String ownerPhoneNumber) {
        this.ownerPhoneNumber = ownerPhoneNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }
}

