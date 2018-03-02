import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class URLParser {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String str = reader.readLine();

        String protocol = "";
        String server = "";
        String resource = "";
        if(str.indexOf("://") > 0){
            protocol = str.substring(0, str.indexOf("://"));
            server = str.substring(str.indexOf("://") + 3);
            if(!(str.lastIndexOf("/") < str.lastIndexOf("."))) {
                resource = server.substring(server.indexOf("/") + 1);
                server = server.substring(0, server.indexOf("/"));
            }
        } else {
            server = str;
            if(str.indexOf("/") > 0) {
                resource = server.substring(server.indexOf("/") + 1);
                server = server.substring(0, server.indexOf("/"));
            }
        }
        System.out.println("[protocol] = \"" + protocol + "\"");
        System.out.println("[server] = \"" + server + "\"");
        System.out.println("[resource] = \"" + resource + "\"");
    }
}
