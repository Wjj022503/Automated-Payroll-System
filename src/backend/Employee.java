/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend;

/**
 *
 * @author wjing
 */
public class Employee {
    private String name;
    private String username;
    private String salt;
    private String password;
    private String ic_no;
    private String contact_no;
    private String department;
    
    //constructor
     public Employee(){ super(); }
    
    Employee(String name,String password,String ic_no,String contact_no,String department){
        this.name = name;
        this.password = password;
        this.ic_no = ic_no;
        this.contact_no = contact_no;
        this.department = department;
    }

    //get set methods
    public String getName(){ return this.name; }
    public void setName(String name){ this.name = name; }
    
    public String getUsername(){ return this.username; }
    public void setUsername(String username){ this.username = username; }
    
    public String getSalt(){ return this.salt; }
    public void setSalt(String salt){ this.salt = salt; }
    
    public String getPassword(){ return this.password; }
    public void setPassword(String password){ this.password = password; }
    
    public String getICNO(){ return this.ic_no; }
    public void setICNO(String ic_no){ this.ic_no = ic_no; }
    
    public String getContactNO(){ return this.contact_no; }
    public void setContactNO(String contact_no){ this.contact_no = contact_no; }
    
    public String getDepartment(){ return this.department; }
    public void setDepartment(String department){ this.department = department; }
    
    //private methods
    
    
    //public methods
}
