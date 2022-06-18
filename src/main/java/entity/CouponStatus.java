package entity;

import javax.persistence.*;

@Entity
@Table(name = "TRANGTHAIPHIEU")
public class CouponStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MATRANGTHAI")
    private Integer cpStatusId;

    @Column(name = "TENTRANGTHAI")
    private String cpStatusName;

    public Integer getCpStatusId() {
        return cpStatusId;
    }

    public void setCpStatusId(Integer cpStatusId) {
        this.cpStatusId = cpStatusId;
    }

    public String getCpStatusName() {
        return cpStatusName;
    }

    public void setCpStatusName(String cpStatusName) {
        this.cpStatusName = cpStatusName;
    }

    public CouponStatus() {
    }

    public CouponStatus(Integer id) {
        this.cpStatusId = id;
    }

    public CouponStatus(Integer cpStatusId, String cpStatusName) {
        this.cpStatusId = cpStatusId;
        this.cpStatusName = cpStatusName;
    }

    @Override
    public String toString() {
        return "CouponStatus{" +
                "cpStatusId=" + cpStatusId +
                ", cpStatusName='" + cpStatusName + '\'' +
                '}';
    }
}
