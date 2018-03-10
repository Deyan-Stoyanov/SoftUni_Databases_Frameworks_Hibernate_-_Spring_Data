import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class WeakStudents {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = reader.readLine();
        Map<String, List<Integer>> students = new LinkedHashMap<>();
        while(!input.isEmpty() && !input.equalsIgnoreCase("END")){
            String[] data = input.split("\\s+");
            String name = (data[0] + " " + data[1]);
            students.putIfAbsent(name, new ArrayList<>());
            for (int i = 2; i < data.length; i++) {
                students.get(name).add(Integer.parseInt(data[i]));
            }
            input = reader.readLine();
        }
        students.entrySet().stream()
                .filter(x -> {
                    int counter = 0;
                    for (int mark:x.getValue()) {
                        if(mark <= 3){
                            counter++;
                        }
                    }
                    if(counter >= 2){
                        return true;
                    } return false;
                }).forEach(x -> System.out.println(x.getKey()));
    }
}
