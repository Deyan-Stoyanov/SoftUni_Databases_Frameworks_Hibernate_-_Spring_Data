package app.domain.dto.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.Set;

public class PersonExportDto {

    private String firstName;

    private String lastName;

    private AddressDto addressImportDto;

    private Set<PhoneNumberDto> phoneNumberDtos;

    public PersonExportDto(String firstName, String lastName, AddressDto addressImportDto, Set<PhoneNumberDto> phoneJsonDtos) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.addressImportDto = addressImportDto;
        this.phoneNumberDtos = phoneJsonDtos;
    }

    public PersonExportDto() {
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
}

