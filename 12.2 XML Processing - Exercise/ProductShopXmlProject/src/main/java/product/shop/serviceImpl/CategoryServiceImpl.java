package product.shop.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import product.shop.model.dtos.views.CategoryViewModel;
import product.shop.model.entity.Category;
import product.shop.repository.CategoryRepository;
import product.shop.service.CategoryService;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> findAll() {
        return this.categoryRepository.findAll();
    }

    @Override
    public Category getOne(int id) {
        return this.categoryRepository.getOne(id);
    }

    @Override
    public void save(Category category) {
        this.categoryRepository.saveAndFlush(category);
    }

    @Override
    public void save(Collection<Category> categories) {
        this.categoryRepository.saveAll(categories);

    }

    @Override
    public List<CategoryViewModel> getCategoriesByTotalProducts() {
        return this.categoryRepository.getCategoriesByTotalProducts();
    }
}
