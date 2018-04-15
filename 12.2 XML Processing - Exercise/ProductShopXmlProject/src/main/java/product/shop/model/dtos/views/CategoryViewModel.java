package product.shop.model.dtos.views;

import com.google.gson.annotations.Expose;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.math.BigDecimal;
@XmlAccessorType(value = XmlAccessType.FIELD)
public class CategoryViewModel {
    @XmlAttribute
    private String categoryName;
    @XmlElement
    private long productCount;
    @XmlElement
    private double averagePrice;
    @XmlElement
    private BigDecimal totalRevenue;

    public CategoryViewModel(String categoryName, long productCount, double averagePrice, BigDecimal totalRevenue) {
        this.categoryName = categoryName;
        this.productCount = productCount;
        this.averagePrice = averagePrice;
        this.totalRevenue = totalRevenue;
    }

    public CategoryViewModel() {
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public long getProductCount() {
        return productCount;
    }

    public void setProductCount(long productCount) {
        this.productCount = productCount;
    }

    public double getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(double averagePrice) {
        this.averagePrice = averagePrice;
    }

    public BigDecimal getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(BigDecimal totalRevenue) {
        this.totalRevenue = totalRevenue;
    }
}
