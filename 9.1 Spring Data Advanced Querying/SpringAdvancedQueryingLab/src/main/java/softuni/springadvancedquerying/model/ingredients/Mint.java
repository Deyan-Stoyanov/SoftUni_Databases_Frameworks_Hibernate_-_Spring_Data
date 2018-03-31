package softuni.springadvancedquerying.model.ingredients;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "mint")
@PrimaryKeyJoinColumn(name = "id")
@DiscriminatorValue(value = "Mint")
public class Mint extends BasicIngredient {

    public Mint(String name, BigDecimal price) {
    }

    public Mint() {
        super("Mint", BigDecimal.valueOf(2.34));
    }
}
