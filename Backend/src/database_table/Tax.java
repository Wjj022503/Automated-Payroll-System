/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database_table;

import java.io.Serializable;

/**
 *
 * @author wjing
 */
public class Tax implements Serializable{
    private static final double EPF_RATE = 0.11; // 11% of gross salary
    private static final double SOCSO_RATE = 0.005; // 0.5% of gross salary
    private static final double EIS_RATE = 0.002; // 0.2% of gross salary
    private static final double INCOME_TAX_RATE = 0.1; // 10% for simplicity
    private double epf;
    private double socso;
    private double eis;
    private double income_tax;
    
    //constructor
    public Tax(){
    
    }
    public Tax(double epf ,double socso, double eis, double income_tax){
        this.epf = epf;
        this.socso = socso;
        this.eis = eis;
        this.income_tax = income_tax;
    }
    
    
    // public methods
    public void tax_update(double grossSalary){
        this.epf = Double.parseDouble(String.format("%.2f",grossSalary * EPF_RATE));
        this.socso = Double.parseDouble(String.format("%.2f",grossSalary * SOCSO_RATE));
        this.eis = Double.parseDouble(String.format("%.2f",grossSalary * EIS_RATE));
        this.income_tax = Double.parseDouble(String.format("%.2f",grossSalary * INCOME_TAX_RATE));
    }
    
    public double getTotalTax(){
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

    public void setEpf(double epf) {
        this.epf = epf;
    }

    public void setSocso(double socso) {
        this.socso = socso;
    }

    public void setEis(double eis) {
        this.eis = eis;
    }

    public void setIncome_tax(double income_tax) {
        this.income_tax = income_tax;
    }
}
