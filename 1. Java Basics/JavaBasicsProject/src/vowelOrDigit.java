import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class vowelOrDigit {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<Character> vowels = new ArrayList<Character>(Arrays.asList('a', 'e', 'o', 'i', 'u'));
        char myChar = reader.readLine().charAt(0);
        if(vowels.contains(myChar)){
            System.out.println("vowel");
        } else if(Character.toString(myChar).matches("[0-9]")){
            System.out.println("digit");
        } else {
            System.out.println("other");
        }
    }
}
