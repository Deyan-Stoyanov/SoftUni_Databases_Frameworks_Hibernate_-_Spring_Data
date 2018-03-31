package softuni.springadvancedquerying.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import softuni.springadvancedquerying.model.ingredients.BasicIngredient;

import javax.transaction.Transactional;
import java.util.Set;

@Repository
public interface BasicIngredientRepository extends JpaRepository<BasicIngredient, Integer> {
    Set<BasicIngredient> findAllByNameStartingWith(String letter);

    Set<BasicIngredient> findAllByNameIsInOrderByPriceAsc(Set<String> words);

    @Query(value = "DELETE FROM BasicIngredient AS b WHERE b.name = :name")
    void removeBasicIngredientByName(@Param(value = "name") String name);

    @Transactional
    @Modifying
    @Query(value = "UPDATE BasicIngredient AS b SET b.price = (b.price * 1.1) WHERE b.name IN :names")
    void updatePriceByName(@Param(value = "names")Set<String> names);

}