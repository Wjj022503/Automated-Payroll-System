/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package discom;

/**
 *
 * @author wjing
 */
public class Deduction {
    private static final double EPF_RATE = 0.11; // 11% of gross salary
    private static final double SOCSO_RATE = 0.005; // 0.5% of gross salary
    private static final double EIS_RATE = 0.002; // 0.2% of gross salary
    private static final double INCOME_TAX_RATE = 0.1; // 10% for simplicity

    public static double getEPF(double grossSalary) {
        return grossSalary * EPF_RATE;
    }

    public static double getSOCSO(double grossSalary) {
        return grossSalary * SOCSO_RATE;
    }

    public static double getEIS(double grossSalary) {
        return grossSalary * EIS_RATE;
    }

    public static double getIncomeTax(double grossSalary) {
        return grossSalary * INCOME_TAX_RATE;
    }
}
