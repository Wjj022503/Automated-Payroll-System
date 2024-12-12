/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author HappyMan20
 */
public class MonthlyReport {
    
    public MonthlyReport() throws RemoteException {
        super();
        createDatabaseAndTable();
    }
    
    private Connection connect() {
        String url = "jdbc:derby://localhost:1527/Library";
        String user = "tan"; 
        String password = "tan"; 

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
    
        private void createDatabaseAndTable() {
        String createTableSQL = "CREATE TABLE Employee ("
                + "ICNo VARCHAR(15) PRIMARY KEY, "
                + "EmpAge INT)";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(createTableSQL)) {
            stmt.executeUpdate();
            System.out.println("Table Employee created successfully.");
        } catch (SQLException e) {
            System.out.println("Table already exists or failed to create: " + e.getMessage());
        }
    }
}