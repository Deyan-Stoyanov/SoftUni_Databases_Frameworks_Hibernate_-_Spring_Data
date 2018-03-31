package softuni.springadvancedquerying.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import softuni.springadvancedquerying.model.ingredients.BasicIngredient;
import softuni.springadvancedquerying.model.ingredients.Ingredient;

import java.util.Set;

@NoRepositoryBean
public interface IngredientBaseRepository extends JpaRepository<Ingredient, Integer> {

}
