package animals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String type = reader.readLine();
            if (type.equalsIgnoreCase("Beast!")) {
                break;
            }
            String[] commands = reader.readLine().split("\\s+");
            String name = commands[0];
            int age = Integer.parseInt(commands[1]);
            String gender = commands[2];
            try {
                switch (type.toUpperCase()) {
                    case "DOG":
                        Dog dog = new Dog(name, age, gender);
                        System.out.println(dog.toString());
                        dog.produceSound();
                        break;
                    case "CAT":
                        Cat cat = new Cat(name, age, gender);
                        System.out.println(cat.toString());
                        cat.produceSound();
                        break;
                    case "FROG":
                        Frog frog = new Frog(name, age, gender);
                        System.out.println(frog.toString());
                        frog.produceSound();
                        break;
                    case "KITTEN":
                        if (!gender.equalsIgnoreCase("Female")) {
                            System.out.println("Invalid type!");
                        } else {
                            Kitten kitten = new Kitten(name, age, gender);
                            System.out.println(kitten.toString());
                            kitten.produceSound();
                        }
                        break;
                    case "TOMCAT":
                        if (!gender.equalsIgnoreCase("Male")) {
                            System.out.println("Invalid type!");
                        } else {
                            Tomcat tomcat = new Tomcat(name, age, gender);
                            System.out.println(tomcat.toString());
                            tomcat.produceSound();
                        }
                        break;
                }
            } catch (IllegalArgumentException ie){
                System.out.println(ie.getMessage());
            }
        }
    }
}
