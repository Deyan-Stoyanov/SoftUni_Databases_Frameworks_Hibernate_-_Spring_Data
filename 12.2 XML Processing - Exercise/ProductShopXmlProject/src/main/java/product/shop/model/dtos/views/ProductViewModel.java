package product.shop.model.dtos.views;

import com.google.gson.annotations.Expose;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.math.BigDecimal;
@XmlAccessorType(value = XmlAccessType.FIELD)
public class ProductViewModel implements Serializable {
    @XmlElement(name = "name")
    private String name;
    @XmlElement(name = "price")
    private BigDecimal price;
    @XmlElement(name = "seller")
    private String sellerFullName;

    public ProductViewModel(String name, BigDecimal price, String sellerFullName) {
        this.name = name;
        this.price = price;
        this.sellerFullName = sellerFullName;
    }

    public ProductViewModel() {
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

    public String getSellerFullName() {
        return sellerFullName;
    }

    public void setSellerFullName(String sellerFullName) {
        this.sellerFullName = sellerFullName;
    }
}
