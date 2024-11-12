/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package frontend;
import java.rmi.*;
import java.net.MalformedURLException;
import backend.connection.RMI_Interface;
import java.util.Scanner;
import java.sql.SQLException;
/**
 *
 * @author wjing
 */
public class Client {
    private static RMI_Interface obj;

    public static RMI_Interface connect() throws MalformedURLException, NotBoundException, RemoteException{
        obj = (RMI_Interface)Naming.lookup("rmi://localhost:1040/main");
        return obj;
    }
}
