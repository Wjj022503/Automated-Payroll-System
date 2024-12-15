package database_table;

import java.io.Serializable;

public class Employee implements Serializable {
    private String id;
    private String username;
    private String fullname;
    private String salt;
    private String password;
    private String ic_no;
    private String contact_no;
    private String department;
    
    public Employee(){
    }
    
    public Employee(String id, String username, String fullname, String salt, String password,String ic_no,String contact_no,String department){
        this.id = id;
        this.username = username;
        this.fullname = fullname;
        this.salt = salt;
        this.password = password;
        this.ic_no = ic_no;
        this.contact_no = contact_no;
        this.department = department;
    }

    public String getID(){ return this.id; }
    public void setID(String id){ this.id = id; }
    
    public String getUsername(){ return this.username; }
    public void setUsername(String username){ this.username = username; }
    
    public String getFullName(){return this.fullname; }
    public void setFullName(String fullname){ this.fullname = fullname; }
    
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

}
