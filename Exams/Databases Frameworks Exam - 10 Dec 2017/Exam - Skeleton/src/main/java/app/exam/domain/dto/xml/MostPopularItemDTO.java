package app.exam.domain.dto.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.math.BigDecimal;

@XmlAccessorType(value = XmlAccessType.FIELD)
public class MostPopularItemDTO {
    @XmlElement
    private String name;
    @XmlElement(name = "total-made")
    private BigDecimal totalMade;
    @XmlElement(name = "times-sold")
    private Long timesSold;

    public MostPopularItemDTO(String name, BigDecimal totalMade, Long timesSold) {
        this.name = name;
        this.totalMade = totalMade;
        this.timesSold = timesSold;
    }

    public MostPopularItemDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getTotalMade() {
        return totalMade;
    }

    public void setTotalMade(BigDecimal totalMade) {
        this.totalMade = totalMade;
    }

    public Long getTimesSold() {
        return timesSold;
    }

    public void setTimesSold(Long timesSold) {
        this.timesSold = timesSold;
    }
}
