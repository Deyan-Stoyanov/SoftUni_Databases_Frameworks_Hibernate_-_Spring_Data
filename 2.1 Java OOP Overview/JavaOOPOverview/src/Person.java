import java.util.ArrayList;
import java.util.List;

public class Person {
    private String name;
    private int age;
    private List<BankAccount> listOfAccounts;

    public Person(String name, int age, List<BankAccount> listOfAccounts) {
        this.name = name;
        this.age = age;
        this.listOfAccounts = listOfAccounts;
    }

    public Person(String name, int age) {
        this(name, age, new ArrayList<>());
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<BankAccount> getListOfAccounts() {
        return this.listOfAccounts;
    }

    public void setListOfAccounts(List<BankAccount> listOfAccounts) {
        this.listOfAccounts = listOfAccounts;
    }

    public double getBalance() {
        return this.listOfAccounts.stream().mapToDouble(x -> x.getBalance()).sum();
    }
}

