package model;

import javax.persistence.Column;

public interface ChemicalIngredients extends Ingredients {
    public String getChemicalFormula() ;

    public void setChemicalFormula(String chemicalFormula) ;
}
