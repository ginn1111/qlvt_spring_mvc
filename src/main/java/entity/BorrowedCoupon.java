package entity;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
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
    private Date date;

    @ManyToOne
    @JoinColumn(name = "MACONGTRINH")
    private Construction construction;

    @ManyToOne
    @JoinColumn(name = "MAPTRANGTHAI")
    private CouponStatus cpStatus;

    @Column(name = "GHICHU")
    private String note;

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

    public BorrowedCoupon() {
    }

    public BorrowedCoupon(Integer brCpId, Employee employee, Worker worker, Date date, Construction construction, CouponStatus cpStatus, String note) {
        this.brCpId = brCpId;
        this.employee = employee;
        this.worker = worker;
        this.date = date;
        this.construction = construction;
        this.cpStatus = cpStatus;
        this.note = note;
    }
}
