package softuni.springadvancedquerying.model.ingredients;

import java.math.BigDecimal;

public interface Ingredient {
    public int getId();

    public void setId(int id) ;

    public String getName() ;

    public void setName(String name);

    public BigDecimal getPrice();

    public void setPrice(BigDecimal price) ;
}
