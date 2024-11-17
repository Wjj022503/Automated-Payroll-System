package registration.frontend;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface registration_interface extends Remote{
    public String registrationRequest(userData userdata) throws RemoteException;
}
