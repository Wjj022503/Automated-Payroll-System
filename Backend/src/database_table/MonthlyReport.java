package database_table;

import java.io.Serializable;

public class MonthlyReport implements Serializable{
     private String employeeId;
    private String fullName;
    private double baseSalary;
    private double grossSalary;
    private double netSalary;
    private double epf;
    private double socso;
    private double eis;
    private double additionalDeductions;
    private double leaveDeductions;

    public String getEmployeeId() { return employeeId; }
    public void setEmployeeId(String employeeId) { this.employeeId = employeeId; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public double getBaseSalary() { return baseSalary; }
    public void setBaseSalary(double baseSalary) { this.baseSalary = baseSalary; }
    public double getGrossSalary() { return grossSalary; }
    public void setGrossSalary(double grossSalary) { this.grossSalary = grossSalary; }
    public double getNetSalary() { return netSalary; }
    public void setNetSalary(double netSalary) { this.netSalary = netSalary; }
    public double getEpf() { return epf; }
    public void setEpf(double epf) { this.epf = epf; }
    public double getSocso() { return socso; }
    public void setSocso(double socso) { this.socso = socso; }
    public double getEis() { return eis; }
    public void setEis(double eis) { this.eis = eis; }
    public double getAdditionalDeductions() { return additionalDeductions; }
    public void setAdditionalDeductions(double additionalDeductions) { this.additionalDeductions = additionalDeductions; }
    public double getLeaveDeductions() { return leaveDeductions; }
    public void setLeaveDeductions(double leaveDeductions) { this.leaveDeductions = leaveDeductions; }
}
