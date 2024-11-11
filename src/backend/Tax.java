/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend;

/**
 *
 * @author wjing
 */
public class Tax {
    private static final double EPF_RATE = 0.11; // 11% of gross salary
    private static final double SOCSO_RATE = 0.005; // 0.5% of gross salary
    private static final double EIS_RATE = 0.002; // 0.2% of gross salary
    private static final double INCOME_TAX_RATE = 0.1; // 10% for simplicity
    private double epf;
    private double socso;
    private double eis;
    private double income_tax;
    
    //constructor
    Tax(){
    
    }    
    
    
    //private methods
    
    
    // public methods
    public double getTotalTax(double grossSalary){
        this.epf = grossSalary * EPF_RATE;
        this.socso = grossSalary * SOCSO_RATE;
        this.eis = grossSalary * EIS_RATE;
        this.income_tax = grossSalary * INCOME_TAX_RATE;
        
        return this.epf + this.socso + this.eis + this.income_tax;
    }

    //get set methods
    public double getEPF() {
        return this.epf;
    }

    public double getSOCSO() {
        
        return this.socso;
    }

    public double getEIS() {
        
        return this.eis;
    }

    public double getIncomeTax() {
        return this.income_tax;
    }
}
