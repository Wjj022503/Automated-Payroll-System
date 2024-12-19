package backend;

import java.sql.*;
import database_table.*;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;

public class Database {
    private Connection connection;
    private static final String address = "jdbc:derby://localhost:1527/db";
    private static final String username = "aa";
    private static final String password = "aa";
    
    Database()throws SQLException{
        this.connection = DriverManager.getConnection(address,username,password);
        if (connection != null){
            System.out.println("Connected to database.");
        }
        else{
            System.out.println("Failed to connect database.");
        }
    }
    
    public void closeConnection() throws SQLException{
        if (this.connection != null){
            this.connection.close();
        }
        else{
            System.out.println("No connection to database.");
        }
    }
    
    public Employee getEmployeebyUsername(String username) throws SQLException{
        String employeeSelector = "SELECT * FROM Employee WHERE username=?";
        PreparedStatement pstmt = connection.prepareStatement(employeeSelector);
        pstmt.setString(1, username);
        ResultSet loginEmployee = pstmt.executeQuery();
        if (loginEmployee.next()) {
            Employee employee = new Employee();
            employee.setID(loginEmployee.getString("employee_id"));
            employee.setFullName(loginEmployee.getString("fullname"));
            employee.setUsername(loginEmployee.getString("username"));
            employee.setSalt(loginEmployee.getString("salt"));
            employee.setPassword(loginEmployee.getString("password"));
            employee.setICNO(loginEmployee.getString("ic_no"));
            employee.setContactNO(loginEmployee.getString("contact"));
            employee.setDepartment(loginEmployee.getString("department"));
            return employee;
        }
        return null; 
    }
    
    public int registerEmployeeAccount(Employee employee) throws SQLException{
        String query = "INSERT INTO EMPLOYEE(employee_id, username, fullname, password, salt, ic_no, contact, department) " +
                   "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = connection.prepareStatement(query);

        stmt.setString(1, employee.getID());
        stmt.setString(2, employee.getUsername());
        stmt.setString(3, employee.getFullName());
        stmt.setString(4, employee.getPassword());
        stmt.setString(5, employee.getSalt());
        stmt.setString(6, employee.getICNO());
        stmt.setString(7, employee.getContactNO());
        stmt.setString(8, employee.getDepartment());

        int insertRow = stmt.executeUpdate();
        return insertRow;
    }
    
    public String generateNewEmployeeID() throws SQLException {
        String fetchMaxIDQuery = "SELECT MAX(employee_id) AS max_id FROM EMPLOYEE";
        String newID;

        PreparedStatement stmt = connection.prepareStatement(fetchMaxIDQuery);
        ResultSet rs = stmt.executeQuery();

        if (rs.next() && rs.getString("max_id") != null) {
            String maxID = rs.getString("max_id");
            int number = Integer.parseInt(maxID.substring(1)); 
            newID = "E" + (number + 1); 
        } else {
            newID = "E1";
        }
    return newID;
    }
    
