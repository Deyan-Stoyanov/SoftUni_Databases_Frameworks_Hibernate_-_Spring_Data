package softuni.springadvancedquerying.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.springadvancedquerying.model.ingredients.BasicChemicalIngredient;

@Repository
public interface ChemicalIngredientRepository extends JpaRepository<BasicChemicalIngredient, Integer> {
}
