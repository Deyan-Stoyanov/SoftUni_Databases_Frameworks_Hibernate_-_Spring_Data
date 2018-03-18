package telefony;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] phoneNumbers = reader.readLine().split("\\s+");
        String[] webSites = reader.readLine().split("\\s+");
        String phonePattern = "^([0-9]+)$";
        String websitePattern = "^([^0-9]+)$";
        Smartphone myPhone = new Smartphone();
        for (String phone:phoneNumbers) {
            if(phone.matches(phonePattern)){
                myPhone.call(phone);
            } else {
                System.out.println("Invalid number!");
            }
        }
        for (String website:webSites) {
            if(website.matches(websitePattern)){
                myPhone.browse(website);
            } else {
                System.out.println("Invalid URL!");
            }
        }
    }
}
