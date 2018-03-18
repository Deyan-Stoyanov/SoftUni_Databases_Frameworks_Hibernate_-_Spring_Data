package animals;

public class Tomcat extends Cat implements SoundProducible {

    public Tomcat(String name, int age, String gender) {
        super(name, age, gender);
    }

    @Override
    public void produceSound(){
        System.out.println("Give me one million b***h");
    }

    @Override
    public String toString() {
        return String.format("Tomcat%n%s %d %s", getName(), getAge(), getGender());
    }
}
