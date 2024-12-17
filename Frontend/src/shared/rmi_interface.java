package shared;

import java.rmi.Remote;
import java.rmi.RemoteException;
import database_table.Employee;

public interface rmi_interface extends Remote{
    public String registrationRequest(Employee employee) throws RemoteException;
    public Employee Login(String username, String password) throws RemoteException;
    public String uniqueName(String ID) throws RemoteException;
}
