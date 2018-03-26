package Entities;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Entity
@Table(name = "wizard_deposits")
public class WizardDeposits {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 50, name = "first_name")
    private String first_name;

    @Column(length = 60, name = "last_name")
    private String last_name;

    @Column(length = 1000, name = "notes")
    private String notes;

    @Column(name = "age")
    private int age;

    @Column(length = 100, name = "magic_wand_creator")
    private String magicWandCreator;

    @Column(name = "magic_wand_size")
    private BigInteger magicWandSize;

    @Column(name = "deposit_group", length = 20)
    private String depositGroup;

    @Column(name = "deposit_start_date")
    private LocalDateTime startDate;

    @Column(name = "deposit_amount")
    private double depositAmount;

    @Column(name = "deposit_interest")
    private double depositInterest;

    @Column(name = "deposit_charge")
    private double depositCharge;

    @Column(name = "deposit_expiration_date")
    private LocalDateTime expirationDate;

    @Column(name = "is_deposit_expired")
    private boolean isDepositExpired;

    public WizardDeposits(String first_name, String last_name, String notes, int age, String magicWandCreator, BigInteger magicWandSize, String depositGroup, LocalDateTime startDate, double depositAmount, double depositInterest, double depositCharge, LocalDateTime expirationDate, boolean isDepositExpired) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.notes = notes;
        this.age = age;
        this.magicWandCreator = magicWandCreator;
        this.magicWandSize = magicWandSize;
        this.depositGroup = depositGroup;
        this.startDate = startDate;
        this.depositAmount = depositAmount;
        this.depositInterest = depositInterest;
        this.depositCharge = depositCharge;
        this.expirationDate = expirationDate;
        this.isDepositExpired = isDepositExpired;
    }

    public WizardDeposits() {
    }

    public int getId() {
        return id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getMagicWandCreator() {
        return magicWandCreator;
    }

    public void setMagicWandCreator(String magicWandCreator) {
        this.magicWandCreator = magicWandCreator;
    }

    public BigInteger getMagicWandSize() {
        return magicWandSize;
    }

    public void setMagicWandSize(BigInteger magicWandSize) {
        this.magicWandSize = magicWandSize;
    }

    public String getDepositGroup() {
        return depositGroup;
    }

    public void setDepositGroup(String depositGroup) {
        this.depositGroup = depositGroup;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public double getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(double depositAmount) {
        this.depositAmount = depositAmount;
    }

    public double getDepositInterest() {
        return depositInterest;
    }

    public void setDepositInterest(double depositInterest) {
        this.depositInterest = depositInterest;
    }

    public double getDepositCharge() {
        return depositCharge;
    }

    public void setDepositCharge(double depositCharge) {
        this.depositCharge = depositCharge;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    public boolean isDepositExpired() {
        return isDepositExpired;
    }

    public void setDepositExpired(boolean depositExpired) {
        isDepositExpired = depositExpired;
    }
}
