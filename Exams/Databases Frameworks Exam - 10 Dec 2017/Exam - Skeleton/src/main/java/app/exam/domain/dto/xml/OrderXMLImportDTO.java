package app.exam.domain.dto.xml;

import javax.validation.Valid;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;

@XmlAccessorType(value = XmlAccessType.FIELD)
public class OrderXMLImportDTO{
    @XmlElement
    private String customer;
    @XmlElement
    private String employee;
    @XmlElement
    private String date;
    @XmlElement
    private String type;
    @XmlElementWrapper(name = "items")
    @XmlElement(name = "item")
    @Valid
    private List<OrderItemXMLImportDTO> items;

    public OrderXMLImportDTO(String customer, String employee, String date, String type, List<OrderItemXMLImportDTO> items) {
        this.customer = customer;
        this.employee = employee;
        this.date = date;
        this.type = type;
        this.items = items;
    }

    public OrderXMLImportDTO() {
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<OrderItemXMLImportDTO> getItems() {
        return items;
    }

    public void setItems(List<OrderItemXMLImportDTO> items) {
        this.items = items;
    }
}

