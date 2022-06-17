package entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="NHANVIEN")
//@NamedNativeQueries(
//        @NamedNativeQuery(name = "findEmployeeByName", query="exec sp_findEmployeeByName", resultClass = Employee.class)
//)
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="MANHANVIEN")
    private Integer employeeId;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name="NGAYSINH")
    private Date dob;
    @Column(name="TEN")
    private String name;

    @Column(name = "SDT")
    private String phone;

    @Column(name = "DIACHI")
    private String address;

    @Column(name = "TRANGTHAI")
    private Boolean status;

    public Employee(Integer employeeId, String name, String phone, String address, Boolean status, Date dob) {
        this.employeeId = employeeId;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.status = status;
        this.dob = dob;
    }

    public Employee() {

    }
    public Employee(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Date getDob() {
        return this.dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
