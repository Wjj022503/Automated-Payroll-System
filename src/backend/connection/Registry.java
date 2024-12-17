/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.connection;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author wjing
 */
public class Registry {
        public static void main(String[] args) throws RemoteException{
        // TODO code application logic here
        try{
            Server server = new Server();
            LocateRegistry.createRegistry(8888);
            Naming.rebind("rmi://192.168.100.19:8888/main", server);
            System.out.println("Server Ready");
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
