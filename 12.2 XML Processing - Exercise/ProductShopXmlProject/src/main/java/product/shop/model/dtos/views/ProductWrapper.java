package product.shop.model.dtos.views;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

@XmlAccessorType(value = XmlAccessType.FIELD)
public class ProductWrapper {
    @XmlAttribute
    private int count;
    @XmlElement(name = "product")
    private List<ProductOfUserViewModel> models;

    public ProductWrapper(List<ProductOfUserViewModel> models) {
        this.count = this.models.size();
        this.models = models;
    }

    public ProductWrapper() {
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<ProductOfUserViewModel> getModels() {
        return models;
    }

    public void setModels(List<ProductOfUserViewModel> models) {
        this.models = models;
    }
}
