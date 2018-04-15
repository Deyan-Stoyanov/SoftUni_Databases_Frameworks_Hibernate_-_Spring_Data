package product.shop.model.dtos.views;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
@XmlRootElement(name = "categores")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class CategoriesWrapper {
    @XmlElement(name = "category")
    private List<CategoryViewModel> categories;

    public CategoriesWrapper(List<CategoryViewModel> categories) {
        this.categories = categories;
    }

    public CategoriesWrapper() {
    }

    public List<CategoryViewModel> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryViewModel> categories) {
        this.categories = categories;
    }
}
