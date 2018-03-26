package entities;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "bank_accounts")
@PrimaryKeyJoinColumn(name = "id")
public class BankAccount extends BillingDetail {

    @Basic
    private String bankName;

    @Basic
    private String swiftCode;

    public BankAccount(int number, User owner, String bankName, String swiftCode) {
        super(number, owner);
        this.bankName = bankName;
        this.swiftCode = swiftCode;
    }

    public BankAccount(String bankName, String swiftCode) {
        this.bankName = bankName;
        this.swiftCode = swiftCode;
    }

    public BankAccount() {

    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getSwiftCode() {
        return swiftCode;
    }

    public void setSwiftCode(String swiftCode) {
        this.swiftCode = swiftCode;
    }
}
