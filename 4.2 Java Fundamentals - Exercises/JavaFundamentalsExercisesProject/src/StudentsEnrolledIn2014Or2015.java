import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class StudentsEnrolledIn2014Or2015 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = reader.readLine();
        Map<String, List<Integer>> students = new LinkedHashMap<>();
        while(!input.isEmpty() && !input.equalsIgnoreCase("END")){
            String[] data = input.split("\\s+");
            String facultyNumber = data[0];
            students.putIfAbsent(facultyNumber, new ArrayList<>());
            for (int i = 1; i < data.length; i++) {
                students.get(facultyNumber).add(Integer.parseInt(data[i]));
            }
            input = reader.readLine();
        }
        students.entrySet().stream()
                .filter(x -> x.getKey().substring(4, 6)
                        .equalsIgnoreCase("14")
                        || x.getKey().substring(4, 6)
                        .equalsIgnoreCase("15"))
                .forEach(x -> {
                    x.getValue().forEach(y -> System.out.print(y + " "));
                    System.out.println();
                });
    }
}
