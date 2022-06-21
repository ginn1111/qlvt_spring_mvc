package entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="CONGNHAN")
@NamedNativeQueries(
        @NamedNativeQuery(name = "timCongNhan", query="exec sp_timCongNhan :key", resultClass = Worker.class)
)
public class Worker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="MACONGNHAN")
    private Integer workerId;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name="NGAYSINH")
    private Date dob;
    @Column(name="TENCONGNHAN")
    private String name;

    @Column(name = "SDT")
    private String phone;

    @Column(name = "DIACHI")
    private String address;

    @Column(name = "TRANGTHAI")
    private Boolean status;

    public Worker(Integer workerId, String name, String phone, String address, Boolean status, Date dob) {
        this.workerId = workerId;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.status = status;
        this.dob = dob;
    }

    public Worker() {

    }
    public Worker(Integer workerId) {
        this.workerId = workerId;
    }

    public Date getDob() {
        return this.dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
