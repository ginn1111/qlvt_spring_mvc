package model;

import entity.DetailExCoupon;
import entity.DetailInCoupon;

public class DetailExCouponModel {
    private Integer id;
    private Integer quantity;
    private SupplyModel supplyModel;
    private ExCouponModel exCouponModel;

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public DetailExCouponModel() {
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

    public ExCouponModel getExCouponModel() {
        return exCouponModel;
    }

    public void setExCouponModel(ExCouponModel exCouponModel) {
        this.exCouponModel = exCouponModel;
    }

    public DetailExCouponModel(Integer id, Integer quantity, Double price, SupplyModel supplyModel, ExCouponModel exCouponModel) {
        this.id = id;
        this.quantity = quantity;
        this.supplyModel = supplyModel;
        this.exCouponModel = exCouponModel;
    }

    public DetailExCouponModel(DetailExCoupon detailExCoupon) {
        this.exCouponModel = new ExCouponModel(detailExCoupon.getExCoupon());
        this.quantity = detailExCoupon.getQuantity();
        this.supplyModel = new SupplyModel(detailExCoupon.getSupply());
    }

    @Override
    public String toString() {
        return "DetailInCouponModel{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", supplyModel=" + supplyModel +
                ", exCouponModel=" + exCouponModel +
                '}';
    }
}
