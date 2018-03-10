import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;

public class FilterStudentsByPhone {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Map<String, String> students = new LinkedHashMap<>();
        String input = reader.readLine();
        while(!input.isEmpty() && !input.equalsIgnoreCase("END")){
            String[] data = input.split("\\s+");
            String phoneNumber = data[2];
            String name = (data[0] + " " + data[1]);
            students.putIfAbsent(name, phoneNumber);
            input = reader.readLine();
        }
        students.entrySet().stream()
                .filter(x -> (x.getValue()
                        .substring(0, 2)
                        .equalsIgnoreCase("02")
                        || x.getValue()
                        .substring(0, 5)
                        .equalsIgnoreCase("+3592")))
                .forEach(x -> System.out.println(x.getKey()));
    }
}
