package product.shop.model.dtos.views;

import com.google.gson.annotations.Expose;
import product.shop.model.entity.Product;

import java.util.List;

public class UserViewModel {
    @Expose
    private
    String firstName;
    @Expose
    private
    String lastName;
    @Expose
    private
    List<SoldProduct> soldProducts;

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
