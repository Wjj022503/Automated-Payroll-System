/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.connection;
import backend.Employee;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import backend.Payroll;
/**
 *
 * @author wjing
 */
public class Server extends UnicastRemoteObject implements RMI_Interface{
    
    public Server() throws RemoteException{
        super();
    }
    
    public Employee getEmployeeDetails(String current_user){
        Database db = new Database();
    }
}
