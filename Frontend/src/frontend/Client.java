package frontend;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import shared.rmi_interface;

public class Client {
    private static rmi_interface Obj;
    private static final String address = "rmi://localhost:5000/AutomatedPayrollSystem";
    
    public static rmi_interface getRMIObject() {
        if (Obj == null) { 
            try {
                Obj = (rmi_interface) Naming.lookup(address);
            } catch (NotBoundException | MalformedURLException | RemoteException ex) {
                ex.printStackTrace();
                Obj = null;
            }
        }
        
        return Obj;
    }
}
