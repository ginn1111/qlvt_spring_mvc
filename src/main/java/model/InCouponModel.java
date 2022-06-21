package model;

import entity.InCoupon;
import request_bean.DetailInCouponModelList;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class InCouponModel extends CouponModel{
   private SupplierModel supplierModel;
   private Date date;
   private BigDecimal total;
   private CouponStatusModel couponStatusModel;

   private List<DetailInCouponModel> detailInCouponModelList;

   public InCouponModel(SupplierModel supplierModel, Date date, BigDecimal total) {
      this.supplierModel = supplierModel;
      this.date = date;
      this.total = total;
   }

   public InCouponModel(SupplierModel supplierModel, Date date, BigDecimal total, List<DetailInCouponModel> detailInCouponModelList) {
      this.supplierModel = supplierModel;
      this.date = date;
      this.total = total;
      this.detailInCouponModelList = detailInCouponModelList;
   }

   public List<DetailInCouponModel> getDetailInCouponModelList() {
      return detailInCouponModelList;
   }

   public void setDetailInCouponModelList(List<DetailInCouponModel> detailInCouponModelList) {
      this.detailInCouponModelList = detailInCouponModelList;
   }

   public InCouponModel() {
      super();
      this.total = new BigDecimal("0");
      this.date = new Date();
   }

   public InCouponModel(InCoupon inCoupon) {
      super(inCoupon.getInCpId(), new EmployeeModel(inCoupon.getEmployee()), new CouponStatusModel(inCoupon.getCpStatus()), inCoupon.getNote());
      this.date = inCoupon.getDate();
      this.supplierModel = new SupplierModel(inCoupon.getSupplier());
      this.total = inCoupon.getTotal();
      this.couponStatusModel = new CouponStatusModel(inCoupon.getCpStatus());
   }

   public InCouponModel(Integer couponId, EmployeeModel employeeModel, CouponStatusModel couponStatusModel, String note, SupplierModel supplierModel, Date date, BigDecimal total, List<DetailInCouponModel> detailInCouponModelList) {
      super(couponId, employeeModel, couponStatusModel, note);
      this.supplierModel = supplierModel;
      this.date = date;
      this.total = total;
      this.detailInCouponModelList = detailInCouponModelList;
   }

   public SupplierModel getSupplierModel() {
      return supplierModel;
   }

   public void setSupplierModel(SupplierModel supplierModel) {
      this.supplierModel = supplierModel;
   }

   public Date getDate() {
      return date;
   }

   public void setDate(Date date) {
      this.date = date;
   }

   public BigDecimal getTotal() {
      return total;
   }

   public void setTotal(BigDecimal total) {
      this.total = total;
   }

   @Override
   public String toString() {
      return "InCouponModel{" +
              super.toString() +
              "supplierModel=" + supplierModel +
              ", date=" + date +
              ", total=" + total +
              ", detailInCouponModelList=" + detailInCouponModelList +
              '}';
   }
}
