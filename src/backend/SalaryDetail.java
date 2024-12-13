/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend;

/**
 *
 * @author wjing
 */
public class SalaryDetail {
    private String sd_id;
    private double base_salary;
    private int working_hours;
    private double hourly_rate;
    private String employee_id;
    
    //constructor
    public SalaryDetail(){
    
    }
    
    public SalaryDetail(String sd_id, double base_salary, int working_hours, double hourly_rate) {
        this.sd_id = sd_id;
        this.base_salary = base_salary;
        this.working_hours = working_hours;
        this.hourly_rate = hourly_rate;
    }
    
    //get set methods
    public String getSd_id() {
        return sd_id;
    }

    public double getBase_salary() {
        return base_salary;
    }

    public int getWorking_hours() {
        return working_hours;
    }

    public double getHourly_rate() {
        return hourly_rate;
    }
    
    public String getEmployee_Id(){
        return this.employee_id;
    }

    public void setSd_id(String sd_id) {
        this.sd_id = sd_id;
    }

    public void setBase_salary(double base_salary) {
        this.base_salary = base_salary;
    }

    public void setWorking_hours(int working_hours) {
        this.working_hours = working_hours;
    }

    public void setHourly_rate(double hourly_rate) {
        this.hourly_rate = hourly_rate;
    }
    
    public void setEmployee_Id(String employee_id){
        this.employee_id = employee_id;
    }
}
