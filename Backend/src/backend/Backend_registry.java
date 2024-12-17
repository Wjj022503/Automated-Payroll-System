package backend;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;

public class Backend_registry {
    public static void main (String args[])throws RemoteException{
        try{
            Backend_server server = new Backend_server();
            LocateRegistry.createRegistry(5000);
            Naming.rebind("rmi://localhost:5000/AutomatedPayrollSystem", server);
            
            System.out.println("Server Ready");
        } catch (MalformedURLException | RemoteException e) {
            e.printStackTrace();
        }
    }
}
