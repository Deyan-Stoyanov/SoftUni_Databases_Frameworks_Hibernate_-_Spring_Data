package softuni.springadvancedquerying.model.shampoos;

import softuni.springadvancedquerying.model.enums.Size;
import softuni.springadvancedquerying.model.ingredients.BasicIngredient;
import softuni.springadvancedquerying.model.labels.ClassicLabel;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "basic_shampoos")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class BasicShampoo implements Shampoo{

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Basic
    private String brand;

    @OneToOne(optional = false)
    @JoinColumn(name = "label_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_label_shampoo"))
    private ClassicLabel label;

    @ManyToMany
    @JoinTable(name = "shampoos_ingredients",
        joinColumns = @JoinColumn(name = "shampoo_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "ingredient_id", referencedColumnName = "id"))
    private Set<BasicIngredient> ingredients;

    @Basic
    private BigDecimal price;

    @Basic
    private Size size;

    public BasicShampoo(String brand, BigDecimal price) {
        this.brand = brand;
        this.price = price;
    }

    public BasicShampoo(String brand, ClassicLabel label, Set<BasicIngredient> ingredients, BigDecimal price, Size size) {
        this.brand = brand;
        this.label = label;
        this.ingredients = ingredients;
        this.price = price;
        this.size = size;
    }

    public BasicShampoo() {
        this.ingredients = new HashSet<>();
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getBrand() {
        return brand;
    }

    @Override
    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public ClassicLabel getLabel() {
        return label;
    }

    @Override
    public void setLabel(ClassicLabel label) {
        this.label = label;
    }

    @Override
    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Set<BasicIngredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<BasicIngredient> ingredients) {
        this.ingredients = ingredients;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }
}
