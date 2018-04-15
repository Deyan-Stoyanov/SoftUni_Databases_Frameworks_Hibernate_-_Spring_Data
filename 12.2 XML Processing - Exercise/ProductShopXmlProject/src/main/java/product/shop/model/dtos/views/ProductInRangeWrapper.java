package product.shop.model.dtos.views;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "products")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class ProductInRangeWrapper {
    @XmlElement(name = "product")
    private List<ProductViewModel> products;

    public ProductInRangeWrapper(List<ProductViewModel> products) {
        this.products = products;
    }

    public ProductInRangeWrapper() {
    }

    public List<ProductViewModel> getProducts() {
        return products;
    }

    public void setProducts(List<ProductViewModel> products) {
        this.products = products;
    }
}
