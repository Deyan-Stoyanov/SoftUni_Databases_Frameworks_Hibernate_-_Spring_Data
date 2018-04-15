package product.shop.model.dtos.views;

import com.google.gson.annotations.Expose;

import java.math.BigDecimal;

public class ProductInfoView {
    @Expose
    private String name;
    @Expose
    private BigDecimal price;

    public ProductInfoView(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public ProductInfoView() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
