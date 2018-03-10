import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class StudentsByFirstAndLastName {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<String> students = new ArrayList<>();
        String input = reader.readLine();
        while (!input.isEmpty() && !input.equalsIgnoreCase("END")){
            students.add(input);
            input = reader.readLine();
        }
        students.stream()
                .filter((x)-> (x.substring(0, x.indexOf(' ')).compareTo(x.substring(x.indexOf(' ') + 1))) < 0)
                .forEach(System.out::println);
    }
}
