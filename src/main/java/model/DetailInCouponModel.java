package model;

import java.math.BigDecimal;

public class DetailInCouponModel {
    private Integer supplyId;
    private Integer quantity;
    private Double price;
    private Integer inCouponId;

    public Integer getSupplyId() {
        return supplyId;
    }

    public void setSupplyId(Integer supplyId) {
        this.supplyId = supplyId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }


    public Integer getInCouponId() {
        return inCouponId;
    }

    public void setInCouponId(Integer inCouponId) {
        this.inCouponId = inCouponId;
    }

    public DetailInCouponModel() {
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public DetailInCouponModel(Integer supplyId, Integer quantity, Double price, Integer inCouponId) {
        this.supplyId = supplyId;
        this.quantity = quantity;
        this.price = price;
        this.inCouponId = inCouponId;
    }
}
