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
    
    public static SalaryHistory getEmpSH(String sd_id, Date date){
        SalaryHistory sh = new SalaryHistory();
        try{
            obj = (RMI_Interface)Naming.lookup(address);
            sh = obj.getEmpSH(sd_id, date);
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
    
    public static void updateMonthlySalary(Date date, String emp_id,double allowance, double ot_hrs, double ld, double ad, String dr){
        try{
            obj = (RMI_Interface)Naming.lookup(address);
            Deduction dd = new Deduction();
            dd.setLeave_deduction(ld);
            dd.setOther_deduction(ad);
            if(dr.isEmpty()){
                dr = "No reason.";
            }
            dd.setOther_deduction_reason(dr);
            
            SalaryDetail sd = obj.getEmpSD(emp_id);
            SalaryHistory sh = new SalaryHistory();
            sh.setSD_ID(sd.getSd_id());
            sh.setDate(date);
            sh.setAllowance(allowance);
            sh.setOvertime_hours(ot_hrs);
            sh.setDate(date);
            
            obj.updateSalaryHistory(dd,sh, date);
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
    
    public static boolean updateBaseSalary(SalaryDetail sd){
        try{
            obj = (RMI_Interface)Naming.lookup(address);
            boolean update_result = obj.updateSalaryDetail(sd);
            return update_result;
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
        return false;
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
