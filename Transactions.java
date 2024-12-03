package org.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Transactions {

    // Method to update depot name from d1 to dd1 in Depot and Stock tables
    public static void updateDepotName(Connection conn) {
        String updateDepot = "UPDATE Depot SET dep = 'dd1' WHERE dep = 'd1'";  // Update the depot name

        try (Statement stmt = conn.createStatement()) {
            conn.setAutoCommit(false);  // Start the transaction

            // Execute the update query for Depot
            stmt.executeUpdate(updateDepot);

            conn.commit();  // Commit transaction if everything is successful
            System.out.println("Depot name updated from d1 to dd1 in Depot and Stock.");
        } catch (SQLException e) {
            try {
                conn.rollback();  // Rollback the transaction if an error occurs
            } catch (SQLException rollbackException) {
                rollbackException.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    // Method to add a new depot (d100) and new stock entry for p1 at d100
    public static void addNewDepotAndStock(Connection conn) {
        String insertNewDepot = "INSERT INTO Depot (dep, addr, volume) VALUES ('d100', 'Chicago', 100)";
        String insertNewStock = "INSERT INTO Stock (prod, dep, quantity) VALUES ('p1', 'd100', 100)";

        try (Statement stmt = conn.createStatement()) {
            conn.setAutoCommit(false);  // Start the transaction

            // Insert the new depot and new stock entry
            stmt.executeUpdate(insertNewDepot);
            stmt.executeUpdate(insertNewStock);

            conn.commit();  // Commit transaction if everything is successful
            System.out.println("New depot (d100) and stock for p1 at d100 added successfully.");
        } catch (SQLException e) {
            try {
                conn.rollback();  // Rollback the transaction if an error occurs
            } catch (SQLException rollbackException) {
                rollbackException.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try (Connection conn = DatabaseConnection.connect()) {
            // Uncomment one of these operations to execute separately
            updateDepotName(conn);  // Execute the depot renaming transaction
            // addNewDepotAndStock(conn);  // Execute the new depot and stock insertion transaction
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
