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
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;

/**
 *
 * @author wjing
 */
public class Database{
    
    private Connection connection;
    private static final String address = "jdbc:derby://localhost:1527/payroll";
    private static final String username = "wjj123";
    private static final String password = "wjj02252003";
    
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
    
    public Employee getEmployee(String employeeId) throws SQLException {
        String query = "SELECT * FROM Employee WHERE employee_id = ?";
        Employee employee = new Employee();

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
    
    public SalaryDetail getSDByID(String sd_id) throws SQLException{
        String sl_query = "SELECT * FROM EMPLOYEE_SALARY_DETAILS WHERE sd_id = ?";
        SalaryDetail sl_detail = new SalaryDetail();
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(sl_query)) {
            preparedStatement.setString(1, sd_id);
            
            try (ResultSet sd_result = preparedStatement.executeQuery()) {
                if (sd_result.next()) {
                    sl_detail.setSd_id(sd_result.getString("sd_id"));
                    sl_detail.setBase_salary(sd_result.getDouble("base_salary"));
                    sl_detail.setHourly_rate(sd_result.getDouble("hourly_rate"));
                    sl_detail.setWorking_hours(sd_result.getInt("working_hours"));
                    sl_detail.setEmployee_Id(sd_result.getString("fk_emp_id"));
                }
            }
        }
        return sl_detail;
    }
    
