package product.shop.model.dtos.bindings;

import com.google.gson.annotations.Expose;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.math.BigDecimal;

@XmlAccessorType(value = XmlAccessType.FIELD)
public class ProductCreateBindingModel {
    @XmlElement(name = "name")
    private String name;
    @XmlElement(name = "price")
    private BigDecimal price;
    private Integer buyer;
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
