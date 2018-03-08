import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

public class AverageOfDoubles {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();
        if(!s.isEmpty()){
            double[] nums = Arrays.stream(s.split("\\s+")).mapToDouble(Double::parseDouble).toArray();
            OptionalDouble average = DoubleStream.of(nums).average();
            if(average.isPresent()){
                System.out.printf("%.2f",average.getAsDouble());
            } else{
                System.out.println("No match");
            }
        } else {
            System.out.println("No match");
        }
    }
}
