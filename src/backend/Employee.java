/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend;

import java.io.Serializable;

/**
 *
 * @author wjing
 */
public class Employee implements Serializable{
    private String id;
    private String name;
    private String password;
    private String ic_no;
    private String contact_no;
    private String department;
    
    //constructor
    public Employee(){
    
    }
    
    public Employee(String name,String password,String ic_no,String contact_no,String department){
        this.name = name;
        this.password = password;
        this.ic_no = ic_no;
        this.contact_no = contact_no;
        this.department = department;
    }
    
    //get set method
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getIc_no() {
        return ic_no;
    }

    public String getContact_no() {
        return contact_no;
    }
    
    public String getDepartment() {    
        return department;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setIc_no(String ic_no) {
        this.ic_no = ic_no;
    }

    public void setContact_no(String contact_no) {
        this.contact_no = contact_no;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
