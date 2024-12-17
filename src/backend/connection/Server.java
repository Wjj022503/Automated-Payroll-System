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
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
/**
 *
 * @author wjing
 */
public class Server extends UnicastRemoteObject implements RMI_Interface{
    
    private LocalDate ld = LocalDate.now();
    private Date current_date = Date.valueOf(ld);
    
    public Server() throws RemoteException{
        super();
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
        return null;
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
            //update salary details
            boolean update_result = false;
            boolean add_result = false;
            
            SalaryDetail sd_from_db = db.getEmpSD(sd.getEmployee_Id());
            //re-calculate hourly rate
            Double new_hrly_rate = Double.parseDouble(String.format("%.2f", sd.getBase_salary()/(sd.getWorking_hours()*22)));
            sd.setHourly_rate(new_hrly_rate);
            
            //check salary detail existing in DB
            if(sd_from_db.getSd_id() != null){
                System.out.println("-----Running UPDATEEEE--------");
                //update salary detail
                update_result = db.updateSalaryDetail(sd);
                SalaryHistory sh = db.getEmpSH(sd.getSd_id(), current_date);
                Deduction dd = db.getEmpDeduction(sh.getDD_ID());
                //update salary history and deduction
                updateSalaryHistory(dd,sh,current_date);
            }else{
                System.out.println("-----Running ADDD--------");
                //add salary detail
                String sd_id = db.addSalaryDetail(sd);
                if(sd_id != null){
                    add_result = true;
                }
                //initial first salary history and deduction to DB
                String new_dd_id = db.getNew_DDID();
                initialDeductionToDB(new_dd_id);
                initialSalaryHistoryToDB(sd_id,new_dd_id);
            }
            db.closeConnection();
            
            if(update_result || add_result){
                System.out.println("Salary detail update succesfully.");
                return true;
            }else{
                System.out.println("Salary detail update failed.");
                return false;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return false;
   }
   
   public boolean updateSalaryDeduction(String employee_id,Deduction dd) throws RemoteException{
        try {
            Database db = new Database();
            boolean update_result = false;
            boolean add_result = false;
            
            Deduction dd_from_db = db.getEmpDeduction(dd.getDd_id());
            
            if(dd_from_db.getDd_id() != null){
                //update deduction detail to DB
                update_result = db.updateSalaryDeduction(dd);               
            }else{
                //add deduction to DB
                add_result = db.addDeduction(dd);
            }
            
            db.closeConnection();
            
            if(update_result || add_result){
                System.out.println("Salary detail update succesfully.");
                return true;
            }else{    
                System.out.println("Salary detail update failed.");
                return false;
            }
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return false;
   }
   
    public boolean updateSalaryHistory(Deduction dd, SalaryHistory sh, Date date) throws RemoteException{
        try {
            Database db = new Database();
            boolean add_result = false;
            boolean update_result = false;
            
            SalaryHistory sh_from_db = db.getEmpSH(sh.getSD_ID(), date);
            SalaryDetail sd = db.getSDByID(sh.getSD_ID());
            
            if(sh_from_db.getSHId() != null){
                //calculate gross salary, net salary for update
                Payroll pr = new Payroll();
                double gross_salary = pr.getGrossSalary(sd.getBase_salary(), sd.getHourly_rate(), sh.getOvertime_hours(), sh.getAllowance());
                double net_salary = pr.getNetSalary(gross_salary, dd.getTotalDeductions(gross_salary));
                sh.setSHId(sh_from_db.getSHId());
                sh.setGrossSalary(gross_salary);
                sh.setNetSalary(net_salary);
                dd.setDd_id(sh_from_db.getDD_ID());
                
                //update to DB
                if(!db.updateSalaryDeduction(dd)){
                    System.out.println("-----------UPDATE DD ERROR-------------------");
                    System.out.println(dd.getDd_id());
                    System.out.println(dd.getLeave_deduction());
                    System.out.println(dd.getOther_deduction());
                    System.out.println(dd.getOther_deduction_reason());
                    System.out.println(dd.getTax().getEIS());
                    System.out.println(dd.getTax().getSOCSO());
                    System.out.println(dd.getTax().getIncomeTax());
                    System.out.println(dd.getTax().getEPF());
                }
                else if (!db.updateSalaryHistory(sh, sh.getDate())){
                    System.out.println("-----------UPDATE SH ERROR---------------");
                    System.out.println(sh.getSHId());
                    System.out.println(sh.getAllowance());
                    System.out.println(sh.getDD_ID());
                    System.out.println(sh.getGross_salary());
                    System.out.println(sh.getNet_salary());
                    System.out.println(sh.getOvertime_hours());
                }
                else{
                    update_result = true;
                }
            }
            else{
                //calculate gross salary, net salary for add
                Payroll pr = new Payroll();
                double gross_salary = pr.getGrossSalary(sd.getBase_salary(), sd.getHourly_rate(), sh.getOvertime_hours(), sh.getAllowance());
                double net_salary = pr.getNetSalary(gross_salary, dd.getTotalDeductions(gross_salary));
                sh.setGrossSalary(gross_salary);
                sh.setNetSalary(net_salary);
                dd.setDd_id(db.getNew_DDID());
                sh.setDD_ID(dd.getDd_id());
                add_result = (db.addDeduction(dd) && db.addSalaryHistory(sh));
            }
            
            db.closeConnection();
            
            if(update_result || add_result){
                System.out.println("Salary details & deduction update succesfully.");
                return true;
            }
            else{
                System.out.println("Salary details & deduction update failed.");
                return false;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return false;
   }
   
   //private method
   private boolean initialSalaryHistoryToDB(String sd_id, String dd_id){
       SalaryHistory sh = new SalaryHistory("",current_date,0,0,0,0,sd_id,dd_id);
       try{
           Database db = new Database();
           
           return db.addSalaryHistory(sh);
       }catch(SQLException ex){
           System.out.println(ex);
       }
       return false;
   }
   
   private boolean initialDeductionToDB(String dd_id){
       Tax tax = new Tax();
       tax.tax_update(0);
       Deduction dd = new Deduction(dd_id,tax,0,0,"");
       try{
           Database db = new Database();
           return db.addDeduction(dd);
       }catch(SQLException ex){
           System.out.println(ex);
       }
       return false;
   }
}
