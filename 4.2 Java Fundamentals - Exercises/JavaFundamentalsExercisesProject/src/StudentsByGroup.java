import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class StudentsByGroup {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = reader.readLine();
        Map<Integer, ArrayList<String>> students = new HashMap<>();
        while(!input.isEmpty() && !input.equalsIgnoreCase("END")){
            String[] data = input.split("\\s+");
            int group = Integer.parseInt(data[2]);
            String name = (data[0] + " " + data[1]);
            students.putIfAbsent(group, new ArrayList<String>());
            students.get(group).add(name);
            input = reader.readLine();
        }

        students.entrySet().stream()
                .filter(x -> x.getKey() == 2)
                .forEach(x -> x.getValue().stream()
                        .sorted(Comparator.comparing(a -> a.substring(0, a.indexOf(' '))))
                        .forEach(System.out::println));
    }
}
