package registration.backend;
import java.rmi.registry.Registry;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;

public class registration_registry {
    public static void main (String args[])throws RemoteException{
        try{
            registration_server server = new registration_server();

            LocateRegistry.createRegistry(5000);
            Naming.rebind("rmi://192.168.100.33:5000/registration_interface", server);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
