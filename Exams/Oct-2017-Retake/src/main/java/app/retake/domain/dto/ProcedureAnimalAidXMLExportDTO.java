package app.retake.domain.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import java.math.BigDecimal;

@XmlAccessorType(value = XmlAccessType.FIELD)
public class ProcedureAnimalAidXMLExportDTO {
    @XmlAttribute
    private String name;
    @XmlAttribute
    private BigDecimal price;

    public ProcedureAnimalAidXMLExportDTO(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public ProcedureAnimalAidXMLExportDTO() {
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
