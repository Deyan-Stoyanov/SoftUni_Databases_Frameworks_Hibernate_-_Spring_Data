package animals;

public class Kitten extends Cat implements SoundProducible {
    public Kitten(String name, int age, String gender) {
        super(name, age, gender);
    }

    @Override
    public void produceSound(){
        System.out.println("Miau");
    }

    @Override
    public String toString() {
        return String.format("Kitten%n%s %d %s", getName(), getAge(), getGender());
    }
}
