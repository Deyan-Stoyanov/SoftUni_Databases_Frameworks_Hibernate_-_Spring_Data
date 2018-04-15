package product.shop.service;

import org.springframework.stereotype.Service;
import product.shop.model.dtos.bindings.ProductCreateBindingModel;
import product.shop.model.dtos.views.ProductViewModel;
import product.shop.model.entity.Product;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

public interface ProductService {
    List<Product> findAll();
    void save(ProductCreateBindingModel model);
    void save(Collection<ProductCreateBindingModel> models);
    List<ProductViewModel> getAllByRangeWithoutBuyer(BigDecimal from, BigDecimal to);

}
