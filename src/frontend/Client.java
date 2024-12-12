/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package frontend;
import java.rmi.*;
import java.net.MalformedURLException;
import backend.connection.RMI_Interface;
import backend.Employee;
/**
 *
 * @author wjing
 */
public class Client {
    private static RMI_Interface obj;
    private static String current_user_id = "E100001";
    
    public static Employee get_curr_user(){
        try{
            obj = (RMI_Interface)Naming.lookup("rmi://localhost:1040/main");
            Employee current_user = obj.getEmpDetails(current_user_id);
            return current_user;
        }
        catch(MalformedURLException e){
            e.printStackTrace();
        }
        catch(NotBoundException e){
            e.printStackTrace();
        }
        catch (RemoteException e){
            e.printStackTrace();
        }
        return null;
    }
}
