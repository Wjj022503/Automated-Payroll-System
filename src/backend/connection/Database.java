/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.connection;
import backend.Employee;
import backend.Deduction;
import backend.SalaryHistory;
import backend.SalaryDetail;
import backend.Tax;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;

/**
 *
 * @author wjing
 */
public class Database{
    
    private Connection connection;
    private static final String address = "jdbc:derby://localhost:1527/Library";
    private static final String username = "tan";
    private static final String password = "tan";
    
    public Database() throws SQLException{
        this.connection = DriverManager.getConnection(this.address, this.username, this.password);
        if (connection != null){
            System.out.println("Connected to Database.");
        }
        else{
            System.out.println("Connect to database failed.");
        }
    }
    
    //public methods
    public void closeConnection() throws SQLException{
        if (this.connection != null){
            this.connection.close();
        }
        else{
            System.out.println("No connection to database.");
        }
    }
    
    public Employee findEmployeeById(String employeeId) throws SQLException {
        String query = "SELECT * FROM Employee WHERE employee_id = ?";
        Employee employee = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, employeeId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    employee = new Employee();
                    employee.setId(resultSet.getString("employee_id"));
                    employee.setName(resultSet.getString("name"));
                    employee.setIc_no(resultSet.getString("ic_no"));
                    employee.setContact_no(resultSet.getString("contact"));
                    employee.setDepartment(resultSet.getString("department"));
                }
            }
        }

        return employee;
    }
    
    public SalaryDetail getSD_by_empID(String employeeId) throws SQLException{
        String sl_query = "SELECT * FROM EMPLOYEE_SALARY_DETAILS employee_id = ?";
        SalaryDetail sl_detail = null;
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(sl_query)) {
            preparedStatement.setString(1, employeeId);
            
            try (ResultSet sd_result = preparedStatement.executeQuery()) {
                if (sd_result.next()) {
                    sl_detail.setSd_id(sd_result.getString("sd_id"));
                    sl_detail.setBase_salary(sd_result.getDouble("base_salary"));
                    sl_detail.setHourly_rate(sd_result.getDouble("hourly_rate"));
                    sl_detail.setWorking_hours(sd_result.getInt("working_hours"));
                    sl_detail.setEmployee_Id(sd_result.getString("employee_id"));
                }
            }
        }
        return sl_detail;
    }
    
    public SalaryHistory getEmpSalaryHistory(String employeeId, Date date) throws SQLException{
        String sh_query = "SELECT * FROM EMPLOYEE_SALARY_DETAILS WHERE sd_id = ? AND date = ?";
        SalaryHistory sh_detail = null;
        String sd_id = getSD_by_empID(employeeId).getSd_id();
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(sh_query)) {
            preparedStatement.setString(1, sd_id);
            preparedStatement.setDate(2, date);

            try (ResultSet sh_result = preparedStatement.executeQuery()) {
                if (sh_result.next()) {
                    sh_detail.setSHId(sh_result.getString("sh_id"));
                    sh_detail.setAllowance(sh_result.getDouble("allowance"));
                    sh_detail.setDate(sh_result.getDate("date"));
                    sh_detail.setNetSalary(sh_result.getDouble("net_salary"));
                    sh_detail.setOvertime_hours(sh_result.getInt("overtime_hours"));
                    sh_detail.setSD_ID(sh_result.getString("sd_id"));
                    sh_detail.setDD_ID(sh_result.getString("dd_id"));
                }
            }
        }
        
        return sh_detail;
    }
    
    public Deduction getEmpDeduction(String employeeId, Date date)throws SQLException{
        String dd_id = getEmpSalaryHistory(employeeId,date).getDD_ID();
        String dd_query = "SELECT * FROM EMPLOYEE_DEDUCTION_DETAILS WHERE dd_id = ?";
        Deduction dd_detail = null;
        Tax tax = null;
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(dd_query)) {
            preparedStatement.setString(1, dd_id);

            try (ResultSet dd_result = preparedStatement.executeQuery()) {
                if (dd_result.next()) {
                    dd_detail.setDd_id(dd_result.getString("dd_id"));
                    tax.setEis(dd_result.getDouble("EPF"));
                    tax.setEis(dd_result.getDouble("SOCSO"));
                    tax.setEis(dd_result.getDouble("EIS"));
                    tax.setEis(dd_result.getDouble("Income_Tax"));
                    dd_detail.setTax(tax);
                    dd_detail.setLeave_deduction(dd_result.getDouble("leave_deductions"));
                    dd_detail.setOther_deduction(dd_result.getDouble("additional_deductions"));
                    dd_detail.setOther_deduction_reason(dd_result.getString("deduction_reason"));
                }
            }
        }
        
        return dd_detail;
    }
}
