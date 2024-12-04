package org.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertData {

    // Method to insert initial data into the tables
    public static void insertInitialData(final Connection conn) {
        final String insertProduct = "INSERT INTO Product (prod, pname, price) VALUES " +
                               "('p1', 'tape', 2.5), " +
                               "('p2', 'tv', 250), " +
                               "('p3', 'vcr', 80)";

        final String insertDepot = "INSERT INTO Depot (dep, addr, volume) VALUES " +
                             "('d1', 'New York', 9000), " +
                             "('d2', 'Syracuse', 6000), " +
                             "('d4', 'New York', 2000)";

        final String insertStock = "INSERT INTO Stock (prod, dep, quantity) VALUES " +
                             "('p1', 'd1', 1000), " +
                             "('p1', 'd2', -100), " +
                             "('p1', 'd4', 1200), " +
                             "('p3', 'd1', 3000), " +
                             "('p3', 'd4', 2000), " +
                             "('p2', 'd4', 1500), " +
                             "('p2', 'd1', -400), " +
                             "('p2', 'd2', 2000)";

        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(insertProduct);
            stmt.executeUpdate(insertDepot);
            stmt.executeUpdate(insertStock);
            System.out.println("Initial data inserted successfully.");
        } catch (final SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(final String[] args) {
        try (Connection conn = DatabaseConnection.connect()) {
            insertInitialData(conn);
        } catch (final SQLException e) {
            e.printStackTrace();
        }
    }
}
