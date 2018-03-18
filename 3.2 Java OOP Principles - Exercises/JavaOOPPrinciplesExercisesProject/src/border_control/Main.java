package border_control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<Thing> myList = new ArrayList<>();
        String command = reader.readLine();
        while(!command.equalsIgnoreCase("END")){
            String[] data = command.split("\\s+");
            if(data.length == 2){
                Robot robot = new Robot(data[0], data[1]);
                myList.add(robot);
            } else if(data.length == 3){
                Citizen citizen = new Citizen(data[0], Integer.parseInt(data[1]), data[2]);
                myList.add(citizen);
            }
            command = reader.readLine();
        }
        String endOfPassport = reader.readLine();
        for (Thing thisThing:myList) {
            if(thisThing.getId().endsWith(endOfPassport)){
                System.out.println(thisThing.getId());
            }
        }
    }
}
