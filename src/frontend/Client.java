/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package frontend;
import java.rmi.*;
import java.net.MalformedURLException;
import java.sql.Date;
import backend.Deduction;
import backend.Employee;
import backend.SalaryDetail;
import backend.SalaryHistory;
import backend.RMI_Interface;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
/**
 *
 * @author wjing
 */
public class Client {
    private static RMI_Interface obj;
    private static String address = "rmi://localhost:1040/main";
    
    public static Employee getCurrentUser(){
        Employee current_user = new Employee();
        try{
            obj = (RMI_Interface)Naming.lookup(address);
            current_user = obj.getCurrentUser();
        }
        catch(MalformedURLException e){
            e.printStackTrace();
        }
        catch(NotBoundException e){
            e.printStackTrace();
        }
        catch (RemoteException e){
            e.printStackTrace();
        }
        return current_user;
    }
    
    public static Employee getEmployee(String emp_id){
        Employee emp = new Employee();
        try{
            obj = (RMI_Interface)Naming.lookup(address);
            emp = obj.getEmpDetails(emp_id);
        }
        catch(MalformedURLException e){
            e.printStackTrace();
        }
        catch(NotBoundException e){
            e.printStackTrace();
        }
        catch (RemoteException e){
            e.printStackTrace();
        }
        return emp; 
    }
    
    public static SalaryDetail getEmployeeSalaryDetail(String employee_id){
        SalaryDetail sd = new SalaryDetail();
        try{
            obj = (RMI_Interface)Naming.lookup(address);
            sd = obj.getEmpSD(employee_id);
        }
        catch(MalformedURLException e){
            e.printStackTrace();
        }
        catch(NotBoundException e){
            e.printStackTrace();
        }
        catch (RemoteException e){
            e.printStackTrace();
        }
        return sd;
    }
    
    public static SalaryDetail getSalaryDetailByID(String sd_id){
        SalaryDetail sd = new SalaryDetail();
        try{
            obj = (RMI_Interface)Naming.lookup(address);
            sd = obj.getSDbyID(sd_id);
        }
        catch(MalformedURLException e){
            e.printStackTrace();
        }
        catch(NotBoundException e){
            e.printStackTrace();
        }
        catch (RemoteException e){
            e.printStackTrace();
        }
        return sd;
    }
    
    public static SalaryHistory getSalaryHistory(Date date){
        SalaryHistory sh = new SalaryHistory();
        try{
            obj = (RMI_Interface)Naming.lookup(address);
            sh = obj.getEmpSH(getCurrentUser().getId(), date);
        }
        catch(MalformedURLException e){
            e.printStackTrace();
        }
        catch(NotBoundException e){
            e.printStackTrace();
        }
        catch (RemoteException e){
            e.printStackTrace();
        }
        return sh;
    }
    
    public static Deduction getDeduction(String dd_id){
        Deduction dd = new Deduction();
        try{
            obj = (RMI_Interface)Naming.lookup(address);
            dd = obj.getEmpDD(dd_id);
        }
        catch(MalformedURLException e){
            e.printStackTrace();
        }
        catch(NotBoundException e){
            e.printStackTrace();
        }
        catch (RemoteException e){
            e.printStackTrace();
        }
        return dd;
    }
    
    public static void updateMonthlySalary(Date date, String emp_id,double allowance, double ot_hrs, double ld, double ad, String reason){
        try{
            obj = (RMI_Interface)Naming.lookup(address);

        }
        catch(MalformedURLException e){
            e.printStackTrace();
        }
        catch(NotBoundException e){
            e.printStackTrace();
        }
        catch (RemoteException e){
            e.printStackTrace();
        }
    }
    
    public static void updateBaseSalary(String emp_id,Double bs,int wh,Double hrs_rt){
        try{
            obj = (RMI_Interface)Naming.lookup(address);
            
        }
        catch(MalformedURLException e){
            e.printStackTrace();
        }
        catch(NotBoundException e){
            e.printStackTrace();
        }
        catch (RemoteException e){
            e.printStackTrace();
        }
    }
    
    public static List<SalaryHistory> searchHistorybyDate(Date date){
        List<SalaryHistory> sh_list = new ArrayList<>();
        try{
            obj = (RMI_Interface)Naming.lookup(address);
            
        }
        catch(MalformedURLException e){
            e.printStackTrace();
        }
        catch(NotBoundException e){
            e.printStackTrace();
        }
        catch (RemoteException e){
            e.printStackTrace();
        }
        return sh_list;
    }
    
    public static double getBasicSalary(String emp_id)throws SQLException{
        double bs = 0;
        try{
            obj = (RMI_Interface)Naming.lookup(address);
            
        }
        catch(MalformedURLException e){
            e.printStackTrace();
        }
        catch(NotBoundException e){
            e.printStackTrace();
        }
        catch (RemoteException e){
            e.printStackTrace();
        }
        
        return bs;
    }
    
    public static int mapMonthToNumber(String month){
        switch(month){
            case "January": return 1;
            case "February": return 2;
            case "March": return 3;
            case "April": return 4;
            case "May": return 5;
            case "June": return 6;
            case "July": return 7;
            case "August": return 8;
            case "September": return 9;
            case "October": return 10;
            case "November": return 11;
            case "December": return 12;
            default: throw new IllegalArgumentException("Invalid month: "+month);
        }
    }
}
