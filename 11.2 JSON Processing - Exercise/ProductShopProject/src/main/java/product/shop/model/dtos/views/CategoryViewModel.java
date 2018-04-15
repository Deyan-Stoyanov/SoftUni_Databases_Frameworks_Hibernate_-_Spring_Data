package product.shop.model.dtos.views;

import com.google.gson.annotations.Expose;

import java.math.BigDecimal;

public class CategoryViewModel {
    @Expose
    private String categoryName;
    @Expose
    private long productCount;
    @Expose
    private double averagePrice;
    @Expose
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
