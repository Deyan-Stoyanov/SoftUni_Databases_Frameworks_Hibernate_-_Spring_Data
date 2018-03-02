import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class phonebook {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Map<String, String> phonebook = new HashMap<>();
        while(true){
            String[] command = reader.readLine().split(" ");
            if(command[0].toUpperCase().equals("END")){
                break;
            }
            if(command[0].toUpperCase().equals("A")){
                String name = command[1];
                String phoneNumber = command[2];
                if(phonebook.containsKey(name)){
                    phonebook.remove(name, phonebook.get(name));
                }
                phonebook.putIfAbsent(name, phoneNumber);
            } else if (command[0].toUpperCase().equals("S")) {
                String name = command[1];
                if(phonebook.containsKey(name)){
                    System.out.println(name + " -> " + phonebook.get(name));
                } else {
                    System.out.printf("Contact %s does not exist.%n", name);
                }
            }
        }
    }
}
