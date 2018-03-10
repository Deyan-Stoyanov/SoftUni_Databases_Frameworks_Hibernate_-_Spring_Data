import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SortStudents {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = reader.readLine();
        List<String> students = new ArrayList<>();
        while(!input.isEmpty() && !input.equalsIgnoreCase("END")){
            students.add(input);
            input = reader.readLine();
        }
        students.stream()
                .sorted((x1, x2) -> {
                    if(x1.substring(x1.indexOf(' ') + 1).compareTo(x2.substring(x2.indexOf(' ') + 1)) == 0){
                        return x2.substring(0, x2.indexOf(' ')).compareTo(x1.substring(0, x1.indexOf(' ')));
                    }
                    return x1.substring(x1.indexOf(' ') + 1).compareTo(x2.substring(x2.indexOf(' ') + 1));
                }).forEach(System.out::println);
    }
}
