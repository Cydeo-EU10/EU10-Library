package com.library.step_definitions;

import org.junit.Test;

import java.sql.*;

public class TestConnection {

    @Test
    public void Test() throws SQLException {

        String query = "select name, isbn,year,author,description from books\n" +
                "where name = 'Chordeiles minor'";
        Connection connection =
                DriverManager.getConnection("jdbc:mysql://34.230.35.214:3306/library1","library1_client","WVF4NdGXCKHeE6VQ");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()){
            System.out.println(resultSet.getString(1));
        }

        resultSet.close();
        statement.close();
        connection.close();
    }
}
