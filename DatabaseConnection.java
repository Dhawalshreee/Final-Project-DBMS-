package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:postgresql://localhost:5432/finalproject";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Admin";

    // Method to establish connection to the database
    public static Connection connect() throws SQLException {
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        
        // Print a success message when the connection is successful
        if (conn != null) {
            System.out.println("Connection Built Successfully!");
        }
        
        return conn;
    }
}
