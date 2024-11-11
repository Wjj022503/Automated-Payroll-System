/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend;

/**
 *
 * @author wjing
 */
public class Payroll {
    private Employee employee;
    private Salary salary;
    private Deduction deduction;
    
    //constructor
    Payroll(Employee employee, Salary salary, Deduction deduction) {
        this.employee = employee;
        this.salary = salary;
        this.deduction = deduction;
    }
    
    //private methods
    
    
    //public methods
    public double getNetSalary() {
        return salary.getGrossSalary() - deduction.getTotalDeductions(salary.getGrossSalary());
    }
}
