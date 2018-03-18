package animals;

public class Dog extends Animal implements SoundProducible {

    public Dog(String name, int age, String gender) {
        super(name, age, gender);
    }

    @Override
    public void produceSound() {
        System.out.println("BauBau");
    }

    @Override
    public String toString() {
        return String.format("Dog%n%s %d %s", getName(), getAge(), getGender());
    }
}
