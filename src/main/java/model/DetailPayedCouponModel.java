package model;

import entity.DetailPayedCoupon;

public class DetailPayedCouponModel {
    private Integer id;
    private SupplyModel supplyModel;
    private Integer quantity;
    private PayedCouponModel payedCouponModel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public SupplyModel getSupplyModel() {
        return supplyModel;
    }

    public void setSupplyModel(SupplyModel supplyModel) {
        this.supplyModel = supplyModel;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public PayedCouponModel getPayedCouponModel() {
        return payedCouponModel;
    }

    public void setPayedCouponModel(PayedCouponModel payedCouponModel) {
        this.payedCouponModel = payedCouponModel;
    }

    public DetailPayedCouponModel() {
    }

    public DetailPayedCouponModel(DetailPayedCoupon detailPayedCoupon) {
        this.id = detailPayedCoupon.getId();
        this.payedCouponModel = new PayedCouponModel(detailPayedCoupon.getPayedCoupon());
        this.supplyModel = new SupplyModel(detailPayedCoupon.getSupply());
        this.quantity = detailPayedCoupon.getQuantity();
    }
    public DetailPayedCouponModel(Integer id) {
        this.id = id;
    }

    public DetailPayedCouponModel(Integer id, SupplyModel supplyModel, Integer quantity, PayedCouponModel payedCouponModel) {
        this.id = id;
        this.supplyModel = supplyModel;
        this.quantity = quantity;
        this.payedCouponModel = payedCouponModel;
    }

    @Override
    public String toString() {
        return "DetailPayedCouponModel{" +
                "id=" + id +
                ", supplyModel=" + supplyModel +
                ", quantity=" + quantity +
                ", payedCouponModel=" + payedCouponModel +
                '}';
    }
}
