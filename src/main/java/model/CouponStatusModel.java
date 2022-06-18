package model;

import entity.CouponStatus;

public class CouponStatusModel {
    private Integer id;
    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public CouponStatusModel() {
    }

    public CouponStatusModel(Integer id) {
        this.id = id;
    }
    public CouponStatusModel(Integer id, String status) {
        this.id = id;
        this.status = status;
    }

    public CouponStatusModel(CouponStatus couponStatus) {
        this.id = couponStatus.getCpStatusId();
        this.status = couponStatus.getCpStatusName();
    }

    @Override
    public String toString() {
        return "CouponStatusModel{" +
                "id=" + id +
                ", status='" + status + '\'' +
                '}';
    }
}
