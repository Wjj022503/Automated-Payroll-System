/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.connection;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import common.rmiinterface;
/**
 *
 * @author wjing
 */
public class Server extends UnicastRemoteObject implements rmiinterface{
    public Server() throws RemoteException{
        super();
    }
    
    public void checkConnection(){
        System.out.println("Server Connected");
    }
    
    public boolean Login(String username, String password) throws SQLException{
    // SQL query to select the employee with the provided username and password
    String userNameSelector = "SELECT * FROM Employee WHERE username=? AND password=?";
    
    // Establish a connection to the database
    Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/Library", "user1", "user1");
    System.out.println("Connected");
    
    // Use PreparedStatement to prevent SQL injection
    PreparedStatement pstmt = conn.prepareStatement(userNameSelector);
    pstmt.setString(1, username);
    pstmt.setString(2, password);
    
    // Execute the query
    ResultSet loginEmployee = pstmt.executeQuery();
    
    // Check if a matching row was found
    boolean isLoggedIn = loginEmployee.next(); // This moves the cursor to the first row if it exists
    
    // Close the resources
    loginEmployee.close();
    pstmt.close();
    conn.close();
    
    // Return true if a row was found, otherwise false
    return isLoggedIn;
    }
}
