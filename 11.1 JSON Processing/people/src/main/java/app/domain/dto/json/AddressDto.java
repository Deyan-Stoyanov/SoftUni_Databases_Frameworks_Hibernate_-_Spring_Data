package app.domain.dto.json;

import com.google.gson.annotations.Expose;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class AddressDto {

    @Expose
    @XmlAttribute(name = "country")
    private String country;
    @Expose
    @XmlElement(name = "city")
    private String city;
    @Expose
    @XmlElement(name = "street")
    private String street;

    public AddressDto(String country, String city, String street) {
        this.country = country;
        this.city = city;
        this.street = street;
    }

    public AddressDto() {

    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Override
    public String toString() {
        return this.getCity() + ", " + this.getCountry() + ", " + this.getStreet();
    }
}

