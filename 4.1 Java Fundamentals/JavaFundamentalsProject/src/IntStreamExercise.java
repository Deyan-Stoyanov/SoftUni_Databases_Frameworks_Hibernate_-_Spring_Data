import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class IntStreamExercise {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] ints = {2,3,4,5};
        IntStream a = IntStream.of(ints);
        int max = a.max().getAsInt();
        System.out.println(max);
        List<Integer> list = new ArrayList<>();
        Stream<Integer> integerStream = list.stream();
    }
}