    public SalaryDetail getEmpSD(String employeeId) throws SQLException{
        String sl_query = "SELECT * FROM EMPLOYEE_SALARY_DETAILS WHERE fk_emp_id = ?";
        SalaryDetail sl_detail = new SalaryDetail();
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(sl_query)) {
            preparedStatement.setString(1, employeeId);
            
            try (ResultSet sd_result = preparedStatement.executeQuery()) {
                if (sd_result.next()) {
                    sl_detail.setSd_id(sd_result.getString("sd_id"));
                    sl_detail.setBase_salary(sd_result.getDouble("base_salary"));
                    sl_detail.setHourly_rate(sd_result.getDouble("hourly_rate"));
                    sl_detail.setWorking_hours(sd_result.getInt("working_hours"));
                    sl_detail.setEmployee_Id(sd_result.getString("fk_emp_id"));
                }
            }
        }
        return sl_detail;
    }
    
    public SalaryHistory getEmpSH(String sd_id, Date date) throws SQLException{
        String sh_query = "SELECT * FROM EMPLOYEE_SALARY_HISTORY WHERE fk_sd_id = ? AND MONTH(date) = ? AND YEAR(date) = ?";
        SalaryHistory sh_detail = new SalaryHistory();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sh_query)) {
            preparedStatement.setString(1, sd_id);
            preparedStatement.setInt(2, date.toLocalDate().getMonthValue());
            preparedStatement.setInt(3, date.toLocalDate().getYear());

            try (ResultSet sh_result = preparedStatement.executeQuery()) {
                if (sh_result.next()) {
                    sh_detail.setSHId(sh_result.getString("sh_id"));
                    sh_detail.setAllowance(sh_result.getDouble("allowance"));
                    sh_detail.setDate(sh_result.getDate("date"));
                    sh_detail.setNetSalary(sh_result.getDouble("net_salary"));
                    sh_detail.setOvertime_hours(sh_result.getInt("overtime_hours"));
                    sh_detail.setSD_ID(sh_result.getString("fk_sd_id"));
                    sh_detail.setDD_ID(sh_result.getString("fk_dd_id"));
                }
            }
        }
        
        return sh_detail;
    }
    
    public Deduction getEmpDeduction(String dd_id)throws SQLException{
        String dd_query = "SELECT * FROM EMPLOYEE_DEDUCTION_DETAILS WHERE dd_id = ?";
        Deduction dd_detail = new Deduction();
        Tax tax = new Tax();
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(dd_query)) {
            preparedStatement.setString(1, dd_id);

            try (ResultSet dd_result = preparedStatement.executeQuery()) {
                if (dd_result.next()) {
                    dd_detail.setDd_id(dd_result.getString("dd_id"));
                    tax.setEpf(dd_result.getDouble("EPF"));
                    tax.setSocso(dd_result.getDouble("SOCSO"));
                    tax.setEis(dd_result.getDouble("EIS"));
                    tax.setIncome_tax(dd_result.getDouble("Income_Tax"));
                    dd_detail.setTax(tax);
                    dd_detail.setLeave_deduction(dd_result.getDouble("leave_deductions"));
                    dd_detail.setOther_deduction(dd_result.getDouble("additional_deductions"));
                    dd_detail.setOther_deduction_reason(dd_result.getString("deduction_reason"));
                }
            }
        }
        
        return dd_detail;
    }
    
    public List<SalaryHistory> get_all_sh_byDate(Date date)throws SQLException{
        List<SalaryHistory> sh_list = new ArrayList<>();
        String sh_query = "SELECT * FROM EMPLOYEE_SALARY_HISTORY WHERE MONTH(date) = ? AND YEAR(date) = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sh_query)){
            try (ResultSet sh_result = preparedStatement.executeQuery()){
                while(sh_result.next()){
                    SalaryHistory sh = new SalaryHistory();
                    sh.setSHId(sh_result.getString("sh_id"));
                    sh.setDate(sh_result.getDate("date"));
                    sh.setAllowance(sh_result.getDouble("allowance"));
                    sh.setOvertime_hours(sh_result.getDouble("overtime_hours"));
                    sh.setNetSalary(sh_result.getDouble("net_salary"));
                    sh.setSD_ID(sh_result.getString("fk_sd_id"));
                    sh.setDD_ID(sh_result.getString("fk_dd_id"));
                    sh_list.add(sh);
                }
            }
        }
        return sh_list;
    }
    
    public int updateSalaryDetail(SalaryDetail sd)throws SQLException{
        SalaryDetail sd_from_db = getSDByID(sd.getSd_id());
        if (sd_from_db.getSd_id() != null){
            String sd_update_query = "UPDATE EMPLOYEE_SALALRY_DETAILS SET base_salary = ?, working_hours = ?, hourly_rate = ? WHERE sd_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sd_update_query)){
                preparedStatement.setDouble(1,sd.getBase_salary());
                preparedStatement.setInt(2,sd.getWorking_hours());
                preparedStatement.setDouble(3, sd.getHourly_rate());
                preparedStatement.setString(4, sd_from_db.getSd_id());
                
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0){
                    System.out.println("Update complete. Rows affected: " + rowsAffected);
                    return 1; //updated successful
                }
                else{
                    return 3; //updated failed
                }
            }
        }else{
            boolean add_result = addSalaryDetail(sd);
            if (add_result){
                return 2; //added successful
            }
            else{
                return 4; //added failed
            }
        }
    }
    
    public int updateSalaryHistory(SalaryHistory sh, Date date)throws SQLException{
        SalaryHistory sh_from_db = getEmpSH(sh.getSHId(),date);
        if (sh_from_db.getSHId() != null){
            String sh_update_query = "UPDATE EMPLOYEE_SALALRY_HISTORY SET allowance = ?, overtime_hours = ?, net_salalry = ? WHERE sh_id = ? AND MONTH(date) = ? AND YEAR(date) = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sh_update_query)){
                
                preparedStatement.setDouble(1, sh.getAllowance());
                preparedStatement.setDouble(2, sh.getOvertime_hours());
                preparedStatement.setDouble(3, sh.getNet_salary());
                preparedStatement.setString(4, sh_from_db.getSHId());
                preparedStatement.setInt(5, date.toLocalDate().getMonthValue());
                preparedStatement.setInt(6, date.toLocalDate().getYear());
                
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0){
                    System.out.println("Update complete. Rows affected: " + rowsAffected);
                    return 1; //updated successful
                }
                else{
                    return 3; //updated failed
                }
            }
        }else{
            boolean add_result = addSalaryHistory(sh);
            if (add_result){
                return 2; //added successful
            }
            else{
                return 4; //added failed
            }
        }   
    }
    
    public int updateSalaryDeduction(Deduction dd)throws SQLException{
        Deduction dd_from_db = getEmpDeduction(dd.getDd_id());
        if (dd_from_db.getDd_id() != null){
            String dd_update_query = "UPDATE EMPLOYEE_DEDUCTION_DETAILS SET epf = ?, socso = ?, eis = ?, income_tax = ?, leave_deductions = ?, additional_deductions = ?, deduction_reason = ? WHERE dd_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(dd_update_query)){
                preparedStatement.setDouble(1, dd.getTax().getEPF());
                preparedStatement.setDouble(2, dd.getTax().getSOCSO());
                preparedStatement.setDouble(3, dd.getTax().getEIS());
                preparedStatement.setDouble(4, dd.getTax().getIncomeTax());
                preparedStatement.setDouble(5, dd.getLeave_deduction());
                preparedStatement.setDouble(6, dd.getOther_deduction());
                preparedStatement.setString(7, dd.getOther_deduction_reason());
                preparedStatement.setString(8, dd.getDd_id());
                
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0){
                    System.out.println("Update complete. Rows affected: " + rowsAffected);
                    return 1; //updated successful
                }
                else{
                    return 3; //updated failed
                }
            }
        }else{
            boolean add_result = addDeduction(dd);
            if (add_result){
                return 2; //added successful
            }
            else{
                return 4; //added failed
            }
        }
    }
    
    //private methods    
    private boolean addSalaryDetail(SalaryDetail sd)throws SQLException{
        String add_sd_query = "INSERT INTO EMPLOYEE_SALARY_HISTORY (sd_id,base_salary,working_hours,hourly_rate,fk_emp_id) VALUES (?,?,?,?,?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(add_sd_query)){
            preparedStatement.setString(1, getNew_SDID());
            preparedStatement.setDouble(2, sd.getBase_salary());
            preparedStatement.setInt(3, sd.getWorking_hours());
            preparedStatement.setDouble(4, sd.getHourly_rate());
            preparedStatement.setString(5, sd.getEmployee_Id());
            
            
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Salary detail added successfully.");
                return true;
            } else {
                System.out.println("No rows affected. Salary detail was not added.");
                return false;
            }    
        }
    }
    
    private boolean addDeduction(Deduction deduction)throws SQLException{
        String add_dd_query = "INSERT INTO EMPLOYEE_DEDUCTION_DETAILS (dd_id,epf,socso,eis,income_tax,leave_deductions,addtional_deductions,deduction_reason) VALUES (?,?,?,?,?,?,?,?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(add_dd_query)){
            preparedStatement.setString(1, getNew_DDID());
            preparedStatement.setDouble(2, deduction.getTax().getEPF());
            preparedStatement.setDouble(3, deduction.getTax().getSOCSO());
            preparedStatement.setDouble(4, deduction.getTax().getEIS());
            preparedStatement.setDouble(5, deduction.getTax().getIncomeTax());
            preparedStatement.setDouble(6, deduction.getLeave_deduction());
            preparedStatement.setDouble(7, deduction.getOther_deduction());
            preparedStatement.setString(8, deduction.getOther_deduction_reason());
            
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Deduction added successfully.");
                return true;
            } else {
                System.out.println("No rows affected. Deduction was not added.");
                return false;
            }
        }
    }
    
    private boolean addSalaryHistory(SalaryHistory sh)throws SQLException{
        String add_sh_query = "INSERT INTO EMPLOYEE_SALARY_HISTORY (sh_id,date,allowance,overtime_hours,net_salary,fk_sd_id,fk_dd_id) VALUES (?,?,?,?,?,?,?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(add_sh_query)){
            preparedStatement.setString(1, getNew_SHID());
            preparedStatement.setDate(2, sh.getDate());
            preparedStatement.setDouble(3, sh.getAllowance());
            preparedStatement.setDouble(4, sh.getOvertime_hours());
            preparedStatement.setDouble(5,sh.getNet_salary());
            preparedStatement.setString(6,sh.getSD_ID());
            preparedStatement.setString(7,sh.getDD_ID());
            
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Salary history added successfully.");
                return true;
            } else {
                System.out.println("No rows affected. Salary history was not added.");
                return false;
            }
        }
    }
    
    private String getNew_SHID()throws SQLException{
        String new_shid_query = "SELECT sh_id FROM EMPLOYEE_SALARY_HISTORY ORDER BY sd_id DESC LIMIT 1";
        String shid = "";
        try (PreparedStatement preparedStatement = connection.prepareStatement(new_shid_query)){
            try (ResultSet shid_result = preparedStatement.executeQuery()){
                shid = shid_result.getString("sh_id");
            }
        }
        String prefix = shid.substring(0,2);
        int numericPart = Integer.parseInt(shid.substring(2));
        
        int new_numericPart = numericPart + 1;
        
        return String.format("%s%06d",prefix,new_numericPart);
    }
    
    private String getNew_DDID()throws SQLException{
        String new_ddid_query = "SELECT sh_id FROM EMPLOYEE_DEDUCTION_DETAILS ORDER BY dd_id DESC LIMIT 1";
        String ddid = "";
        try (PreparedStatement preparedStatement = connection.prepareStatement(new_ddid_query)){
            try (ResultSet ddid_result = preparedStatement.executeQuery()){
                ddid = ddid_result.getString("dd_id");
            }
        }
        String prefix = ddid.substring(0,2);
        int numericPart = Integer.parseInt(ddid.substring(2));
        
        int new_numericPart = numericPart + 1;
        
        return String.format("%s%06d",prefix,new_numericPart);
    }
    
    private String getNew_SDID()throws SQLException{
        String new_sdid_query = "SELECT sd_id FROM EMPLOYEE_SALARY_DETAILS ORDER BY sd_id DESC LIMIT 1";
        String sdid = "";
        try (PreparedStatement preparedStatement = connection.prepareStatement(new_sdid_query)){
            try (ResultSet sdid_result = preparedStatement.executeQuery()){
                sdid = sdid_result.getString("sd_id");
            }
        }
        String prefix = sdid.substring(0,2);
        int numericPart = Integer.parseInt(sdid.substring(2));
        
        int new_numericPart = numericPart + 1;
        
        return String.format("%s%06d",prefix,new_numericPart);
    }
    
    public java.sql.Date getFirstDate() throws SQLException{
        String first_date_query = "SELECT MIN(date) AS first_date FROM Employee_Salary_History";
        java.sql.Date firstDate = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(first_date_query)){
            try (ResultSet rs = preparedStatement.executeQuery()){
                if (rs.next()) {
                    firstDate = rs.getDate("first_date");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return firstDate;
    }
}
