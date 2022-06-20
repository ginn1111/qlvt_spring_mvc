package entity;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "PHIEUCHUYENKHO")
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "soLuongPhieuChuyenKhoTrongThang",
                query = "exec sp_SoLuongPhieuChuyenKhoTrongThang :m, :y",
                resultClass = Number.class

        ),
        @NamedNativeQuery(
                name = "soLuongPhieuChuyenKhoTrongThangNhanVien",
                query = "exec sp_SoLuongPhieuChuyenKhoTrongThangNhanVien :m, :y, :id",
                resultClass = Number.class

        )
})
public class TransCoupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MAPHIEUCHUYENKHO")
    private Integer trCpId;

    @ManyToOne
    @JoinColumn(name = "MANHANVIEN")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "MAKHO")
    private Warehouse warehouse;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "THOIGIAN")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "MATRANGTHAI")
    private CouponStatus cpStatus;

    @Column(name = "GHICHU")
    private String note;

    public Integer getTrCpId() {
        return trCpId;
    }

    public void setTrCpId(Integer trCpId) {
        this.trCpId = trCpId;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public TransCoupon() {

    }

    public TransCoupon(Integer trCpId) {
        this.trCpId = trCpId;
    }

    public TransCoupon(Integer trCpId, Employee employee, Warehouse warehouse, Date date, CouponStatus cpStatus, String note) {
        this.trCpId = trCpId;
        this.employee = employee;
        this.warehouse = warehouse;
        this.date = date;
        this.cpStatus = cpStatus;
        this.note = note;
    }
}
