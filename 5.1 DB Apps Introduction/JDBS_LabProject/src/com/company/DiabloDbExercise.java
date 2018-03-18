package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

public class DiabloDbExercise {
    public static void main(String[] args) throws IOException, SQLException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String url = "jdbc:mysql://localhost:3306/diablo?createDatabaseOfNotExist=true";
        String user = "root";
        String password = "password";
        String query = "SELECT \n" +
                "    u.user_name, COUNT(uc.game_id) AS games_count, u.first_name, u.last_name\n" +
                "FROM\n" +
                "    users_games AS uc\n" +
                "        INNER JOIN\n" +
                "    users AS u ON u.id = uc.user_id\n" +
                "GROUP BY uc.user_id HAVING u.user_name = ?;";
        String username = reader.readLine();
        ResultSet resultSet = null;
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement
                     (query)) {
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            resultSet.beforeFirst();
            if (!resultSet.next()) {
                System.out.println("No such user exists");
            } else {

                System.out.printf("User: %s%n%s has played %d games%n", resultSet.getString("user_name"),
                        (resultSet.getString("first_name") + " " + resultSet.getString("last_name")), resultSet.getInt("games_count"));

            }
        } catch (SQLException s) {
            s.printStackTrace();
        }
    }
}
