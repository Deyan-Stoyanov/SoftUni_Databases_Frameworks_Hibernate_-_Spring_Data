package birtday_celebration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<Living> myList = new ArrayList<>();
        String command = reader.readLine();
        while(!command.equalsIgnoreCase("END")){
            String[] data = command.split("\\s+");
            switch (data[0]){
                case "Citizen":
                    Citizen citizen = new Citizen(data[1], Integer.parseInt(data[2]), data[3], data[4]);
                    myList.add(citizen);
                    break;
                case "Pet":
                    Pet pet = new Pet(data[1], data[2]);
                    myList.add(pet);
                    break;
                case "Robot":
                    break;
            }
            command = reader.readLine();
        }
        String year = reader.readLine();
        myList
                .stream()
                .filter(x -> x.getBirthDate().endsWith(year))
                .forEach(x -> System.out.println(x.getBirthDate()));
    }
}
