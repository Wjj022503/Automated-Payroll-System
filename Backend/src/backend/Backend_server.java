package backend;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.security.*;
import database_table.Employee;
import shared.rmi_interface;

public class Backend_server extends UnicastRemoteObject implements rmi_interface{
    public Backend_server() throws RemoteException{
        super();
    }
    
    @Override
    
    public Employee Login(String username, String password) throws RemoteException{
        Employee currentUser = null;
        String salt;
        String correctPassword = null;
        String hashPassword = null;
        StringBuilder hexString = null;
        
        try{
           Database db = new Database();
           currentUser = db.getEmployeebyUsername(username);
           
           if (currentUser == null){
               return null;
           }
           
           salt = currentUser.getSalt();
           correctPassword = currentUser.getPassword();
           hashPassword = password + salt;
        } catch(SQLException ex){
           ex.printStackTrace();
        }
        
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(hashPassword.getBytes());
            hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
        } catch (NoSuchAlgorithmException e) {
            System.out.println("SHA-256 algorithm not found.");
            return null;
        }
        
        if(hexString.toString().equals(correctPassword) && correctPassword != null){
            return currentUser;
        }
        
        return null;
    }
    
    @Override
    
    public String registrationRequest(Employee employee) throws RemoteException{
        int insertRow = 0;
        String id = null;
        
        String salt = "RMI";
        employee.setSalt(salt);
        
        String password = employee.getPassword();
        String hashPassword = password + salt;
        StringBuilder hexString = null;
        
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(hashPassword.getBytes());
            hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
        } catch (NoSuchAlgorithmException e) {
            System.out.println("SHA-256 algorithm not found.");
            return "Failed to hash user password for " + employee.getFullName() + ".";
        }
        
        employee.setPassword(hexString.toString());
        
        try{
            Database db = new Database();
            id = db.generateNewEmployeeID();
            db.closeConnection();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        
        if (id != null){
            employee.setID(id);
        } else{
            return "Failed to generate new ID for " + employee.getFullName() + ".";
        }
        
        try{
            Database db = new Database();
            insertRow = db.registerEmployeeAccount(employee);
            db.closeConnection();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        
        if (insertRow > 0) {
            return "Success";
        } else {
            return "Failed to register user " + employee.getFullName() + ".";
        }
    }
    
    @Override
    
    public String uniqueName(String username) throws RemoteException{
        String isUnique = null;
        try{
            Database db = new Database();
            isUnique = db.uniqueName(username);
            db.closeConnection();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return isUnique;
    }
}
    

