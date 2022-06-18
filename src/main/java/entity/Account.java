package entity;

import model.RoleModel;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity
@Table(name="TAIKHOAN")
public class Account {
   @Id
   @Column(name="EMAIL")
    private String email;

   @Column(name="MATKHAU")
    private String password;

    @ManyToOne
    @JoinColumn(name = "MAVAITRO")
    private Role role;

    @ManyToOne
    @JoinColumn(name = "MANHANVIEN")
    private Employee employee ;

    @Column(name="TRANGTHAI")
    private Boolean status;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Account() {
        this.status = true;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Role getRole() {
        return role;
    }

    public Account(String email) {
        this.email = email;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Account(String email, String password, Role role, Employee employee, Boolean status) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.employee = employee;
        this.status = status;
    }
}