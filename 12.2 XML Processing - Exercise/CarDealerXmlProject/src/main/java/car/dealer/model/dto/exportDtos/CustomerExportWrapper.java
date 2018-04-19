package car.dealer.model.dto.exportDtos;

import car.dealer.model.entity.Customer;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "customers")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class CustomerExportWrapper {
    @XmlElement(name = "customer")
    private List<Customer> customers;

    public CustomerExportWrapper(List<Customer> customers) {
        this.customers = customers;
    }

    public CustomerExportWrapper() {
    }


    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }
}
