/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package backend.connection;
import backend.Deduction;
import java.rmi.*;
import java.sql.SQLException;
import backend.Employee;
import backend.SalaryDetail;
import backend.SalaryHistory;
import java.sql.Date;
/**
 *
 * @author wjing
 * 
 */
public interface RMI_Interface extends Remote{
       public Employee getEmpDetails(String employee_id);
       public SalaryDetail getEmpSD(String employee_id);
       public Deduction getEmpDD(String employee_id,Date date);
       public SalaryHistory getEmpSH(String employee_id, Date date);
}
