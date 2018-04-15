package product.shop.model.dtos.bindings;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "products")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class ProductCreateWrapper {

    @XmlElement(name = "product")
    private List<ProductCreateBindingModel> products;

    public ProductCreateWrapper(List<ProductCreateBindingModel> products) {
        this.products = products;
    }

    public ProductCreateWrapper() {
    }

    public List<ProductCreateBindingModel> getProducts() {
        return products;
    }

    public void setProducts(List<ProductCreateBindingModel> products) {
        this.products = products;
    }
}
