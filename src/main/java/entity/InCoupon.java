package entity;

import org.hibernate.annotations.Cascade;
import org.springframework.format.annotation.DateTimeFormat;
import sun.reflect.CallerSensitive;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "PHIEUNHAP")
@NamedNativeQueries({
        @NamedNativeQuery(
                name="soLuongPhieuNhapTrongThang",
                query="exec sp_SoLuongPhieuNhapTrongThang :m, :y",
                resultClass = Number.class

        ), @NamedNativeQuery(
                name="soLuongPhieuNhapTrongThangNhanVien",
                query="exec sp_SoLuongPhieuNhapTrongThangNhanVien :m, :y, :id",
                resultClass = Number.class

        )
})
public class InCoupon implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MAPHIEUNHAP")
    private Integer inCpId;

    @ManyToOne
    @JoinColumn(name = "MANHANVIEN")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "MANHACUNGCAP")
    private Supplier supplier;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "THOIGIAN")
    private Date date;

    @Column(name = "TONGTIEN")
    private BigDecimal total;

    @ManyToOne
    @JoinColumn(name = "MATRANGTHAI")
    private CouponStatus cpStatus;

    @Column(name = "GHICHU")
    private String note;
    public Integer getInCpId() {
        return inCpId;
    }

    public void setInCpId(Integer inCpId) {
        this.inCpId = inCpId;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
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

    public InCoupon() {
    }

    public InCoupon(Integer inCpId, Employee employee, Supplier supplier, Date date, BigDecimal total, CouponStatus cpStatus, String note) {
        this.inCpId = inCpId;
        this.employee = employee;
        this.supplier = supplier;
        this.date = date;
        this.total = total;
        this.cpStatus = cpStatus;
        this.note = note;
    }

    public InCoupon(Integer iCPId) {
        this.inCpId = iCPId;
    }

    @Override
    public String toString() {
        return "InCoupon{" +
                "inCpId=" + inCpId +
                ", employee=" + employee +
                ", supplier=" + supplier +
                ", date=" + date +
                ", total=" + total +
                ", cpStatus=" + cpStatus +
                ", note='" + note + '\'' +
                '}';
    }
}
