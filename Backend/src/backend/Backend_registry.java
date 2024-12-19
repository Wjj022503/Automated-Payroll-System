package backend;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Backend_registry {
    public static void main (String args[])throws RemoteException{
        try{
            InetAddress localHost = InetAddress.getLocalHost();
            String ipAddress = localHost.getHostAddress();
            
            System.out.println(ipAddress);
            
            String address = "rmi://" + ipAddress + ":5000/AutomatedPayrollSystem";
            Backend_server server = new Backend_server();
            LocateRegistry.createRegistry(5000);
            Naming.rebind(address, server);
            
            System.out.println("Server Ready");
        } catch (MalformedURLException | RemoteException | UnknownHostException e) {
            e.printStackTrace();
        }
    }
}

