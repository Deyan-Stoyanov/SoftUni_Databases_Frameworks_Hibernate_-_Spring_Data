package car.dealer.model.dto.exportDtos;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "sales")
@XmlAccessorType(XmlAccessType.FIELD)
public class SaleExportWrapper {
    @XmlElement(name = "sale")
    private List<SaleViewModel> sales;

    public SaleExportWrapper(List<SaleViewModel> sales) {
        this.sales = sales;
    }

    public SaleExportWrapper() {
    }

    public List<SaleViewModel> getSales() {
        return sales;
    }

    public void setSales(List<SaleViewModel> sales) {
        this.sales = sales;
    }
}
