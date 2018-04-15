package product.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import product.shop.model.dtos.views.CategoryViewModel;
import product.shop.model.entity.Category;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    List<Category> findAll();
    @Query(value = "select new product.shop.model.dtos.views.CategoryViewModel(c.name, count(p.id), avg(p.price), sum(p.price)) FROM Category AS c JOIN c.products AS p GROUP BY c.id")
    List<CategoryViewModel> getCategoriesByTotalProducts();
}
