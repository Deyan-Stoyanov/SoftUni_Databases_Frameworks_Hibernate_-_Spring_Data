import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class reverseString {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sbf = new StringBuilder(reader.readLine());
        System.out.println(sbf.reverse());
    }
}
