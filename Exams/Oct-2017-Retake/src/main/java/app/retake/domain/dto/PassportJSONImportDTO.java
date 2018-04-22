package app.retake.domain.dto;

import app.retake.domain.models.Animal;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.persistence.Column;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

public class PassportJSONImportDTO implements Serializable {

    @Expose
    @Pattern(regexp = "[a-zA-Z0-9]{7}[0-9]{3}")
    @NotNull
    private String serialNumber;

    @NotNull
    @Expose
    @Pattern(regexp = "(\\+359[0-9]{9})|(0[0-9]{9})")
    private String ownerPhoneNumber;

    @Expose
    @Size(min = 3, max = 30)
    private String ownerName;

    @Expose
    private String registrationDate;

    public PassportJSONImportDTO(String serialNumber, String ownerPhoneNumber, String ownerName, String registrationDate) {
        this.serialNumber = serialNumber;
        this.ownerPhoneNumber = ownerPhoneNumber;
        this.ownerName = ownerName;
        this.registrationDate = registrationDate;
    }

    public PassportJSONImportDTO() {
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
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

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }
}
