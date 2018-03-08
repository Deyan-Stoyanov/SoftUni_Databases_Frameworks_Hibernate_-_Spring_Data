import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class UpperStrings {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] sArr = reader.readLine().split("\\s+");
        Arrays.stream(sArr).map(String::toUpperCase).forEach(x -> System.out.printf("%s ", x));
    }
}
