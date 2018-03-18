package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

public class SoftUniDbExercise {
    public static void main(String[] args) throws SQLException, IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String url = "jdbc:mysql://localhost:3306/soft_uni?createDatabaseOfNotExist=true";
        String user = "root";
        String password = reader.readLine();

        ResultSet resultSet = null;
        try(Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM employees WHERE salary > ? ORDER BY employee_id")) {
            if(Login(connection, 3)){
                System.out.println("Login successful!");
            }
            if (connection != null) {
                System.out.println("Connection is opened!");
            }
            String salary = reader.readLine();
            preparedStatement.setDouble(1, Double.parseDouble(salary));
            resultSet = preparedStatement.executeQuery();
            resultSet.beforeFirst();
            while (resultSet.next()) {
                System.out.printf("%d. %s %s %.2f%n", resultSet.getRow(), resultSet.getString("first_name"),
                        resultSet.getString("last_name"), resultSet.getDouble("salary"));
            }
            if(connection.isClosed()){
                System.out.println("Connection closed!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static boolean Login(Connection connection, Integer employeeid) throws SQLException {
        String SQLInjection = "SELECT * FROM employees WHERE employee_id =" + employeeid;
        boolean hasRows = false;
        try (Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(SQLInjection);
            resultSet.next();
            while (resultSet.next()){
                System.out.printf("User: %s",
                        resultSet.getString("first_name") + resultSet.getString("last_name"));
            }
            resultSet.last();
            int count = resultSet.getRow();
            if(count != 0){
                hasRows = true;
            }
        }
        return  hasRows;
    }
}
