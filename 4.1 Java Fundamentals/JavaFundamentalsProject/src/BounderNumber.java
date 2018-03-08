import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BounderNumber {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<Integer> bounds = Arrays.stream(reader.readLine()
                .split("\\s+"))
                .map(Integer::valueOf)
                .sorted(Integer::compareTo)
                .collect(Collectors.toList());
        int lowerBounder = bounds.get(0);
        int upperBounder = bounds.get(1);
        List<Integer> numbers = Arrays.stream(reader.readLine()
                .split("\\s+"))
                .map(Integer::valueOf)
                .filter(x -> x >= lowerBounder && x <= upperBounder)
                .collect(Collectors.toList());
        if(numbers.size() != 0){
            numbers.forEach(x -> System.out.printf("%d ", x));
        }
    }
}
