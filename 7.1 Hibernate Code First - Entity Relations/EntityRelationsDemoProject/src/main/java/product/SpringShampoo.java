package product;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue(value = "SpringShampoo")
public class SpringShampoo extends BasicShampoo {

    private static final String NAME = "Spring Shampoo";
    private static final BigDecimal PRICE = BigDecimal.valueOf(2.56);

    public SpringShampoo(String name, ClassicLabel label, ProductionBatch batch,  BigDecimal price) {
        super(name, label, batch, price);
    }

    public SpringShampoo() {
        super(NAME, PRICE);
    }
}
