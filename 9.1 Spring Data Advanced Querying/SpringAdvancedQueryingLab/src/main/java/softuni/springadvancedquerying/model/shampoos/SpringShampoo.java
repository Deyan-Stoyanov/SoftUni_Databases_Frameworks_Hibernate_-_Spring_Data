package softuni.springadvancedquerying.model.shampoos;

import softuni.springadvancedquerying.model.enums.Size;
import softuni.springadvancedquerying.model.ingredients.BasicIngredient;
import softuni.springadvancedquerying.model.labels.ClassicLabel;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@DiscriminatorValue(value = "SpringShampoo")
public class SpringShampoo extends BasicShampoo {

    private static final String NAME = "Spring Shampoo";
    private static final BigDecimal PRICE = BigDecimal.valueOf(2.56);

    public SpringShampoo(String brand, BigDecimal price) {
        super(brand, price);
    }

    public SpringShampoo(String brand, ClassicLabel label, Set<BasicIngredient> ingredients, BigDecimal price, Size size) {
        super(brand, label, ingredients, price, size);
    }

    public SpringShampoo() {
        super(NAME, PRICE);
    }
}
