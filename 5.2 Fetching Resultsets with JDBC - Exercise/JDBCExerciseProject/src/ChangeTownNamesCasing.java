import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Properties;
import java.util.Set;

public class ChangeTownNamesCasing {
    public static void main(String[] args) throws SQLException, IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String country = reader.readLine();
        Properties properties = new Properties();
                properties.setProperty("user", "root");
                properties.setProperty("password", "password");
                String url = "jdbc:mysql://localhost:3306/minions";
               try(Connection connection =  DriverManager.getConnection(url, properties)){
                   Set<String> towns = new LinkedHashSet<>();
                   String selectTowns = "SELECT t.name AS `name` FROM minions.towns AS t\n" +
                           "WHERE t.country = ?;";
                   PreparedStatement ps = connection.prepareStatement(selectTowns);
                   ps.setString(1, country);
                   ResultSet rs = ps.executeQuery();
                   while(rs.next()){
                       if(!(rs.getString("name").equals(rs.getString("name").toUpperCase()))){
                            towns.add(rs.getString("name"));
                       }
                   }
                    String query = "UPDATE minions.towns AS t\n" +
                            "SET t.name = UPPER(t.name)\n" +
                            "WHERE t.country = ?;";
                   PreparedStatement statement = connection.prepareStatement(query);
                   statement.setString(1, country);
                   statement.executeUpdate();
                   if(towns.size() == 0){
                       System.out.println("No town names were affected.");
                   } else {
                       System.out.printf("%d town names were affected.%n", towns.size());
                       StringBuilder sb = new StringBuilder();
                       sb.append("[");
                       for (String s:towns) {
                           sb.append(s.toUpperCase()).append(", ");
                       }
                       sb.replace(sb.length() - 2, sb.length(), "");
                       sb.append("]");
                       System.out.println(sb.toString());
                   }
                   ps.close();
                   statement.close();
                   connection.close();
               } catch (SQLException e){
                   e.printStackTrace();
               }
    }
}
