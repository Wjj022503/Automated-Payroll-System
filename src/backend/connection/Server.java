/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.connection;
import backend.Employee;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import backend.Payroll;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author wjing
 */
public class Server extends UnicastRemoteObject implements RMI_Interface{
    
    public Server() throws RemoteException{
        super();
    }
    
    public Employee getEmployeeDetails(String current_user_id){
        Employee ep = new Employee();
        try {
            Database db = new Database();
            ep.equals(db.findEmployeeById(current_user_id));
            db.closeConnection();
        } catch (SQLException ex) {
            System.out.println(ex);
        } 
        return ep;
    }
}
