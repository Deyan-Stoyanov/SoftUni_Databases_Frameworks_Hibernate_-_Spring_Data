package product.shop.model.dtos.bindings;

import product.shop.model.entity.Category;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "categories")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class CategoryCreateWrapper {

    @XmlElement(name = "category")
    private List<Category> categories;

    public CategoryCreateWrapper(List<Category> categories) {
        this.categories = categories;
    }

    public CategoryCreateWrapper() {
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
