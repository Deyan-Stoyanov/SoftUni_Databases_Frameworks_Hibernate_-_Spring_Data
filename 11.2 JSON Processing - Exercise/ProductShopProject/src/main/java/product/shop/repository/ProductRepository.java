package product.shop.repository;

import org.hibernate.annotations.SQLInsert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import product.shop.model.dtos.views.ProductViewModel;
import product.shop.model.dtos.views.UserViewModel;
import product.shop.model.entity.Product;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findAll();
    @Query(value = "SELECT new product.shop.model.dtos.views.ProductViewModel(p.name, p.price, concat(COALESCE(p.seller.firstName, ''), ' ', p.seller.lastName)) FROM Product AS p WHERE (p.price BETWEEN :from AND :to) AND p.buyer IS NULL ORDER BY p.price ASC")
    List<ProductViewModel> getAllByRangeWithoutBuyer(@Param("from") BigDecimal from, @Param("to") BigDecimal to);

}
