package registration.frontend;
import java.io.Serializable;

public class userData implements Serializable {
    
    private String username;
    private String password;
    private String fullName;
    private String IC;
    private String department;
    private String contact;

    public userData(){
    }
    
    public userData(String username, String password, String fullName, String IC, String department, String contact) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.IC = IC;
        this.department = department;
        this.contact = contact;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getIC() {
        return IC;
    }

    public void setIC(String IC) {
        this.IC = IC;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}

