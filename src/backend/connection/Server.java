/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.connection;

import backend.Employee;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import common.rmiinterface;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author wjing
 */
public class Server extends UnicastRemoteObject implements rmiinterface{
    private Database connection;
    private Employee currentuser;
    
    public Server() throws RemoteException{
        super();
    }
    
    private Database getDatabaseConnection(){
       if(connection == null){
           try {
               connection = new Database("jdbc:derby://localhost:1527/Library", "user1", "user1");
           } catch (SQLException ex) {
               Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
           }
       }
       return connection;
    }
    
    public void checkConnection(){
        System.out.println("Server Connected");
    }
    
    public boolean Login(String username, String password) throws RemoteException{
        Database connection = getDatabaseConnection();
        
        try {
            this.currentuser = connection.getEmployeebyUsernamePassword(username, password);
            String correctPassword = currentuser.getPassword();
            
            String salt = currentuser.getSalt();
            String input = password + salt;
            
            StringBuilder hexString = getHashBySHA256(input);
            
            if(hexString != null && hexString.toString() == correctPassword){
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    private StringBuilder getHashBySHA256(String input){
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            byte[] hashBytes = digest.digest(input.getBytes());

            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            System.out.println("SHA-256 Hash: " + hexString.toString());
            return hexString;
        } catch (NoSuchAlgorithmException e) {
            System.out.println("SHA-256 algorithm not found.");
            return null;
        }
    }
}
