package vehicles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] tokens = reader.readLine().split("\\s+");
        Car car = new Car(Double.parseDouble(tokens[1]), Double.parseDouble(tokens[2]));
        tokens = reader.readLine().split("\\s+");
        Truck truck = new Truck(Double.parseDouble(tokens[1]), Double.parseDouble(tokens[2]));
        int n = Integer.parseInt(reader.readLine());
        for (int i = 0; i < n; i++) {
            String[] command = reader.readLine().split("\\s+");
            String toDo = command[0];
            String type = command[1];
            switch (toDo.toUpperCase()){
                case "DRIVE":
                    double distance = Double.parseDouble(command[2]);
                    switch (type.toUpperCase()){
                        case "CAR":
                            car.drive(distance);
                            break;
                        case "TRUCK":
                            truck.drive(distance);
                            break;
                    }
                    break;
                case "REFUEL":
                    double fuel = Double.parseDouble(command[2]);
                    switch (type.toUpperCase()){
                        case "CAR":
                            car.refuel(fuel);
                            break;
                        case "TRUCK":
                            truck.refuel(fuel);
                            break;
                    }
                    break;
            }
        }
        System.out.println(car.toString());
        System.out.println(truck.toString());
    }
}
