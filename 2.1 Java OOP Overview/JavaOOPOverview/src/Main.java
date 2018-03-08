import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Map<Integer, BankAccount> accounts = new HashMap<>();
        while (true) {
            String[] command = reader.readLine().split(" ");
            if(command[0].toUpperCase().equals("END")){
                break;
            }
            switch (command[0].toUpperCase()){
                case "CREATE":
                    int id = Integer.parseInt(command[1]);
                    BankAccount ba = new BankAccount(id, 0);
                    if(accounts.containsKey(ba.getId())){
                        System.out.println("This account already exists");
                    } else {
                        accounts.putIfAbsent(id, ba);
                    }
                    break;
                case "DEPOSIT":
                    int id1 = Integer.parseInt(command[1]);
                    double ammount1 = Double.parseDouble(command[2]);
                    if(!accounts.containsKey(id1)){
                        System.out.println("This account doesn't exist");
                    } else {
                        accounts.get(id1).deposit(ammount1);
                    }
                    break;
                case "WITHDRAW":
                    int id2 = Integer.parseInt(command[1]);
                    double ammount2 = Double.parseDouble(command[2]);
                    if(!accounts.containsKey(id2)){
                        System.out.println("This account doesn't exist");
                    } else {
                        accounts.get(id2).withdraw(ammount2);
                    }
                    break;
                case "PRINT":
                    int id3 = Integer.parseInt(command[1]);
                    if(!accounts.containsKey(id3)){
                        System.out.println("This account doesn't exist");
                    } else {
                        System.out.println(accounts.get(id3).toString());
                    }
                    break;
            }
        }
    }
}