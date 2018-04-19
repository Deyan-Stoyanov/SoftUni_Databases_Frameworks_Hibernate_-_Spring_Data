package car.dealer.model.dto.importDtos;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "suppliers")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class SupplierWrapper {

    @XmlElement(name = "supplier")
    private List<SupplierImportDto> suppliers;

    public SupplierWrapper(List<SupplierImportDto> suppliers) {
        this.suppliers = suppliers;
    }

    public SupplierWrapper() {
    }

    public List<SupplierImportDto> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(List<SupplierImportDto> suppliers) {
        this.suppliers = suppliers;
    }
}
