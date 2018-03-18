import com.sun.xml.internal.ws.util.ReadAllStream;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Arrays;
import java.util.Properties;

public class IncreaseMinionAge {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] minionIds = Arrays.stream(reader.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "password");
        String url = "jdbc:mysql://localhost:3306/minions";
        try(Connection connection =  DriverManager.getConnection(url, properties)){
            String query = "UPDATE minions AS m SET m.name = CONCAT(UPPER(LEFT(m.name, 1)), substr(m.name, 2)), m.age = m.age + 1 WHERE m.id = ?;";
            for (int i = 0; i < minionIds.length; i++) {
                PreparedStatement stmt = connection.prepareStatement(query);
                stmt.setInt(1, minionIds[i]);
                stmt.executeUpdate();
                stmt.close();
            }
            String selectQuery = "SELECT m.name AS `name`, m.age AS `age` FROM  minions AS m;";
            PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
            ResultSet rs = selectStatement.executeQuery();
            while (rs.next()){
                System.out.printf("%s %d%n", rs.getString("name"), rs.getInt("age"));
            }
            selectStatement.close();
            rs.close();
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
