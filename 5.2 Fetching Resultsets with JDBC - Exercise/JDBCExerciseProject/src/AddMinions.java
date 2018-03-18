import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Properties;

public class AddMinions {
    public static void main(String[] args) throws SQLException, IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Properties properties = new Properties();
                properties.setProperty("user", "root");
                properties.setProperty("password", "password");
                String url = "jdbc:mysql://localhost:3306/minions";
                String[] minionData = reader.readLine().split("\\s+");
                String minionName = minionData[1];
                int minionAge = Integer.parseInt(minionData[2]);
                String city = minionData[3];
                String villainName = reader.readLine().split("\\s+")[1];
                try (Connection connection =  DriverManager.getConnection(url, properties)) {
                    if(IsTownAdded(connection, city) == -1){
                        PreparedStatement addTown = connection.prepareStatement("INSERT INTO minions.towns(name) VALUES (?);");
                        addTown.setString(1, city);
                        addTown.executeUpdate();
                        System.out.printf("Town %s was added to the database.%n", city);
                        addTown.close();
                    }
                    if(IsVillainAdded(connection, villainName) == -1){
                        PreparedStatement addVillain = connection.prepareStatement("INSERT INTO minions.villains(name, evilness_factor) VALUES (?, 'evil');");
                        addVillain.setString(1, villainName);
                        addVillain.executeUpdate();
                        System.out.printf("Villain %s was added to the database.%n", villainName);
                        addVillain.close();
                    }
                    PreparedStatement statement = connection.prepareStatement("INSERT INTO minions.minions(name, age, town_id) VALUES (?, ?, ?);");
                    statement.setString(1, minionName);
                    statement.setInt(2, minionAge);
                    statement.setInt(3, FindTown(connection, city));
                    statement.executeUpdate();
                    PreparedStatement addMinionsVillains = connection.prepareStatement("INSERT INTO minions_villains(minion_id, villain_id) VALUES (?, ?);");
                    addMinionsVillains.setInt(1, FindMinion(connection, minionName));
                    addMinionsVillains.setInt(2, FindVillain(connection, villainName));
                    addMinionsVillains.executeUpdate();
                    System.out.printf("Successfully added %s to be minion of %s%n", minionName, villainName);
                    statement.close();
                    addMinionsVillains.close();

                } catch (SQLException e){
                    e.printStackTrace();
                }
    }

     public static Integer IsTownAdded(Connection connection, String townName) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT t.name AS `name`, t.id AS id FROM minions.towns As t;");
         ResultSet resultSet = statement.executeQuery();
         while (resultSet.next()){
             if(resultSet.getString("name").equalsIgnoreCase(townName)){
                 return resultSet.getInt("id");
             }
         }
         return -1;
     }

    public static Integer IsVillainAdded(Connection connection, String villainName) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT v.name AS `name`, v.id AS id FROM minions.villains As v;");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            if(resultSet.getString("name").equalsIgnoreCase(villainName)){
                return resultSet.getInt("id");
            }
        }
        return -1;
    }

    public static Integer FindTown(Connection connection, String townName) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT t.name AS `name`, t.id AS id FROM minions.towns As t;");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            if(resultSet.getString("name").equalsIgnoreCase(townName)){
                return resultSet.getInt("id");
            }
        }
        return 0;
    }

    public static Integer FindVillain(Connection connection, String villainName) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT v.name AS `name`, v.id AS id FROM minions.villains As v;");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            if(resultSet.getString("name").equalsIgnoreCase(villainName)){
                return resultSet.getInt("id");
            }
        }
        return 0;
    }

    public static Integer FindMinion(Connection connection, String minionName) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT m.name AS `name`, m.id AS id FROM minions.minions As m;");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            if(resultSet.getString("name").equalsIgnoreCase(minionName)){
                return resultSet.getInt("id");
            }
        }
        return 0;
    }
}