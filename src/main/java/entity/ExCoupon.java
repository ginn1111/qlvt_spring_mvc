package entity;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "PHIEUXUAT")
@NamedNativeQueries({
        @NamedNativeQuery(
                name="soLuongPhieuXuatTrongThang",
                query="exec sp_SoLuongPhieuXuatTrongThang :m, :y",
                resultClass = Number.class

        ),
        @NamedNativeQuery(
                name="soLuongPhieuXuatTrongThangNhanVien",
                query="exec sp_SoLuongPhieuXuatTrongThangNhanVien :m, :y, :id",
                resultClass = Number.class

        )
} )
public class ExCoupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MAPHIEUXUAT")
    private Integer exCpId;

    @ManyToOne
    @JoinColumn(name = "MANHANVIEN")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "MACONGNHAN")
    private Worker worker;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "THOIGIAN")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "MACONGTRINH")
    private Construction construction;

    @ManyToOne
    @JoinColumn(name = "MATRANGTHAI")
    private CouponStatus cpStatus;

    public Integer getExCpId() {
        return exCpId;
    }

    public void setExCpId(Integer exCpId) {
        this.exCpId = exCpId;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Construction getConstruction() {
        return construction;
    }

    public void setConstruction(Construction construction) {
        this.construction = construction;
    }

    public CouponStatus getCpStatus() {
        return cpStatus;
    }

    public void setCpStatus(CouponStatus cpStatus) {
        this.cpStatus = cpStatus;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public ExCoupon() {
    }

    public ExCoupon(Integer exCpId) {
        this.exCpId = exCpId;
    }

    public ExCoupon(Integer exCpId, Employee employee, Worker worker, Date date, Construction construction, CouponStatus cpStatus, String note) {
        this.exCpId = exCpId;
        this.employee = employee;
        this.worker = worker;
        this.date = date;
        this.construction = construction;
        this.cpStatus = cpStatus;
        this.note = note;
    }

    @Column(name = "GHICHU")
    private String note;

}
