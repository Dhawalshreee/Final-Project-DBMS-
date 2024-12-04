package org.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AddDepotAndStock {

    // Method to add a new depot (d100) and a new stock entry for p1 at d100, ensuring product p1 exists
    public static void addNewDepotAndStock(Connection conn) {
        String checkProductExistence = "SELECT COUNT(*) FROM Product WHERE prod = 'p1'";
        String insertDepot = "INSERT INTO Depot (dep, addr, volume) VALUES ('d100', 'Chicago', 100)";
        String insertProduct = "INSERT INTO Product (prod, pname, price) VALUES ('p1', 'cd', 5) ON CONFLICT (prod) DO NOTHING";
        String insertStock = "INSERT INTO Stock (prod, dep, quantity) VALUES ('p1', 'd100', 100)";

        try (Statement stmt = conn.createStatement()) {
            conn.setAutoCommit(false);  // Start transaction

            // Check if product p1 exists
            ResultSet rs = stmt.executeQuery(checkProductExistence);
            rs.next();
            if (rs.getInt(1) == 0) {
                // If product doesn't exist, insert it
                stmt.executeUpdate(insertProduct);
            }

            // Insert the new depot
            stmt.executeUpdate(insertDepot);

            // Insert stock entry for product p1 at depot d100
            stmt.executeUpdate(insertStock);

            conn.commit();  // Commit transaction if both operations are successful
            System.out.println("Depot and stock for p1 at d100 added successfully.");
        } catch (SQLException e) {
            try {
                conn.rollback();  // Rollback if an error occurs
            } catch (SQLException rollbackException) {
                rollbackException.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try (Connection conn = DatabaseConnection.connect()) {
            addNewDepotAndStock(conn);  // Execute Transaction 6: Add a new depot and stock entry
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
