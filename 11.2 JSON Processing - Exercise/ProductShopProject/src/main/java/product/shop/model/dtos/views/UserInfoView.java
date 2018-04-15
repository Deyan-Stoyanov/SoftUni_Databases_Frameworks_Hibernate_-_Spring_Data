package product.shop.model.dtos.views;

import com.google.gson.annotations.Expose;

import java.util.List;

public class UserInfoView {
    @Expose
    private String firstName;
    @Expose
    private String lastName;
    @Expose
    private Integer age;
    @Expose
    private Integer productsCount;
    @Expose
    private List<ProductInfoView> products;

    public UserInfoView(String firstName, String lastName, Integer age, Integer productsCount, List<ProductInfoView> products) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.productsCount = productsCount;
        this.products = products;
    }

    public UserInfoView() {
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

    public Integer getProductsCount() {
        return productsCount;
    }

    public void setProductsCount(Integer productsCount) {
        this.productsCount = productsCount;
    }

    public List<ProductInfoView> getProducts() {
        return products;
    }

    public void setProducts(List<ProductInfoView> products) {
        this.products = products;
    }
}
