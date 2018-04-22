package app.retake.domain.dto;

import javax.validation.constraints.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(value = XmlAccessType.FIELD)
public class VetXMLImportDTO {

    @XmlElement(name = "vet")
    @Size(min = 3, max = 40)
    @NotNull
    private String name;
    @XmlElement
    @Size(min = 3, max = 50)
    @NotNull
    private String profession;
    @XmlElement
    @Min(22)
    @Max(65)
    private Integer age;
    @XmlElement(name = "phone-number")
    @Pattern(regexp = "(\\+359[0-9]{9})|(0[0-9]{9})")
    @NotNull
    private String phoneNumber;

    public VetXMLImportDTO(String name, String profession, Integer age, String phoneNumber) {
        this.name = name;
        this.profession = profession;
        this.age = age;
        this.phoneNumber = phoneNumber;
    }

    public VetXMLImportDTO() {
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
}
