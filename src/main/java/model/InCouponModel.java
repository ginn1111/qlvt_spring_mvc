package model;

import entity.InCoupon;
import request_bean.DetailInCouponModelList;

import java.math.BigDecimal;
import java.util.Date;

public class InCouponModel extends CouponModel{
   private SupplierModel supplierModel;
   private Date date;
   private BigDecimal total;

   private DetailInCouponModelList detailInCouponModelList;

   public DetailInCouponModelList getDetailInCouponModelList() {
      return detailInCouponModelList;
   }

   public void setDetailInCouponModelList(DetailInCouponModelList detailInCouponModelList) {
      this.detailInCouponModelList = detailInCouponModelList;
   }

   public InCouponModel(Integer couponId, EmployeeModel employeeModel, String status, String note, SupplierModel supplierModel, Date date, BigDecimal total, DetailInCouponModelList detailInCouponModelList) {
      super(couponId, employeeModel, status, note);
      this.supplierModel = supplierModel;
      this.date = date;
      this.total = total;
      this.detailInCouponModelList = detailInCouponModelList;
   }

   public InCouponModel(SupplierModel supplierModel, Date date, BigDecimal total, DetailInCouponModelList detailInCouponModelList) {
      this.supplierModel = supplierModel;
      this.date = date;
      this.total = total;
      this.detailInCouponModelList = detailInCouponModelList;
   }

   public InCouponModel(SupplierModel supplierModel, Date date, BigDecimal total) {
      this.supplierModel = supplierModel;
      this.date = date;
      this.total = total;
   }

   public InCouponModel(Integer couponId, EmployeeModel employeeModel, String status, String note, SupplierModel supplierModel, Date date, BigDecimal total) {
      super(couponId, employeeModel, status, note);
      this.supplierModel = supplierModel;
      this.date = date;
      this.total = total;
   }

   public InCouponModel() {
      this.total = new BigDecimal("0");
      this.date = new Date();
   }

   public InCouponModel(InCoupon inCoupon) {
      super(inCoupon.getInCpId(), new EmployeeModel(inCoupon.getEmployee()),
              inCoupon.getCpStatus().getCpStatusName(), inCoupon.getNote());
      this.supplierModel = new SupplierModel(inCoupon.getSupplier());
      this.date = inCoupon.getDate();
      this.total = inCoupon.getTotal();
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
}
