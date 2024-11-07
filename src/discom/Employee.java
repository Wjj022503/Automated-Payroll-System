/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package discom;

/**
 *
 * @author wjing
 */
public class Employee {
    private String name;
    private String ic_no;
    private String contact_no;
    private double basic_salary;
    private double hourly_rate;
    private double allowance;
    private double overtimeHours;
    
    //constructor
    Employee(String name, String ic_no, String contact_no, double base_salary, double hourly_rate, double allowance, double overtimeHours){
        this.name = name;
        this.ic_no = ic_no;
        this.basic_salary = base_salary;
        this.hourly_rate = hourly_rate;
        this.allowance = allowance;
        this.overtimeHours = overtimeHours;
    }
    
    //get set methods
    
    
    //private methods
    private double getOvertimePay(){
        return this.overtimeHours * this.hourly_rate;
    }
    
    //public methods
    public double getGrossSalary(){
        return this.basic_salary + this.allowance + this.getOvertimePay();
    }
}
