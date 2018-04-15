package product.shop.model.dtos.bindings;

import com.google.gson.annotations.Expose;

import java.math.BigDecimal;

public class ProductCreateBindingModel {
    @Expose
    private String name;
    @Expose
    private BigDecimal price;
    @Expose
    private Integer buyer;
    @Expose
    private Integer seller;

    public ProductCreateBindingModel(String name, BigDecimal price, Integer buyer, Integer seller) {
        this.name = name;
        this.price = price;
        this.buyer = buyer;
        this.seller = seller;
    }

    public ProductCreateBindingModel() {
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

    public Integer getBuyer() {
        return buyer;
    }

    public void setBuyer(Integer buyer) {
        this.buyer = buyer;
    }

    public Integer getSeller() {
        return seller;
    }

    public void setSeller(Integer seller) {
        this.seller = seller;
    }
}
