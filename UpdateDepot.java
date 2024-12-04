package org.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateDepot {

    // Method to update depot name from d1 to dd1 in both Depot and Stock tables
    public static void updateDepotName(Connection conn) {
        // Update query for the Depot table
        String updateDepot = "UPDATE Depot SET dep = 'dd1' WHERE dep = 'd1'";
        // Update query for the Stock table to reflect the depot name change
        String updateStock = "UPDATE Stock SET dep = 'dd1' WHERE dep = 'd1'";

        try (Statement stmt = conn.createStatement()) {
            conn.setAutoCommit(false);  // Start the transaction

            // Update the depot name in the Depot table
            stmt.executeUpdate(updateDepot);

            // Manually update the related stock entries in the Stock table
            stmt.executeUpdate(updateStock);

            // Commit the transaction if both operations are successful
            conn.commit();
            System.out.println("Depot name updated from d1 to dd1 in both Depot and Stock.");
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
            updateDepotName(conn);  // Execute the depot renaming transaction
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
