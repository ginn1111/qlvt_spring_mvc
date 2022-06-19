package entity;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "PHIEUTRA")
@NamedNativeQueries(
        @NamedNativeQuery(
                name="soLuongPhieuTraTrongThang",
                query="exec sp_SoLuongPhieuTraTrongThang :m, :y",
                resultClass = Number.class

        )
)
public class PayedCoupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MAPHIEUTRA")
    private Integer pyCpId;

    @ManyToOne
    @JoinColumn(name = "MANHANVIEN")
    private Employee employee;

    @ManyToOne()
    @JoinColumn(name = "MAPHIEUMUON")
    private BorrowedCoupon borrowedCoupon;

    @ManyToOne
    @JoinColumn(name = "MACONGNHAN")
    private Worker worker;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "NGAYTRA")
    private Date payedDate;

    @ManyToOne
    @JoinColumn(name = "MATRANGTHAI")
    private CouponStatus cpStatus;

    @Column(name = "GHICHU")
    private String note;

    public Integer getPyCpId() {
        return pyCpId;
    }

    public void setPyCpId(Integer pyCpId) {
        this.pyCpId = pyCpId;
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

    public PayedCoupon() {
    }

    public PayedCoupon(Integer pyCpId) {
        this.pyCpId = pyCpId;
    }

    public BorrowedCoupon getBorrowedCoupon() {
        return borrowedCoupon;
    }

    public void setBorrowedCoupon(BorrowedCoupon borrowedCoupon) {
        this.borrowedCoupon = borrowedCoupon;
    }

    public PayedCoupon(Integer pyCpId, Employee employee, BorrowedCoupon borrowedCoupon, Worker worker, Date payedDate, CouponStatus cpStatus, String note) {
        this.pyCpId = pyCpId;
        this.employee = employee;
        this.borrowedCoupon = borrowedCoupon;
        this.worker = worker;
        this.payedDate = payedDate;
        this.cpStatus = cpStatus;
        this.note = note;
    }

    public Date getPayedDate() {
        return payedDate;
    }

    public void setPayedDate(Date payedDate) {
        this.payedDate = payedDate;
    }
}
