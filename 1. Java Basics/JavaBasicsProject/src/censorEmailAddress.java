import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class censorEmailAddress {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String email = reader.readLine();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < email.substring(0, email.indexOf('@')).length(); i++) {
            sb.append('*');
        }
        String replacement = sb.toString();
        String emailReplacement = replacement + email.substring(email.indexOf("@"));
        String text = reader.readLine();
        text = text.replace(email, emailReplacement);
        System.out.println(text);
    }
}