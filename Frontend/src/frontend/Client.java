package frontend;

import shared.rmi_interface;
import java.rmi.*;
import java.net.MalformedURLException;
import java.sql.Date;
import java.util.List;
import java.util.ArrayList;
import database_table.*;
import java.time.LocalDate;


public class Client {
    private static rmi_interface Obj;
    private static final String address = "rmi://localhost:5000/AutomatedPayrollSystem";
    
    public static rmi_interface getRMIObject() {
        if (Obj == null) { 
            try {
                Obj = (rmi_interface) Naming.lookup(address);
            } catch (NotBoundException | MalformedURLException | RemoteException ex) {
                ex.printStackTrace();
                Obj = null;
            }
        }
        
        return Obj;
    }
    
    public static Employee getEmployee(String emp_id){
        Employee emp = new Employee();
        try{
            Obj = (rmi_interface)Naming.lookup(address);
            emp = Obj.getEmpDetails(emp_id);
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
            Obj = (rmi_interface)Naming.lookup(address);
            sd = Obj.getEmpSD(employee_id);
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
            Obj = (rmi_interface)Naming.lookup(address);
            sd = Obj.getSDbyID(sd_id);
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
            Obj = (rmi_interface)Naming.lookup(address);
            sh = Obj.getEmpSH(sd_id, date);
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
            Obj = (rmi_interface)Naming.lookup(address);
            dd = Obj.getEmpDD(dd_id);
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
    
    public static boolean updateMonthlySalary(Date date, String emp_id,double allowance, double ot_hrs, double ld, double ad, String dr){
        try{
            Obj = (rmi_interface)Naming.lookup(address);
            Deduction dd = new Deduction();
            dd.setLeave_deduction(ld);
            dd.setOther_deduction(ad);
            if(dr.isEmpty()){
                dr = "No reason.";
            }
            dd.setOther_deduction_reason(dr);
            
            SalaryDetail sd = Obj.getEmpSD(emp_id);
            SalaryHistory sh = new SalaryHistory();
            sh.setSD_ID(sd.getSd_id());
            sh.setDate(date);
            sh.setAllowance(allowance);
            sh.setOvertime_hours(ot_hrs);
            sh.setDate(date);
            
            return Obj.updateSalaryHistory(dd,sh, date);
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
    
    public static boolean updateBaseSalary(SalaryDetail sd){
        try{
            Obj = (rmi_interface)Naming.lookup(address);
            boolean update_result = Obj.updateSalaryDetail(sd);
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
            Obj = (rmi_interface)Naming.lookup(address);
            sh_list=Obj.searchHistoryByDate(date);
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
    
    public static LocalDate getfirstSalaryDate() throws MalformedURLException, NotBoundException, RemoteException{
        LocalDate firstDate;
        Obj = (rmi_interface)Naming.lookup(address);
        firstDate = Obj.getFirstSalaryDate();
        return firstDate;
    }
    
    public static List<MonthlyReport> getMontlyReportData(LocalDate localdate)throws MalformedURLException, NotBoundException, RemoteException{
        List<MonthlyReport> historyList;
        Obj = (rmi_interface)Naming.lookup(address);
        historyList = Obj.getMontlyReportData(localdate);
        return historyList;
    }
}
