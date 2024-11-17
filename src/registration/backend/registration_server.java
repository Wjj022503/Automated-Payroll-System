package registration.backend;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;

public class registration_server extends UnicastRemoteObject implements registration_interface{
    public registration_server() throws RemoteException{
        super();
    }
    
    @Override
    
    public String registrationRequest(userData userdata) throws RemoteException{
        String employeeID = "";
        String username = userdata.getUsername();
        String password = userdata.getPassword();
        String fullName = userdata.getFullName();
        String IC = userdata.getIC();
        String department = userdata.getDepartment();
        String contact = userdata.getContact();
        
        try (Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/registration","aa","aa")){
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO EMPLOYEE(USERNAME, PASSWORD, FULLNAME, IC_NO, CONTACT, DEPARTMENT)");

            stmt.setString(1, employeeID);
            stmt.setString(2, username);
            stmt.setString(3, password);
            stmt.setString(4, fullName);
            stmt.setString(5, IC);
            stmt.setString(6, contact);
            stmt.setString(7, department);
            
            int insertRow = stmt.executeUpdate();
            
            if (insertRow > 0) {
                return "Success";
            } else {
                return "Failed to register user " + fullName + ".";
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return "Error occurred during registration: " + e.getMessage();
        }
    }
}
    

