package person;

public class Citizen implements Person, Birthable, Identifiable {
    int age;
    String name;
    String id;
    String birthDate;

    public Citizen(String name, int age, String id, String birthDate) {
        this.age = age;
        this.name = name;
        this.id = id;
        this.birthDate = birthDate;
    }

    public Citizen(String name, int age) {
        this.age = age;
        this.name = name;
    }

    @Override
    public int getAge() {
        return this.age;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getBirthDate() {
        return this.birthDate;
    }

    @Override
    public String getId() {
        return this.id;
    }
}
