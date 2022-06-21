package model;

import entity.DetailInCoupon;
import entity.Supply;

import javax.xml.soap.Detail;
import java.math.BigDecimal;

public class DetailInCouponModel {
    private Integer id;
    private Integer quantity;
    private Double price;
    private SupplyModel supplyModel;
    private InCouponModel inCouponModel;

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public DetailInCouponModel() {
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
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

    public InCouponModel getInCouponModel() {
        return inCouponModel;
    }

    public void setInCouponModel(InCouponModel inCouponModel) {
        this.inCouponModel = inCouponModel;
    }

    public DetailInCouponModel(Integer id, Integer quantity, Double price, SupplyModel supplyModel, InCouponModel inCouponModel) {
        this.id = id;
        this.quantity = quantity;
        this.price = price;
        this.supplyModel = supplyModel;
        this.inCouponModel = inCouponModel;
    }

    public DetailInCouponModel(DetailInCoupon detailInCoupon) {
        this.inCouponModel = new InCouponModel(detailInCoupon.getInCoupon());
        this.price = detailInCoupon.getPrice();
        this.quantity = detailInCoupon.getQuantity();
        this.supplyModel = new SupplyModel(detailInCoupon.getSupply());
    }

    @Override
    public String toString() {
        return "DetailInCouponModel{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", price=" + price +
                ", supplyModel=" + supplyModel +
                ", inCouponModel=" + inCouponModel +
                '}';
    }
}
