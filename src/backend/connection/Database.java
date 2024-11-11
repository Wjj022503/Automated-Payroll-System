/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;

/**
 *
 * @author wjing
 */
public class Database{
    
    private Connection connection;
    
    Database(String address,String username, String password)throws SQLException{
        this.connection = DriverManager.getConnection(address,username,password);
        if (connection != null){
            System.out.println("Connected to Database.");
        }
        else{
            System.out.println("Connect to database failed.");
        }
    }
}
