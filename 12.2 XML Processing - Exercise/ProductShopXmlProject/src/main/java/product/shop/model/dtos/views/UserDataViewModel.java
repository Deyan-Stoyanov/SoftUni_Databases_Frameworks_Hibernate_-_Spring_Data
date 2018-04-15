package product.shop.model.dtos.views;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(value = XmlAccessType.FIELD)
public class UserDataViewModel {
    @XmlAttribute(name = "first-name")
    private
    String firstName;
    @XmlAttribute(name = "last-name")
    private
    String lastName;
    @XmlAttribute(name = "age")
    private Integer age;
    @XmlElement(name = "sold-products")
    private ProductWrapper wraper;

    public UserDataViewModel(String firstName, String lastName, Integer age, ProductWrapper wraper) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.wraper = wraper;
    }

    public UserDataViewModel() {
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public ProductWrapper getWraper() {
        return wraper;
    }

    public void setWraper(ProductWrapper wraper) {
        this.wraper = wraper;
    }
}
