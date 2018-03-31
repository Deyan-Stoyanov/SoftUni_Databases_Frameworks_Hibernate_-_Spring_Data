package softuni.springadvancedquerying.model.ingredients;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "ammonium_chloride")
@PrimaryKeyJoinColumn(name = "id")
@DiscriminatorValue(value = "NH4Cl")
public class AmoniumChloride extends BasicChemicalIngredient {

    public AmoniumChloride(){
        super("Amonium Chloride", BigDecimal.valueOf(5.12), "NH4Cl");
    }
    public AmoniumChloride(String chemicalFormula) {
        super(chemicalFormula);
    }

    public AmoniumChloride(String name, BigDecimal price, String chemicalFormula) {
        super(name, price, chemicalFormula);
    }
}
