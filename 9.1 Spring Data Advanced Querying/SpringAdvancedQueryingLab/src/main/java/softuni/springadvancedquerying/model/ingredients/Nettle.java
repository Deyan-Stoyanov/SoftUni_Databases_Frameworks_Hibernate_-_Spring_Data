package softuni.springadvancedquerying.model.ingredients;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "nettle")
@PrimaryKeyJoinColumn(name = "id")
@DiscriminatorValue(value = "Nettle")
public class Nettle extends BasicIngredient {

    public Nettle(String name, BigDecimal price) {
    }

    public Nettle() {
        super("Nettle", BigDecimal.valueOf(4.23));
    }
}
