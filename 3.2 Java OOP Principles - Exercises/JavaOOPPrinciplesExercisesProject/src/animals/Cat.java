package animals;

public class Cat extends Animal implements SoundProducible {

    public Cat(String name, int age, String gender) {
        super(name, age, gender);
    }

    @Override
    public void produceSound() {
        System.out.println("MiauMiau");
    }

    @Override
    public String toString() {
        return String.format("Cat%n%s %d %s", getName(), getAge(), getGender());
    }
}
