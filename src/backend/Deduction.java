/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend;

/**
 *
 * @author wjing
 */
public class Deduction {
    private String id;
    private Tax tax;
    private double leave_deduction;
    private double other_deduction;
    private String other_deduction_reason;
    
    //constructor
    Deduction(String id, Tax tax, double leave_deduction, double other_deduction, String other_deduction_reason) {
        this.id = id;
        this.tax = tax;
        this.leave_deduction = leave_deduction;
        this.other_deduction = other_deduction;
        this.other_deduction_reason = other_deduction_reason;
    }    
    
    //private methods
    
    
    //public methods
    public double getTotalDeductions(double gross_salary){
        return tax.getTotalTax(gross_salary) + this.leave_deduction + this.other_deduction;
    }
    
    public String getOtherDeductionReason(){
        return this.other_deduction_reason;
    }
}
