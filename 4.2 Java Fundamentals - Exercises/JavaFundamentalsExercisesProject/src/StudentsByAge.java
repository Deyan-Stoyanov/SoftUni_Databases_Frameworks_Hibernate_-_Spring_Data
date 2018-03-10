import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class StudentsByAge {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Map<String, Integer> students = new LinkedHashMap<>();
        String input = reader.readLine();
        while(!input.isEmpty() && !input.equalsIgnoreCase("END")){
            String[] data = input.split("\\s+");
            int age = Integer.parseInt(data[2]);
            String name = (data[0] + " " + data[1]);
            students.putIfAbsent(name, age);
            input = reader.readLine();
        }
        students.entrySet().stream()
                .filter(x -> (x.getValue() >= 18 && x.getValue() <= 24))
                .forEach(s -> System.out.printf("%s %d%n", s.getKey(), s.getValue()));
    }
}
