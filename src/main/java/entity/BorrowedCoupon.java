package entity;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "PHIEUMUON")
@NamedNativeQueries(
        @NamedNativeQuery(
                name="soLuongPhieuMuonTrongThang",
                query="exec sp_SoLuongPhieuMuonTrongThang :m, :y",
                resultClass = Number.class

        )
)
public class BorrowedCoupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MAPHIEUMUON")
    private Integer brCpId;

    @ManyToOne
    @JoinColumn(name = "MANHANVIEN")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "MACONGNHAN")
    private Worker worker;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "NGAYMUON")
    private Date borrowedDate;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "NGAYTRA")
    private Date payedDate;

    @ManyToOne
    @JoinColumn(name = "MACONGTRINH")
    private Construction construction;

    @ManyToOne
    @JoinColumn(name = "MATRANGTHAI")
    private CouponStatus cpStatus;

    @Column(name = "GHICHU")
    private String note;

//    @OneToMany(mappedBy = "borrowedCoupon", fetch = FetchType.EAGER)
//    Collection<DetailBorrowedCoupon> detailBorrowedCouponList;
//
//    public Collection<DetailBorrowedCoupon> getDetailBorrowedCouponList() {
//        return detailBorrowedCouponList;
//    }
//
//    public void setDetailBorrowedCouponList(Collection<DetailBorrowedCoupon> detailBorrowedCouponList) {
//        this.detailBorrowedCouponList = detailBorrowedCouponList;
//    }


    public Integer getBrCpId() {
        return brCpId;
    }

    public void setBrCpId(Integer brCpId) {
        this.brCpId = brCpId;
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

    public BorrowedCoupon() {
    }

    public Date getBorrowedDate() {
        return borrowedDate;
    }

    public void setBorrowedDate(Date borrowedDate) {
        this.borrowedDate = borrowedDate;
    }

    public Date getPayedDate() {
        return payedDate;
    }

    public void setPayedDate(Date payedDate) {
        this.payedDate = payedDate;
    }

    public BorrowedCoupon(Integer brCpId) {
        this.brCpId = brCpId;
    }

    public BorrowedCoupon(Integer brCpId, Employee employee, Worker worker, Date borrowedDate, Date payedDate, Construction construction, CouponStatus cpStatus, String note) {
        this.brCpId = brCpId;
        this.employee = employee;
        this.worker = worker;
        this.borrowedDate = borrowedDate;
        this.payedDate = payedDate;
        this.construction = construction;
        this.cpStatus = cpStatus;
        this.note = note;
    }
}
