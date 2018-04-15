package product.shop.model.dtos.views;

import com.google.gson.annotations.Expose;
import product.shop.model.entity.Product;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(value = XmlAccessType.FIELD)
public class UserViewModel {
    @XmlAttribute(name = "first-name")
    private
    String firstName;
    @XmlAttribute(name = "last-name")
    private
    String lastName;
    @XmlElementWrapper
    @XmlElement(name = "sold-products")
    private List<SoldProduct> soldProducts;

    public UserViewModel(String firstName, String lastName, List<SoldProduct> soldProducts) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.soldProducts = soldProducts;
    }

    public UserViewModel() {
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

    public List<SoldProduct> getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(List<SoldProduct> soldProducts) {
        this.soldProducts = soldProducts;
    }
}
