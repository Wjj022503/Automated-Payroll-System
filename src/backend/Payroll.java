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
    private SalaryDetail salary_detail;
    private SalaryHistory salary_history;
    private Deduction deduction;
    
    //constructor
    Payroll(Employee employee, SalaryHistory salary_history, Deduction deduction) {
        this.employee = employee;
        this.salary_history = salary_history;
        this.deduction = deduction;
    }
    
    //private methods
    
    
    //public methods
    public double getNetSalary() {
        double gross_salary = salary_history.getGrossSalary(salary_detail.getBase_salary(),salary_detail.getHourly_rate());
        return gross_salary - deduction.getTotalDeductions(gross_salary);
    }
}
