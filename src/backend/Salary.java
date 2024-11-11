/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend;

import java.util.Date;

/**
 *
 * @author wjing
 */
public class Salary {
    private String id;
    private Employee employee;
    private double base_salary;
    private double hourly_rate;
    private Date date;
    private double working_hours;
    private double allowance;
    private double overtime_hours;
    
    //constructor
    Salary(String id, Employee employee, double base_salary, double hourly_rate, Date date, double working_hours, double allowance, double overtime_hours) {
        this.id = id;
        this.employee = employee;
        this.base_salary = base_salary;
        this.hourly_rate = hourly_rate;
        this.date = date;
        this.working_hours = working_hours;
        this.allowance = allowance;
        this.overtime_hours = overtime_hours;
    }
    
    //private methods
    private double getOvertimePay(){
        double overtime_hrs = this.overtime_hours;
        return overtime_hrs * this.hourly_rate;
    }
    
    //public methods
    public double getGrossSalary(){
        return this.base_salary + this.allowance + this.getOvertimePay();
    }
}
