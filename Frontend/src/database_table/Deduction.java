package database_table;
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
        this.tax = new Tax();
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

    public String getOther_deduction_reason() {
        return other_deduction_reason;
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
    public double getTotalDeductions(Double gross_salary) {
        tax.tax_update(gross_salary);
        return Double.parseDouble(String.format("%.2f",tax.getTotalTax() + this.leave_deduction + this.other_deduction));
    }
}
