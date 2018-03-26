package entities;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "credit_cards")
@PrimaryKeyJoinColumn(name = "id")
public class CreditCard extends BillingDetail {

    @Basic
    private String cardType;

    @Basic
    private int expirationMonth;

    @Basic
    private int expirationYear;

    public CreditCard(int number, User owner, String cardType, int expirationMonth, int expirationYear) {
        super(number, owner);
        this.cardType = cardType;
        this.expirationMonth = expirationMonth;
        this.expirationYear = expirationYear;
    }

    public CreditCard(String cardType, int expirationMonth, int expirationYear) {
        this.cardType = cardType;
        this.expirationMonth = expirationMonth;
        this.expirationYear = expirationYear;
    }

    public CreditCard(){

    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public int getExpirationMonth() {
        return expirationMonth;
    }

    public void setExpirationMonth(int expirationMonth) {
        this.expirationMonth = expirationMonth;
    }

    public int getExpirationYear() {
        return expirationYear;
    }

    public void setExpirationYear(int expirationYear) {
        this.expirationYear = expirationYear;
    }
}
