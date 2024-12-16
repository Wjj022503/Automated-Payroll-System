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
public class Deduction implements Serializable{
    private String dd_id;
    private Tax tax;
    private double leave_deduction;
    private double other_deduction;
    private String other_deduction_reason;
    
    //constructor
    public Deduction(){
    
    }
    public Deduction(String dd_id, Tax tax, double leave_deduction, double other_deduction, String other_deduction_reason) {
        this.dd_id = dd_id;
        this.tax = tax;
        this.leave_deduction = leave_deduction;
        this.other_deduction = other_deduction;
        this.other_deduction_reason = other_deduction_reason;
    }    
    
    //getter and setter
    public String getDd_id(){
        return dd_id;
    }

    public Tax getTax() {
        return tax;
    }

    public double getLeave_deduction() {
        return leave_deduction;
    }

    public double getOther_deduction() {
        return other_deduction;
    }

    public void setDd_id(String dd_id) {
        this.dd_id = dd_id;
    }

    public void setTax(Tax tax) {
        this.tax = tax;
    }

    public void setLeave_deduction(double leave_deduction) {
        this.leave_deduction = leave_deduction;
    }

    public void setOther_deduction(double other_deduction) {
        this.other_deduction = other_deduction;
    }

    public void setOther_deduction_reason(String other_deduction_reason) {
        this.other_deduction_reason = other_deduction_reason;
    }

    //public methods
    public String getOther_deduction_reason() {
        return other_deduction_reason;
    }

    public double getTotalDeductions(double gross_salary) {
        tax.tax_update(gross_salary);
        return tax.getTotalTax() + this.leave_deduction + this.other_deduction;
    }
    
    public String getOtherDeductionReason(){
        return this.other_deduction_reason;
    }
}
