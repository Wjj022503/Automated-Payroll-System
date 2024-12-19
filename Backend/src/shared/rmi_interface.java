package shared;

import java.rmi.Remote;
import java.rmi.RemoteException;
import database_table.*;
import java.sql.Date;
import java.util.List;
import java.time.LocalDate;

public interface rmi_interface extends Remote{
    public String registrationRequest(Employee employee) throws RemoteException;
    public Employee Login(String username, String password) throws RemoteException;
    public String uniqueName(String ID) throws RemoteException;
    public Employee getEmpDetails(String employee_id) throws RemoteException;
    public SalaryDetail getEmpSD(String employee_id)throws RemoteException;
    public SalaryDetail getSDbyID(String sd_id)throws RemoteException;
    public Deduction getEmpDD(String dd_id)throws RemoteException;
    public SalaryHistory getEmpSH(String employee_id, Date date)throws RemoteException;
    public List<SalaryHistory> searchHistoryByDate(Date date) throws RemoteException;
    public boolean updateSalaryDetail(SalaryDetail sd)throws RemoteException;
    public boolean updateSalaryDeduction(String employee_id,Deduction dd) throws RemoteException;
    public boolean updateSalaryHistory(Deduction dd, SalaryHistory sh, Date date) throws RemoteException;
    public LocalDate getFirstSalaryDate() throws RemoteException;
    public List<MonthlyReport> getMontlyReportData(LocalDate localdate) throws RemoteException;
}
