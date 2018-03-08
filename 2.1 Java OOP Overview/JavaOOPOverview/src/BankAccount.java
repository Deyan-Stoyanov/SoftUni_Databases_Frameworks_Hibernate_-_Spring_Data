public class BankAccount {
    private int id;
    private double balance;
    private final static double DEFAULT_INTEREST = 0.02;
    private double interestRate = DEFAULT_INTEREST;
    private int baCount = 0;

    public BankAccount(int id, double balance) {
        this.id = id;
        this.balance = balance;
    }

    public BankAccount(){
        this.id = ++baCount;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getBalance() {
        return this.balance;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public  double getInterest(int years){
        return years * getInterestRate();
    }

    public void deposit(double amount) {
        if(amount > 0){
            this.balance += amount;
        } else {
            System.out.println("You can't work with negative amounts");
        }
    }

    public void withdraw(double amount) {
        if(amount > 0 && amount <= this.balance) {
            this.balance -= amount;
        } else {
            System.out.println("Insufficient balance");
        }
    }

    @Override
    public String toString() {
        return String.format("Your bank account with ID %d and balance %.2f", this.id, this.balance);
    }
}