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
        
        return true;
    }
}
