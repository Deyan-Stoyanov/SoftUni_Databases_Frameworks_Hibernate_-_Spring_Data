import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class changeToUppercase {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input  = reader.readLine();
        while (input.contains("<upcase>") && input.contains("</upcase>"))
        {
           input = input.replace(input.substring(input.indexOf("<upcase>"), input.indexOf("</upcase>") + 9), input.substring(input.indexOf("<upcase>") + 8, input.indexOf("</upcase>")).toUpperCase());
        }
        System.out.println(input);
    }
}
