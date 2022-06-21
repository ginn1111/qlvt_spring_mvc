package model;

import entity.DetailBorrowedCoupon;

public class DetailBorrowedCouponModel {
    private Integer id;
    private SupplyModel supplyModel;
    private Integer quantity;
    private BorrowedCouponModel borrowedCouponModel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BorrowedCouponModel getBorrowedCouponModel() {
        return borrowedCouponModel;
    }

    public void setBorrowedCouponModel(BorrowedCouponModel borrowedCouponModel) {
        this.borrowedCouponModel = borrowedCouponModel;
    }

    public DetailBorrowedCouponModel(Integer id) {
        this.id = id;
    }

    public DetailBorrowedCouponModel(DetailBorrowedCoupon detailBorrowedCoupon) {
        this.id = detailBorrowedCoupon.getId();
        this.supplyModel = new SupplyModel(detailBorrowedCoupon.getSupply());
        this.quantity = detailBorrowedCoupon.getQuantity();
        this.borrowedCouponModel = new BorrowedCouponModel(detailBorrowedCoupon.getBorrowedCoupon());
    }
    public DetailBorrowedCouponModel() {
    }
    public SupplyModel getSupplyModel() {
        return supplyModel;
    }

    public void setSupplyModel(SupplyModel supplyModel) {
        this.supplyModel = supplyModel;
    }

    public DetailBorrowedCouponModel(Integer id, SupplyModel supplyModel, Integer quantity, BorrowedCouponModel borrowedCouponModel) {
        this.id = id;
        this.supplyModel = supplyModel;
        this.quantity = quantity;
        this.borrowedCouponModel = borrowedCouponModel;
    }

    @Override
    public String toString() {
        return "DetailBorrowedCouponModel{" +
                "id=" + id +
                ", supplyModel=" + supplyModel +
                ", quantity=" + quantity +
                ", borrowedCouponModel=" + borrowedCouponModel +
                '}';
    }
}
