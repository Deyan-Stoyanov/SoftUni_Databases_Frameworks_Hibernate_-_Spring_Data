package car.dealer.model.dto.exportDtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "suppliers")
@XmlAccessorType(XmlAccessType.FIELD)
public class SupplierExportWrapper {
    @XmlElement(name = "supplier")
    private List<SupplierDto> suppliers;

    public SupplierExportWrapper(List<SupplierDto> suppliers) {
        this.suppliers = suppliers;
    }

    public SupplierExportWrapper() {
    }

    public List<SupplierDto> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(List<SupplierDto> suppliers) {
        this.suppliers = suppliers;
    }
}

