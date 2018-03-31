package softuni.springadvancedquerying.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import softuni.springadvancedquerying.model.enums.Size;
import softuni.springadvancedquerying.model.labels.ClassicLabel;
import softuni.springadvancedquerying.model.shampoos.BasicShampoo;

import java.math.BigDecimal;
import java.util.Set;

@Repository
public interface ShampooRepository extends JpaRepository<BasicShampoo, Integer> {
    Set<BasicShampoo> findAllBySize(Size size);
    Set<BasicShampoo> findAllBySizeOrLabelOrderByPriceAsc(Size size, ClassicLabel label);
    Set<BasicShampoo> findAllByPriceGreaterThanEqualOrderByPriceAsc(BigDecimal price);
    int countSllByPriceLessThan(BigDecimal price);

    @Query(value = "select s FROM BasicShampoo AS s JOIN s.ingredients AS i where i.name in:ingredientList")
    Set<BasicShampoo> findAllByIngredientsIn(@Param(value = "ingredientList")Set<String> ingredientList);

    @Query(value = "SELECT sum(i.price) FROM BasicShampoo AS s JOIN s.ingredients AS i WHERE s.brand = :brand")
    BigDecimal findBasicShampooByBrand(@Param(value = "brand") String brand);
}
