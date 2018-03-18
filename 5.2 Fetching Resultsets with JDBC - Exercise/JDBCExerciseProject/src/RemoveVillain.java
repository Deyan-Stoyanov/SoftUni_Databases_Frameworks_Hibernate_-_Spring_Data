import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Properties;

public class RemoveVillain {
    public static void main(String[] args) throws SQLException, IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Properties properties = new Properties();
                properties.setProperty("user", "root");
                properties.setProperty("password", "password");
                String url = "jdbc:mysql://localhost:3306/minions";
                String villainName = reader.readLine();
                try(Connection connection =  DriverManager.getConnection(url, properties)){
                    if(isVillainThere(connection, villainName)){
                        int minionsCount = countOfFreedMinions(connection, villainName);
                        String deleteFromMinionsVillains = "DELETE FROM minions_villains\n" +
                                "WHERE villain_id = (\n" +
                                "  SELECT v.id\n" +
                                "  FROM villains AS v\n" +
                                "  WHERE v.name = ?);";
                        PreparedStatement firstStatement = connection.prepareStatement(deleteFromMinionsVillains);
                        firstStatement.setString(1, villainName);
                        firstStatement.executeUpdate();
                        firstStatement.close();
                        String deleteFromVillains = "DELETE FROM villains WHERE name = ?;";
                        PreparedStatement secondStmt = connection.prepareStatement(deleteFromMinionsVillains);
                        secondStmt.setString(1, villainName);
                        secondStmt.executeUpdate();
                        secondStmt.close();
                        System.out.printf("%s was deleted%n", villainName);
                        System.out.printf("%d minions released%n", minionsCount);
                    } else {
                        System.out.println("No such villain was found");
                    }
                    connection.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
    }

    public static boolean isVillainThere(Connection connection, String villainName) throws SQLException {
        String query = "SELECT v.name AS `name` FROM villains AS v WHERE v.name=?;";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, villainName);
        ResultSet rs = stmt.executeQuery();
        return rs.next();
    }

    public static Integer countOfFreedMinions(Connection connection, String villainName) throws SQLException {
        String query = "SELECT count(*) AS `count`\n" +
                "FROM villains AS v INNER JOIN minions_villains AS mv ON v.id = mv.villain_id\n" +
                "WHERE v.name = ?\n" +
                "GROUP BY mv.villain_id;";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, villainName);
        ResultSet rs = stmt.executeQuery();
        rs.next();
        return rs.getInt("count");
    }
}
