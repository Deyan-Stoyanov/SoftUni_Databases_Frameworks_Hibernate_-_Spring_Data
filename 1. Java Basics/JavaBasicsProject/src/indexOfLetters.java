import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class indexOfLetters {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String str = reader.readLine();
        for (char c:
             str.toCharArray()) {
            System.out.printf("%c -> %d", c, (c - 97));
            System.out.println();
        }
    }
}
