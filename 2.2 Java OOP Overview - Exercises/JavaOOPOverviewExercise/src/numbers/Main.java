package numbers;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        double n = Double.parseDouble(reader.readLine());
        Reversed reversed = new Reversed(n);
        reversed.setReverced();
        System.out.println(reversed.getReverced());
    }
}
