/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.connection;
import backend.Employee;
import backend.SalaryHistory;
import backend.SalaryDetail;
import backend.Deduction;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.sql.Date;
/**
 *
 * @author wjing
 */
public class Server extends UnicastRemoteObject implements RMI_Interface{
    
    public Server() throws RemoteException{
        super();
    }
    
    public Employee getEmpDetails(String employee_id){
        Employee ep = new Employee();
        try {
            Database db = new Database();
            ep.equals(db.findEmployeeById(employee_id));
            db.closeConnection();
        } catch (SQLException ex) {
            System.out.println(ex);
        } 
        return ep;
    }
   
   public SalaryDetail getEmpSD(String employee_id){
       SalaryDetail sd = new SalaryDetail();
        try {
            Database db = new Database();
            sd.equals(db.getSD_by_empID(employee_id));
            db.closeConnection();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return sd;
   }
    
   public Deduction getEmpDD(String employee_id,Date date){
        Deduction dd = new Deduction();
        try {
            Database db = new Database();
            dd.equals(db.getEmpDeduction(employee_id, date));
            db.closeConnection();
        } catch (SQLException ex) {
            System.out.println(ex);
        } 
        return dd;
   }
   
   public SalaryHistory getEmpSH(String employee_id, Date date){
        SalaryHistory sh = new SalaryHistory();
        try {
            Database db = new Database();
            sh.equals(db.getEmpSalaryHistory(employee_id, date));
            db.closeConnection();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return sh;
   }
}
