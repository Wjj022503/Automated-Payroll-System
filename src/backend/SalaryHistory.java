/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend;
import java.io.Serializable;
import java.sql.Date;
/**
 *
 * @author wjing
 */
public class SalaryHistory implements Serializable{
    private String sh_id;
    private Date date;
    private double allowance;
    private double overtime_hours;
    private double net_salary;
    private double gross_salary;
    private String sd_id;
    private String dd_id;
    
    //constructor
    public SalaryHistory(){
    
    }
    
    public SalaryHistory(String sh_id, Employee employee, double base_salary, double hourly_rate, Date date, double working_hours, double allowance, double overtime_hours) {
        this.sh_id = sh_id;
        this.date = date;
        this.allowance = allowance;
        this.overtime_hours = overtime_hours;
    }
    
    //get set methods
    public String getSHId() {
        return this.sh_id;
    }

    public Date getDate() {
        return this.date;
    }

    public double getAllowance() {
        return this.allowance;
    }

    public double getOvertime_hours() {
        return this.overtime_hours;
    }
    
    public double getNet_salary(){
        return this.net_salary;
    }
    
    public double getGross_salary(){
        return this.gross_salary;
    }
    
    public String getSD_ID(){
        return this.sd_id;
    }
    
    public String getDD_ID(){
        return this.dd_id;
    }

    public void setSHId(String sh_id) {
        this.sh_id = sh_id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setAllowance(double allowance) {
        this.allowance = allowance;
    }

    public void setOvertime_hours(double overtime_hours) {
        this.overtime_hours = overtime_hours;
    }
    
    public void setNetSalary(double net_salary){
        this.net_salary = net_salary;
    }
    
    public void setGrossSalary(double gross_salary){
        this.gross_salary = gross_salary;
    }
    
    public void setSD_ID(String sd_id){
        this.sd_id = sd_id;
    }
    
    public void setDD_ID(String dd_id){
        this.dd_id = dd_id;
    }
}
