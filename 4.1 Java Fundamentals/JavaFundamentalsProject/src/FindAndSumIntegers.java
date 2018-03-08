import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Optional;


public class FindAndSumIntegers {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Optional<Integer> sum = Arrays.stream(reader.readLine().split("\\s+"))
                .filter(x->{
                    try{
                        int a = Integer.parseInt(x);
                        return true;
                    } catch (Exception e) {
                        return false;
                    }
                })
                .map(Integer::valueOf)
                .reduce((x1, x2) -> x1 + x2);
        if(sum.isPresent()){
            System.out.println(sum.get());
        } else {
            System.out.println("No match");
        }
    }
}
