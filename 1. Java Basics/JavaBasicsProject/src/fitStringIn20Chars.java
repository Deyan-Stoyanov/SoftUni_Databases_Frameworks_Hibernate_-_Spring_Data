import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class fitStringIn20Chars {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = reader.readLine();
        if(input.length() < 20){
            StringBuilder sb = new StringBuilder();
            sb.append(input);
            for (int i = input.length(); i < 20; i++) {
                sb.append("*");
            }
            input = sb.toString();
        } else {
            input = input.substring(0, 20);
        }
        System.out.println(input);
    }
}
