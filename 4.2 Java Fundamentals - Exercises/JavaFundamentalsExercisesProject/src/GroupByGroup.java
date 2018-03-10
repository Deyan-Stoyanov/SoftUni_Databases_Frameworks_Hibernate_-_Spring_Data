import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GroupByGroup {

   private static class Person{
       public String name;
       public int group;

        Person(String name, int group) {
            this.name = name;
            this.group = group;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getGroup() {
            return this.group;
        }

        public void setGroup(int group) {
            this.group = group;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = reader.readLine();
        List<Person> students = new ArrayList<>();
        Map<Integer, List<String>> result = new LinkedHashMap<>();
        while (!input.isEmpty() && !input.equalsIgnoreCase("End")){
            String[] data = input.split("\\s+");
            String name = data[0] + " " + data[1];
            Integer group = Integer.parseInt(data[2]);
            Person student = new Person(name, group);
            students.add(student);
            input = reader.readLine();
        }
        students.stream()
                .collect(Collectors.groupingBy(Person::getGroup,
                        Collectors.mapping(Person::getName, Collectors.toList())))
                .forEach((x, y) -> {
                    result.putIfAbsent(x, new ArrayList<>());
                    result.get(x).addAll(y);
        });
        result.forEach((key, value) -> {
            System.out.printf("%d - ", key);
            StringBuilder sb = new StringBuilder();
            value.forEach(y -> sb.append(y).append(", "));
            System.out.println(sb.toString().substring(0, sb.toString().length() - 2));
        });
    }
}
