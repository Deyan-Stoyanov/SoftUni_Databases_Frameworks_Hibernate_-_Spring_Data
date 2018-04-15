package app.domain.dto.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.xml.bind.annotation.*;
import java.util.Set;
@XmlAccessorType(XmlAccessType.FIELD)
public class PersonDto {

    @Expose
    @XmlAttribute(name = "id")
    @SerializedName("id")
    private long id;

    @Expose
    @SerializedName("firstName")
    @XmlElement(name = "firstName")
    private String firstName;

    @Expose
    @SerializedName("lastName")
    @XmlElement(name = "lastName")
    private String lastName;

    @Expose
    @XmlElement(name = "address")
    private AddressDto addressImportDto;

    @Expose
    @SerializedName("phoneNumbers")
    @XmlElementWrapper(name = "phoneNumbers")
    @XmlElement(name = "phoneNumber")
    private Set<PhoneNumberDto> phoneNumberDtos;

    public PersonDto(long id, String firstName, String lastName, AddressDto addressImportDto, Set<PhoneNumberDto> phoneNumbers) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.addressImportDto = addressImportDto;
        this.phoneNumberDtos = phoneNumbers;
    }

    public PersonDto() {
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

    public AddressDto getAddressImportDto() {
        return addressImportDto;
    }

    public void setAddressImportDto(AddressDto addressImportDto) {
        this.addressImportDto = addressImportDto;
    }

    public Set<PhoneNumberDto> getPhoneNumberDtos() {
        return phoneNumberDtos;
    }

    public void setPhoneNumberDtos(Set<PhoneNumberDto> phoneNumberDtos) {
        this.phoneNumberDtos = phoneNumberDtos;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return this.getFirstName() + " " + this.getLastName();
    }
}
