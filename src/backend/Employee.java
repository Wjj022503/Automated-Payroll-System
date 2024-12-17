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
    private String full_name;
    private String username;
    private String password;
    private String salt;
    private String ic_no;
    private String contact_no;
    private String department;
    
    //constructor
    public Employee(){
    
    }
    
    public Employee(String id,String full_name,String username,String password,String salt,String ic_no,String contact_no,String department){
        this.id = id;
        this.full_name = full_name;
        this.username = username;
        this.password = password;
        this.salt = salt;
        this.ic_no = ic_no;
        this.contact_no = contact_no;
        this.department = department;
    }
    
    //get set method
    public String getId() {
        return id;
    }

    public String getFull_name() {
        return full_name;
    }
    
    public String getUsername(){
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getSalt() {
        return salt;
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

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }
    
    public void setUsername(String username){
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSalt(String salt) {
        this.salt = salt;
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
