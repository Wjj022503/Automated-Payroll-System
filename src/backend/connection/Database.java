/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.connection;
import backend.Employee;
import backend.Payroll;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

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
                    employee.setPassword(resultSet.getString("password")); // Usually, you wouldn't retrieve passwords like this
                    employee.setIc_no(resultSet.getString("ic_no"));
                    employee.setContact_no(resultSet.getString("contact"));
                    employee.setDepartment(resultSet.getString("department"));
                }
            }
        }

        return employee;
    }
}
