package car.dealer.model.dto.importDtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "customers")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class CustomerWrapper {
    @XmlElement(name = "customer")
    private List<CustomersXmlDto> customers;

    public CustomerWrapper(List<CustomersXmlDto> customers) {
        this.customers = customers;
    }

    public CustomerWrapper() {

    }

    public List<CustomersXmlDto> getCustomers() {
        return customers;
    }

    public void setCustomers(List<CustomersXmlDto> customers) {
        this.customers = customers;
    }
}
