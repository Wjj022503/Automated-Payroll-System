package backend;

import java.sql.*;
import database_table.Employee;

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
}
