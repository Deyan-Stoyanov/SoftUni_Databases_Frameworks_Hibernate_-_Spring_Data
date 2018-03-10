import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class FilterStudentsByDomain {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Map<String, String> students = new LinkedHashMap<>();
        String input = reader.readLine();
        while(!input.isEmpty() && !input.equalsIgnoreCase("END")){
            String[] data = input.split("\\s+");
            String domain = data[2];
            String name = (data[0] + " " + data[1]);
            students.putIfAbsent(name, domain);
            input = reader.readLine();
        }
        students.entrySet().stream()
                .filter(x -> x.getValue()
                        .substring(x.getValue().indexOf('@'))
                        .equalsIgnoreCase("@gmail.com"))
                .forEach(x -> System.out.println(x.getKey()));
    }
}
