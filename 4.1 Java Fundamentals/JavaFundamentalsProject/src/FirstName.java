import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class FirstName {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        HashSet<String> names =new HashSet<>( Arrays.asList(reader.readLine().split("\\s+")));
        List<String> c = Arrays.asList(reader.readLine().toLowerCase().split("\\s+"));
        Optional<String> first = names.stream().filter(name -> c.contains(name.toLowerCase().substring(0, 1))).sorted().findFirst();
        if(first.isPresent()){
            System.out.println(first.get());
        } else {
            System.out.println("No match");
        }
    }
}