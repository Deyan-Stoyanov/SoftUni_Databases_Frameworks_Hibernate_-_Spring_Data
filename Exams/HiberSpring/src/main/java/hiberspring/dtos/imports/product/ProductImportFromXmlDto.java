package hiberspring.dtos.imports.product;

import javax.validation.Valid;
import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductImportFromXmlDto {

    @XmlElement(name = "product")
    private List<ProductsImportFromXmlDto> products;

    public ProductImportFromXmlDto(List<ProductsImportFromXmlDto> products) {
        this.products = products;
    }

    public ProductImportFromXmlDto() {
    }

    public List<ProductsImportFromXmlDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductsImportFromXmlDto> products) {
        this.products = products;
    }
}
