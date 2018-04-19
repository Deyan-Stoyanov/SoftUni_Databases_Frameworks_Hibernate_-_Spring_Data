package car.dealer.model.dto.exportDtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.math.BigDecimal;
@XmlAccessorType(XmlAccessType.FIELD)
public class SaleViewModel {
    @XmlElement
    private CarForSaleDto car;
    @XmlElement(name = "customer-name")
    private String customerName;
    @XmlElement(name = "price")
    private BigDecimal price;
    @XmlElement(name = "price-with-discount")
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
