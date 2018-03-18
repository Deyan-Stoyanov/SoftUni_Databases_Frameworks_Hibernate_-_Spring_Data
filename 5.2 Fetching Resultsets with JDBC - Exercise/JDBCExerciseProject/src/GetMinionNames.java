import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Properties;

public class GetMinionNames {
    public static void main(String[] args) throws SQLException, IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Properties properties = new Properties();
                properties.setProperty("user", "root");
                properties.setProperty("password", "password");
                String url = "jdbc:mysql://localhost:3306/minions";
                Connection connection =  DriverManager.getConnection(url, properties);
                int n = Integer.parseInt(reader.readLine());
        PreparedStatement statement = connection.prepareStatement("SELECT\n" +
                "  v.name              AS `villain_name`,\n" +
                "  m.name AS `minion_name`,\n" +
                "  m.age AS `age`\n" +
                "FROM villains AS v\n" +
                "  INNER JOIN minions_villains AS mv ON mv.villain_id = v.id\n" +
                "  INNER JOIN minions AS m ON mv.minion_id = m.id\n" +
                "  WHERE mv.villain_id=?;");
        statement.setInt(1, n);
        ResultSet resultSet = statement.executeQuery();
        int counter = 0;
        while (resultSet.next()){
            if(counter == 0){
                System.out.printf("Villain: %s%n", resultSet.getString("villain_name"));
            }
            String minionName = resultSet.getString("minion_name");
            Integer age = resultSet.getInt("age");
            if(!(minionName.equalsIgnoreCase(""))){
                counter++;
                System.out.printf("%d. %s %d%n", counter, resultSet.getString("minion_name"), resultSet.getInt("age"));
                ResultSetMetaData rsm = resultSet.getMetaData();
                int count = rsm.getColumnCount();

            }
        }
        if(counter == 0){
            System.out.printf("No villain with ID %d exists in the database.%n", n);
        }
        resultSet.close();
    }
}
