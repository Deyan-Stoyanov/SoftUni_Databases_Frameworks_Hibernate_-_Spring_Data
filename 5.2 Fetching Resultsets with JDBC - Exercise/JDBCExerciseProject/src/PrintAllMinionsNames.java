import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PrintAllMinionsNames {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "password");
        String url = "jdbc:mysql://localhost:3306/minions";
        List<String> listOfNames = new ArrayList<>();
        try(Connection connection =  DriverManager.getConnection(url, properties)){
            String query = "SELECT m.name FROM minions AS m;";
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                listOfNames.add(rs.getString("name"));
            }
            SortAndPrintList(listOfNames);
            rs.close();
            stmt.close();
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void SortAndPrintList(List<String> listOfNames) {
        int j = listOfNames.size() - 1;
        for (int i = 0; i <= listOfNames.size() / 2; i++) {
            System.out.println(listOfNames.get(i));
            for (; j > listOfNames.size() / 2;) {
                System.out.println(listOfNames.get(j));
                j--;
                break;
            }
        }
    }
}
