package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "chemical_ingredients")
@PrimaryKeyJoinColumn(name = "id")
public abstract class BasicChemicalIngredient extends BasicIngredient implements ChemicalIngredients {
    @Column(name = "chemical_formula")
    protected String chemicalFormula;

    public BasicChemicalIngredient() { }

    public BasicChemicalIngredient(String chemicalFormula) {
        this.chemicalFormula = chemicalFormula;
    }

    public BasicChemicalIngredient(String name, BigDecimal price, String chemicalFormula) {
        super(name, price);
        this.chemicalFormula = chemicalFormula;
    }
    @Override
    public String getChemicalFormula() {
        return chemicalFormula;
    }
    @Override
    public void setChemicalFormula(String chemicalFormula) {
        this.chemicalFormula = chemicalFormula;
    }
}
