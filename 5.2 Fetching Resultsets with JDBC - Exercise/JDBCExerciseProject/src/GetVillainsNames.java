import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Properties;

public class GetVillainsNames {

    public static void main(String[] args) throws SQLException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Properties properties = new Properties();
                properties.setProperty("user", "root");
                properties.setProperty("password", "password");
                String url = "jdbc:mysql://localhost:3306/minions";
                Connection connection =  DriverManager.getConnection(url, properties);
                PreparedStatement statement = connection.prepareStatement("SELECT\n" +
                "  v.name              AS `name`,\n" +
                "  count(mv.minion_id) AS `minion_count`\n" +
                "FROM villains AS v INNER JOIN minions_villains AS mv ON mv.villain_id = v.id\n" +
                "GROUP BY mv.villain_id ORDER BY minion_count DESC;");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            System.out.printf("%s %d%n", resultSet.getString("name"), resultSet.getInt("minion_count"));
        }
        statement.close();
    }
}
