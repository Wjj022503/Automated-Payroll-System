/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package backend;
import java.rmi.*;
import java.sql.Date;
import java.util.List;
/**
 *
 * @author wjing
 * 
 */
public interface RMI_Interface extends Remote{
    public Employee getCurrentUser()throws RemoteException;
       public Employee getEmpDetails(String employee_id) throws RemoteException;
       public SalaryDetail getEmpSD(String employee_id)throws RemoteException;
       public SalaryDetail getSDbyID(String sd_id)throws RemoteException;
       public Deduction getEmpDD(String dd_id)throws RemoteException;
       public SalaryHistory getEmpSH(String employee_id, Date date)throws RemoteException;
       public List<SalaryHistory> searchHistoryByDate(Date date) throws RemoteException;
       public boolean updateSalaryDetail(SalaryDetail sd)throws RemoteException;
       public boolean updateSalaryDeduction(Deduction dd) throws RemoteException;
       public boolean updateSalaryHistory(SalaryHistory sh, Date date) throws RemoteException;
}
