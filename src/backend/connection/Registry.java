/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.connection;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 *
 * @author wjing
 */
public class Registry {
    public static void main(String args[])throws RemoteException{
        java.rmi.registry.Registry reg = LocateRegistry.createRegistry(1040);
        reg.rebind("AutomatedPayrollSystem", new Server());
    }
}
