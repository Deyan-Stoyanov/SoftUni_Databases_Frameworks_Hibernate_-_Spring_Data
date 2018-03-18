import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Properties;

public class IncreaseAgeStoredProcedure {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int minionId = Integer.parseInt(reader.readLine());
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "password");
        String url = "jdbc:mysql://localhost:3306/minions";
        try(Connection connection =  DriverManager.getConnection(url, properties)){
            String procedure = "{call usp_get_older(?)}";
            CallableStatement statement = connection.prepareCall(procedure);
            statement.setInt(1, minionId);
            statement.execute();
            String query = "SELECT m.name AS `name`, m.age AS `age` FROM minions AS m WHERE m.id = ?;";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, minionId);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            System.out.printf("%s %d%n", rs.getString("name"), rs.getInt("age"));
            rs.close();
            statement.close();
            stmt.close();
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
