package entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "CONGNHAN")
public class Worker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="MACONGNHAN")
    private Integer workerId;

    @Column(name="TENCONGNHAN")
    private String name;

    @Column(name="SDT")
    private String phone;

    @Column(name="DIACHI")
    private String address;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name="NGAYSINH")
    private Date dob;

    public Integer getWorkerId() {
        return workerId;
    }

    public void setWorkerId(Integer workerId) {
        this.workerId = workerId;
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

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Worker() {
    }

    public Worker(Integer workerId, String name, String phone, String address, Date dob, Boolean status) {
        this.workerId = workerId;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.dob = dob;
        this.status = status;
    }

    @Column(name="TRANGTHAI")
    private Boolean status;
}
