package model;

import entity.DetailInCoupon;
import entity.DetailTransCoupon;

public class DetailTransCouponModel {

    private Integer id;
    private Integer quantity;
    private SupplyModel supplyModel;
    private TransCouponModel transCouponModel;

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public DetailTransCouponModel() {
    }


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

    public TransCouponModel getTransCouponModel() {
        return transCouponModel;
    }

    public void setTransCouponModel(TransCouponModel transCouponModel) {
        this.transCouponModel = transCouponModel;
    }

    public DetailTransCouponModel(Integer id, Integer quantity, Double price, SupplyModel supplyModel, TransCouponModel transCouponModel) {
        this.id = id;
        this.quantity = quantity;
        this.supplyModel = supplyModel;
        this.transCouponModel = transCouponModel;
    }

    public DetailTransCouponModel(DetailTransCoupon detailTransCoupon) {
        this.transCouponModel = new TransCouponModel(detailTransCoupon.getTransCoupon());
        this.quantity = detailTransCoupon.getQuantity();
        this.supplyModel = new SupplyModel(detailTransCoupon.getSupply());
    }

    @Override
    public String toString() {
        return "DetailInCouponModel{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", supplyModel=" + supplyModel +
                ", inCouponModel=" + transCouponModel +
                '}';
    }
}
