/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package discom;

/**
 *
 * @author wjing
 */
public class Payroll {
    private Employee employee;

    public Payroll(Employee employee) {
        this.employee = employee;
    }

    public double getTotalDeductions() {
        double grossSalary = employee.getGrossSalary();
        return Deduction.getEPF(grossSalary) +
               Deduction.getSOCSO(grossSalary) +
               Deduction.getEIS(grossSalary) +
               Deduction.getIncomeTax(grossSalary);
    }

    public double getNetSalary() {
        return employee.getGrossSalary() - getTotalDeductions();
    }
}
