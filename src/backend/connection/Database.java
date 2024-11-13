/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.connection;
import backend.Employee;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author wjing
 */
public class Database{
    
    private Connection connection;
    
    Database(String address,String username, String password)throws SQLException{
        this.connection = DriverManager.getConnection(address,username,password);
        if (connection != null){
            System.out.println("Connected to Database.");
        }
        else{
            System.out.println("Connect to database failed.");
        }
    }
    
    public Employee getEmployeebyUsernamePassword(String username, String password) throws SQLException{
        String employeeSelector = "SELECT * FROM Employee WHERE username=? AND password=?";
        PreparedStatement pstmt = connection.prepareStatement(employeeSelector);
        pstmt.setString(1, username);
        pstmt.setString(2, password);
        ResultSet loginEmployee = pstmt.executeQuery();
        if (loginEmployee.next()) {
            // Create and populate the DTO
            Employee employee = new Employee();
            employee.setName(loginEmployee.getString("name"));
            employee.setPassword(loginEmployee.getString("password"));
            employee.setICNO(loginEmployee.getString("ic_no"));
            employee.setContactNO(loginEmployee.getString("contact_no"));
            employee.setDepartment(loginEmployee.getString("department"));
            return employee;
        }
        return null; // Return null if no matching employee is found
    }
}
