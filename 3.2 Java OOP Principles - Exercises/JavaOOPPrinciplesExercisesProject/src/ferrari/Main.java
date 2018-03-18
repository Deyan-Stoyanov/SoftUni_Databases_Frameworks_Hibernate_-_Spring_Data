package ferrari;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String name = reader.readLine();
        Ferrari myFerrari = new Ferrari(name);
        System.out.printf("%s/%s/%s/%s", myFerrari.getMODEL(), myFerrari.brake(), myFerrari.gas(), myFerrari.getDriverName());
    }
}
