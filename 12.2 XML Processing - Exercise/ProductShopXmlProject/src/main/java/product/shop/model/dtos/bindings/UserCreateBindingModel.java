package product.shop.model.dtos.bindings;

import com.google.gson.annotations.Expose;

import javax.xml.bind.annotation.*;
import java.io.Serializable;

@XmlAccessorType(value = XmlAccessType.FIELD)
public class UserCreateBindingModel implements Serializable {
    @XmlAttribute(name = "first-name")
    private String firstName;
    @XmlAttribute(name = "last-name")
    private String lastName;
    @XmlAttribute(name = "age")
    private Integer age;

    public UserCreateBindingModel(String firstName, String lastName, Integer age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public UserCreateBindingModel() {
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
}
