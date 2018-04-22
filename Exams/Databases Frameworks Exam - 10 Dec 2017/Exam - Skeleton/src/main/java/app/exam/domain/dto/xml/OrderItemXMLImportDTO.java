package app.exam.domain.dto.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(value = XmlAccessType.FIELD)
public class OrderItemXMLImportDTO {

    @XmlElement
    private String name;
    @XmlElement
    private Integer quantity;

    public OrderItemXMLImportDTO(String name, Integer quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public OrderItemXMLImportDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
