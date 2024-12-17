/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.connection;
import backend.RMI_Interface;
import backend.Employee;
import backend.SalaryHistory;
import backend.SalaryDetail;
import backend.Deduction;
import backend.Payroll;
import backend.Tax;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.sql.Date;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
/**
 *
 * @author wjing
 */
public class Server extends UnicastRemoteObject implements RMI_Interface{
    
    private String current_user_id = "E100001";
    
    public Server() throws RemoteException{
        super();
    }
    
    public Employee getCurrentUser()throws RemoteException{
        Employee current_user = new Employee();
        try {
            Database db = new Database();
            current_user = db.getEmployee(current_user_id);
            db.closeConnection();
            return current_user;
        }catch(SQLException ex){
            System.out.println(ex);
        }
        return null;
    }
    
    public Employee getEmpDetails(String employee_id) throws RemoteException{
        Employee ep = new Employee();
        try {
            Database db = new Database();
            ep = db.getEmployee(employee_id);
            db.closeConnection();
            if (ep.getId() != null){
                System.out.println("Employee detail retrived succesfully: " + ep.getId());
                return ep;
            }
            else{
                System.out.println("Employee detail no found.");
                return null;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }
   
   public SalaryDetail getEmpSD(String employee_id)throws RemoteException{
       SalaryDetail sd = new SalaryDetail();
        try {
            Database db = new Database();
            sd = db.getEmpSD(employee_id);
            db.closeConnection();
            if (sd.getSd_id() != null){
                System.out.println("Salary Detail retrived succesfully: " + sd.getSd_id());
                return sd;
            }
            else{
                System.out.println("Salary Detail no found.");
                return null;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return sd;
   }
   
   public SalaryDetail getSDbyID(String sd_id)throws RemoteException{
        SalaryDetail sd = new SalaryDetail();
        try {
            Database db = new Database();
            sd = db.getSDByID(sd_id);
            db.closeConnection();
            if (sd.getSd_id() != null){
                System.out.println("Salary Detail retrived succesfully: " + sd.getSd_id());
                return sd;
            }
            else{
                System.out.println("Salary Detail no found.");
                return null;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
   }
    
   public Deduction getEmpDD(String dd_id)throws RemoteException{
        Deduction dd = new Deduction();
        try {
            Database db = new Database();
            dd = db.getEmpDeduction(dd_id);
            db.closeConnection();
            if (dd.getDd_id() != null){
                System.out.println("Salary Deduction retrived succesfully: " + dd.getDd_id());
                return dd;
            }
            else{
                System.out.println("Salary Deduction no found.");
                return null;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } 
        return null;
   }
   
   public SalaryHistory getEmpSH(String employee_id, Date date)throws RemoteException{
        SalaryHistory sh = new SalaryHistory();
        try {
            Database db = new Database();
            sh = db.getEmpSH(employee_id, date);
            db.closeConnection();
            if (sh.getSHId() != null){
                System.out.println("Salary History retrived succesfully: " + sh.getSHId());
                return sh;
            }
            else{
                System.out.println("Salary History no found.");
                return null;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
   }
   
   public List<SalaryHistory> searchHistoryByDate(Date date) throws RemoteException{
        List<SalaryHistory> sh_list = new ArrayList<>();
        try {
            Database db = new Database();
            sh_list = db.get_all_sh_byDate(date);
            db.closeConnection();
            if(sh_list.isEmpty()){
                System.out.println("No history in the date.");
                return null;
            }
            else{
                System.out.println("Salary History retrieve succesfully.");
                return sh_list;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
   }
   
   public boolean updateSalaryDetail(SalaryDetail sd)throws RemoteException{
        try {
            Database db = new Database();
            int result_flag = db.updateSalaryDetail(sd);
            db.closeConnection();
            
            switch(result_flag){
                case 1:
                    System.out.println("Salary detail update succesfully.");
                    return true;
                case 2:
                    System.out.println("Salary detail add succesfully.");
                    return true;
                case 3:
                    System.out.println("Salary detail update failed.");
                    return false;
                case 4:
                    System.out.println("Salary detail add failed.");
                    return false;
                default:
                    System.out.println("Unknowed Error, update failed.");
                    return false;
            }
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return false;
   }
   
   public boolean updateSalaryDeduction(Deduction dd) throws RemoteException{
        try {
            Database db = new Database();
            int result_flag = db.updateSalaryDeduction(dd);
            db.closeConnection();
            
            switch(result_flag){
                case 1:
                    System.out.println("Salary detail update succesfully.");
                    return true;
                case 2:
                    System.out.println("Salary detail add succesfully.");
                    return true;
                case 3:
                    System.out.println("Salary detail update failed.");
                    return false;
                case 4:
                    System.out.println("Salary detail add failed.");
                    return false;
                default:
                    System.out.println("Unknowed Error, update failed.");
                    return false;
            }
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return false;
   }
   
    public boolean updateSalaryHistory(SalaryHistory sh, Date date) throws RemoteException{
        try {
            Database db = new Database();
            int result_flag = db.updateSalaryHistory(sh,date);
            db.closeConnection();
            
            switch(result_flag){
                case 1:
                    System.out.println("Salary detail update succesfully.");
                    return true;
                case 2:
                    System.out.println("Salary detail add succesfully.");
                    return true;
                case 3:
                    System.out.println("Salary detail update failed.");
                    return false;
                case 4:
                    System.out.println("Salary detail add failed.");
                    return false;
                default:
                    System.out.println("Unknowed Error, update failed.");
                    return false;
            }
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return false;
   }
    
   public LocalDate getFirstMonthReportDate(){
       java.sql.Date firstdate = null;
       
       try {
            Database db = new Database();
            firstdate = db.getFirstDate();
            db.closeConnection();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
       
       if (firstdate != null){
           LocalDate date = firstdate.toLocalDate();
           return date;
       }
       
       return null;
   }
}
