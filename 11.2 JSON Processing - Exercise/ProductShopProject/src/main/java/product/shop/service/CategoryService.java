package product.shop.service;

import org.springframework.stereotype.Service;
import product.shop.model.dtos.views.CategoryViewModel;
import product.shop.model.entity.Category;

import java.util.Collection;
import java.util.List;

public interface CategoryService {
    List<Category> findAll();
    Category getOne(int id);
    void save(Category category);
    void save(Collection<Category> categories);
    List<CategoryViewModel> getCategoriesByTotalProducts();

}