    public String uniqueName(String username) throws SQLException{
        String query = "SELECT COUNT(*) AS user_count FROM EMPLOYEE WHERE username = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, username);
        
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
        int count = rs.getInt("user_count");
            if (count == 0) {
                return "Unique";
            } else {
                return "notUnique";
            }
        }
        return "notUnique";
    }
    
    public Employee getEmployee(String employeeId) throws SQLException {
        String query = "SELECT * FROM Employee WHERE employee_id = ?";
        Employee employee = new Employee();

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, employeeId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    employee = new Employee();
                    employee.setID(resultSet.getString("employee_id"));
                    employee.setFullName(resultSet.getString("fullname"));
                    employee.setUsername(resultSet.getString("username"));
                    employee.setICNO(resultSet.getString("ic_no"));
                    employee.setContactNO(resultSet.getString("contact"));
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
                    sh_detail.setGrossSalary(sh_result.getDouble("gross_salary"));
                    sh_detail.setOvertime_hours(sh_result.getInt("overtime_hours"));
                    sh_detail.setSD_ID(sh_result.getString("fk_sd_id"));
                    sh_detail.setDD_ID(sh_result.getString("fk_dd_id"));
                }
            }
        }
        System.out.println(sh_detail.getSHId());
        return sh_detail;
    }
    
    public SalaryHistory getSHById(String sh_id, Date date) throws SQLException{
        String sh_query = "SELECT * FROM EMPLOYEE_SALARY_HISTORY WHERE sh_id = ? AND MONTH(date) = ? AND YEAR(date) = ?";
        SalaryHistory sh_detail = new SalaryHistory();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sh_query)) {
            preparedStatement.setString(1, sh_id);
            preparedStatement.setInt(2, date.toLocalDate().getMonthValue());
            preparedStatement.setInt(3, date.toLocalDate().getYear());

            try (ResultSet sh_result = preparedStatement.executeQuery()) {
                if (sh_result.next()) {
                    sh_detail.setSHId(sh_result.getString("sh_id"));
                    sh_detail.setAllowance(sh_result.getDouble("allowance"));
                    sh_detail.setDate(sh_result.getDate("date"));
                    sh_detail.setNetSalary(sh_result.getDouble("net_salary"));
                    sh_detail.setGrossSalary(sh_result.getDouble("gross_salary"));
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
        
        LocalDate localDate = date.toLocalDate();
        int month = localDate.getMonthValue();
        int year = localDate.getYear();
        
        String sh_query = "SELECT * FROM EMPLOYEE_SALARY_HISTORY WHERE MONTH(date) = ? AND YEAR(date) = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sh_query)){
            stmt.setInt(1, month);
            stmt.setInt(2, year);
            
            try (ResultSet sh_result = stmt.executeQuery()){
                while(sh_result.next()){
                    SalaryHistory sh = new SalaryHistory();
                    sh.setSHId(sh_result.getString("sh_id"));
                    sh.setDate(sh_result.getDate("date"));
                    sh.setAllowance(sh_result.getDouble("allowance"));
                    sh.setOvertime_hours(sh_result.getDouble("overtime_hours"));
                    sh.setNetSalary(sh_result.getDouble("net_salary"));
                    sh.setGrossSalary(sh_result.getDouble("gross_salary"));
                    sh.setSD_ID(sh_result.getString("fk_sd_id"));
                    sh.setDD_ID(sh_result.getString("fk_dd_id"));
                    sh_list.add(sh);
                }
                
            }
        }
        return sh_list;
    }
    
    public boolean updateSalaryDetail(SalaryDetail sd)throws SQLException{
        String sd_update_query = "UPDATE EMPLOYEE_SALARY_DETAILS SET base_salary = ?, working_hours = ?, hourly_rate = ? WHERE sd_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sd_update_query)){
            preparedStatement.setDouble(1,sd.getBase_salary());
            preparedStatement.setInt(2,sd.getWorking_hours());
            preparedStatement.setDouble(3, sd.getHourly_rate());
            preparedStatement.setString(4, sd.getSd_id());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0){
                System.out.println("Update complete. Rows affected: " + rowsAffected);
                return true; //updated successful
            }
            else{
                return false; //updated failed
            }
        }
    }
    
    public boolean updateSalaryHistory(SalaryHistory sh, Date date)throws SQLException{
        String sh_update_query = "UPDATE EMPLOYEE_SALARY_HISTORY SET allowance = ?, overtime_hours = ?, net_salary = ?, gross_salary = ? WHERE sh_id = ? AND MONTH(date) = ? AND YEAR(date) = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sh_update_query)){

            preparedStatement.setDouble(1, sh.getAllowance());
            preparedStatement.setDouble(2, sh.getOvertime_hours());
            preparedStatement.setDouble(3, sh.getNet_salary());
            preparedStatement.setDouble(4, sh.getGross_salary());
            preparedStatement.setString(5, sh.getSHId());
            preparedStatement.setInt(6, date.toLocalDate().getMonthValue());
            preparedStatement.setInt(7, date.toLocalDate().getYear());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0){
                System.out.println("Update complete. Rows affected: " + rowsAffected);
                return true; //updated successful
            }
            else{
                return false; //updated failed
            }
        }
    }
    
    public boolean updateSalaryDeduction(Deduction dd)throws SQLException{
        String dd_update_query = "UPDATE EMPLOYEE_DEDUCTION_DETAILS SET epf = ?, socso = ?, eis = ?, income_tax = ?, leave_deductions = ?, ADDITIONAL_DEDUCTIONS = ?, deduction_reason = ? WHERE dd_id = ?";
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
                return true; //updated successful
            }
            else{
                return false; //updated failed
            }
        }
    }
      
    public String addSalaryDetail(SalaryDetail sd)throws SQLException{
        String add_sd_query = "INSERT INTO EMPLOYEE_SALARY_DETAILS (sd_id,base_salary,working_hours,hourly_rate,fk_emp_id) VALUES (?,?,?,?,?)";
        String new_sd_id = getNew_SDID();
        try (PreparedStatement preparedStatement = connection.prepareStatement(add_sd_query)){
            preparedStatement.setString(1, new_sd_id);
            preparedStatement.setDouble(2, sd.getBase_salary());
            preparedStatement.setInt(3, sd.getWorking_hours());
            preparedStatement.setDouble(4, sd.getHourly_rate());
            preparedStatement.setString(5, sd.getEmployee_Id());
            
            
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Salary detail added successfully.");
                return new_sd_id;
            } else {
                System.out.println("No rows affected. Salary detail was not added.");
                return null;
            }    
        }
    }
    
    public boolean addDeduction(Deduction deduction)throws SQLException{
        String add_dd_query = "INSERT INTO EMPLOYEE_DEDUCTION_DETAILS (dd_id,epf,socso,eis,income_tax,leave_deductions,ADDITIONAL_DEDUCTIONS,deduction_reason) VALUES (?,?,?,?,?,?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(add_dd_query)){
            preparedStatement.setString(1, deduction.getDd_id());
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
    
    public boolean addSalaryHistory(SalaryHistory sh)throws SQLException{
        String add_sh_query = "INSERT INTO EMPLOYEE_SALARY_HISTORY (sh_id,date,allowance,overtime_hours,net_salary,gross_salary,fk_sd_id,fk_dd_id) VALUES (?,?,?,?,?,?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(add_sh_query)){
            preparedStatement.setString(1, getNew_SHID());
            preparedStatement.setDate(2, sh.getDate());
            preparedStatement.setDouble(3, sh.getAllowance());
            preparedStatement.setDouble(4, sh.getOvertime_hours());
            preparedStatement.setDouble(5,sh.getNet_salary());
            preparedStatement.setDouble(6,sh.getGross_salary());
            preparedStatement.setString(7,sh.getSD_ID());
            preparedStatement.setString(8,sh.getDD_ID());
            
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
    
    public String getNew_DDID()throws SQLException{
        String new_ddid_query = "SELECT dd_id FROM EMPLOYEE_DEDUCTION_DETAILS ORDER BY dd_id DESC FETCH FIRST 1 ROW ONLY";
        String ddid = "";
        try (PreparedStatement preparedStatement = connection.prepareStatement(new_ddid_query)){
            try (ResultSet ddid_result = preparedStatement.executeQuery()){
                if(ddid_result.next()){
                    ddid = ddid_result.getString("dd_id");
                }
            }
        }
        String prefix = ddid.substring(0,2);
        int numericPart = Integer.parseInt(ddid.substring(2));
        
        int new_numericPart = numericPart + 1;
        
        return String.format("%s%06d",prefix,new_numericPart);
    }    
    
    //private methods  
    private String getNew_SHID()throws SQLException{
        String new_shid_query = "SELECT sh_id FROM EMPLOYEE_SALARY_HISTORY ORDER BY sh_id DESC FETCH FIRST 1 ROW ONLY";
        String shid = "";
        try (PreparedStatement preparedStatement = connection.prepareStatement(new_shid_query)){
            try (ResultSet shid_result = preparedStatement.executeQuery()){
                if(shid_result.next()){
                    shid = shid_result.getString("sh_id");
                }
            }
        }
        String prefix = shid.substring(0,2);
        int numericPart = Integer.parseInt(shid.substring(2));
        
        int new_numericPart = numericPart + 1;
        
        return String.format("%s%06d",prefix,new_numericPart);
    }
    
    private String getNew_SDID()throws SQLException{
        String new_sdid_query = "SELECT sd_id FROM EMPLOYEE_SALARY_DETAILS ORDER BY sd_id DESC FETCH FIRST 1 ROW ONLY";
        String sdid = "";
        try (PreparedStatement preparedStatement = connection.prepareStatement(new_sdid_query)){
            try (ResultSet sdid_result = preparedStatement.executeQuery()){
                if(sdid_result.next()){
                    sdid = sdid_result.getString("sd_id");
            
                }
            }
        }
        String prefix = sdid.substring(0,2);
        int numericPart = Integer.parseInt(sdid.substring(2));
        
        int new_numericPart = numericPart + 1;
        
        return String.format("%s%06d",prefix,new_numericPart);
    }
    
    public LocalDate getFirstSalaryDate(){
        String first_date_query = "SELECT MIN(date) AS first_date FROM Employee_Salary_History";
        java.sql.Date firstDate = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(first_date_query)) {
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    firstDate = rs.getDate("first_date");
                }
            }   
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        if (firstDate != null){
            LocalDate fDate = firstDate.toLocalDate();
            return fDate;
        } 
        return null;
    }
    
    public List<MonthlyReport> getMontlyReportResult(LocalDate localdate){
        List<MonthlyReport> historyList = new ArrayList<>();
        
        try{
            int month = localdate.getMonthValue();
            int year = localdate.getYear();
            
            String query = "SELECT e.employee_id, e.fullname, s.base_salary, h.gross_salary, h.net_salary, d.epf, " +
                "d.socso, d.eis, d.additional_deductions, d.leave_deductions " +
                "FROM Employee_Salary_History h " +
                "JOIN Employee_Salary_Details s ON h.fk_sd_id = s.sd_id " +
                "JOIN Employee e ON s.fk_emp_id = e.employee_id " +
                "JOIN Employee_Deduction_Details d ON h.fk_dd_id = d.dd_id " +
                "WHERE MONTH(h.date) = ? AND YEAR(h.date) = ?";

            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setInt(1, month);
                stmt.setInt(2, year);
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                    MonthlyReport mr = new MonthlyReport();
                    mr.setEmployeeId(rs.getString("employee_id"));
                    mr.setFullName(rs.getString("fullname"));
                    mr.setBaseSalary(rs.getDouble("base_salary"));
                    mr.setGrossSalary(rs.getDouble("gross_salary"));
                    mr.setNetSalary(rs.getDouble("net_salary"));
                    mr.setEpf(rs.getDouble("epf"));
                    mr.setSocso(rs.getDouble("socso"));
                    mr.setEis(rs.getDouble("eis"));
                    mr.setAdditionalDeductions(rs.getDouble("additional_deductions"));
                    mr.setLeaveDeductions(rs.getDouble("leave_deductions"));
                    historyList.add(mr);
                    }
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        
        return historyList;
    }
}
