package car.dealer.model.dto.exportDtos;

import com.google.gson.annotations.Expose;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import java.math.BigDecimal;
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerViewModel {
    @XmlAttribute(name = "full-name")
    private
    String fullName;
    @XmlAttribute(name = "bought-cars")
    private
    Long boughtCars;
    @XmlAttribute(name = "spent-money")
    private
    BigDecimal spentMoney;

    public CustomerViewModel(String fullName, Long boughtCars, BigDecimal spentMoney) {
        this.fullName = fullName;
        this.boughtCars = boughtCars;
        this.spentMoney = spentMoney;
    }

    public CustomerViewModel() {
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Long getBoughtCars() {
        return boughtCars;
    }

    public void setBoughtCars(Long boughtCars) {
        this.boughtCars = boughtCars;
    }

    public BigDecimal getSpentMoney() {
        return spentMoney;
    }

    public void setSpentMoney(BigDecimal spentMoney) {
        this.spentMoney = spentMoney;
    }
}


