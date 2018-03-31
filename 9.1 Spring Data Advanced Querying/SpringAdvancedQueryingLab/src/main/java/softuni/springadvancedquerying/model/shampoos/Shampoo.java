package softuni.springadvancedquerying.model.shampoos;

import softuni.springadvancedquerying.model.labels.ClassicLabel;

import java.math.BigDecimal;

public interface Shampoo {
     int getId();

     String getBrand();

     void setBrand(String brand);

     ClassicLabel getLabel() ;

     void setLabel(ClassicLabel label);

     BigDecimal getPrice();

     void setPrice(BigDecimal price);
}
