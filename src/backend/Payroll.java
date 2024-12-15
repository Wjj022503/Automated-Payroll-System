/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend;

import java.io.Serializable;

/**
 *
 * @author wjing
 */
public class Payroll implements Serializable{
    
    //constructor
    public Payroll() {

    }
    
    //public methods
    public double getOvertimePay(double hourly_rate, double overtime_hrs){
        return overtime_hrs * hourly_rate;
    }
    
    public double getGrossSalary(double base_salary, double hourly_rate, double overtime_hrs, double allowance){
        double overtime_pay = getOvertimePay(hourly_rate,overtime_hrs);
        return base_salary + overtime_pay + allowance;
    }
    
    public double getNetSalary(double gross_salary,double total_deduction) {
        return gross_salary - total_deduction;
    }
}
