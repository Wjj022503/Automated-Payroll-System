package common;


import java.rmi.Remote;
import java.rmi.RemoteException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author shifong0303
 */
public interface rmiinterface extends Remote{
    public void checkConnection() throws RemoteException;    
    public boolean Login(String username, String password) throws RemoteException;

}
