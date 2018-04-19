package car.dealer.model.dto.exportDtos;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerSalesWrapper {
    @XmlElement(name = "customer")
    private List<CustomerViewModel> customers;

    public CustomerSalesWrapper(List<CustomerViewModel> customers) {
        this.customers = customers;
    }

    public CustomerSalesWrapper() {
    }

    public List<CustomerViewModel> getCustomers() {
        return customers;
    }

    public void setCustomers(List<CustomerViewModel> customers) {
        this.customers = customers;
    }
}
