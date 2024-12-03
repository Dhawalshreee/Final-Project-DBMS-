package org.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTables {

    // Method to create tables
    public static void createTables(Connection conn) {
        String createProductTable = "CREATE TABLE IF NOT EXISTS Product (" +
                                    "prod VARCHAR(50) PRIMARY KEY, " +
                                    "pname VARCHAR(50), " +
                                    "price NUMERIC)";
        
        String createDepotTable = "CREATE TABLE IF NOT EXISTS Depot (" +
                                  "dep VARCHAR(50) PRIMARY KEY, " +
                                  "addr VARCHAR(50), " +
                                  "volume NUMERIC)";

        String createStockTable = "CREATE TABLE IF NOT EXISTS Stock (" +
                                  "prod VARCHAR(50), " +
                                  "dep VARCHAR(50), " +
                                  "quantity NUMERIC, " +
                                  "PRIMARY KEY (prod, dep), " +
                                  "FOREIGN KEY (prod) REFERENCES Product(prod), " +
                                  "FOREIGN KEY (dep) REFERENCES Depot(dep))";

        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(createProductTable);
            stmt.executeUpdate(createDepotTable);
            stmt.executeUpdate(createStockTable);
            System.out.println("Tables created successfully.");
        } catch (SQLException e) {
        }
    }

    public static void main(String[] args) {
        try (Connection conn = DatabaseConnection.connect()) {
            createTables(conn);
        } catch (SQLException e) {
        }
    }
}
