package model;

import entity.DetailTransCoupon;
import entity.TransCoupon;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class TransCouponModel extends CouponModel {
    private WarehouseModel warehouseModel;
    private Date date;
    private CouponStatusModel couponStatusModel;

    private List<DetailTransCouponModel> detailTransCouponModelList;

    public TransCouponModel(WarehouseModel warehouseModel, Date date, BigDecimal total) {
        this.warehouseModel = warehouseModel;
        this.date = date;
    }
    public TransCouponModel(TransCoupon transCoupon) {
        super(transCoupon.getTrCpId(), new EmployeeModel(transCoupon.getEmployee()), new CouponStatusModel(transCoupon.getCpStatus()), transCoupon.getNote());
        this.date = transCoupon.getDate();
        this.warehouseModel = new WarehouseModel(transCoupon.getWarehouse());
        this.couponStatusModel = new CouponStatusModel(transCoupon.getCpStatus());
    }

    public TransCouponModel(WarehouseModel warehouseModel, Date date, List<DetailTransCouponModel> detailTransCouponModelList) {
        this.warehouseModel = warehouseModel;
        this.date = date;
        this.detailTransCouponModelList = detailTransCouponModelList;
    }

    public List<DetailTransCouponModel> getDetailTransCouponModelList() {
        return detailTransCouponModelList;
    }

    public void setDetailTransCouponModelList(List<DetailTransCouponModel> detailTransCouponModelList) {
        this.detailTransCouponModelList = detailTransCouponModelList;
    }

    public TransCouponModel() {
        super();
        this.date = new Date();
    }


    public TransCouponModel(Integer couponId, EmployeeModel employeeModel, CouponStatusModel couponStatusModel, String note, WarehouseModel warehouseModel, Date date, List<DetailTransCouponModel> detailTransCouponModelList) {
        super(couponId, employeeModel, couponStatusModel, note);
        this.warehouseModel = warehouseModel;
        this.date = date;
        this.detailTransCouponModelList = detailTransCouponModelList;
    }

    public WarehouseModel getWarehouseModel() {
        return warehouseModel;
    }

    public void setWarehouseModel(WarehouseModel warehouseModel) {
        this.warehouseModel = warehouseModel;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    @Override
    public String toString() {
        return "InCouponModel{" +
                super.toString() +
                "warehouseModel=" + warehouseModel +
                ", date=" + date +
                ", detailTransCouponModelList=" + detailTransCouponModelList +
                '}';
    }
}
