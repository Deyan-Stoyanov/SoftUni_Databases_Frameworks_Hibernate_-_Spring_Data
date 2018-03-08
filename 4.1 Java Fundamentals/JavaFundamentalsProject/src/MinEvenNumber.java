import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Optional;
import java.util.OptionalDouble;

public class MinEvenNumber {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Optional<Double> min = Arrays.stream(reader.readLine()
                .split("\\s+"))
                .filter(x -> !x.isEmpty())
                .map(Double::valueOf)
                .filter(x -> x % 2 == 0)
                .min(Double::compare);
        if(min.isPresent()){
            System.out.printf("%.2f", min.get());
        } else {
            System.out.println("No match");
        }

    }
}
