package car.dealer.model.dto;

import com.google.gson.annotations.Expose;

import java.math.BigDecimal;

public class SaleViewModel {
    @Expose
    private CarForSaleDto car;
    @Expose
    private String customerName;
    @Expose
    private BigDecimal price;
    @Expose
    private BigDecimal priceWithDiscount;

    public SaleViewModel(CarForSaleDto car, String customerName, BigDecimal price, BigDecimal priceWithDiscount) {
        this.car = car;
        this.customerName = customerName;
        this.price = price;
        this.priceWithDiscount = priceWithDiscount;
    }

    public SaleViewModel() {
    }

    public CarForSaleDto getCar() {
        return car;
    }

    public void setCar(CarForSaleDto car) {
        this.car = car;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPriceWithDiscount() {
        return priceWithDiscount;
    }

    public void setPriceWithDiscount(BigDecimal priceWithDiscount) {
        this.priceWithDiscount = priceWithDiscount;
    }
}
