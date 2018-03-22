package product;

import java.math.BigDecimal;

public interface Shampoo {
     int getId();

     String getName();

     void setName(String name);

     ClassicLabel getLabel() ;

     void setLabel(ClassicLabel label);

     BigDecimal getPrice();

     void setPrice(BigDecimal price);

     ProductionBatch getBatch();

     void setBatch(ProductionBatch batch);
}
