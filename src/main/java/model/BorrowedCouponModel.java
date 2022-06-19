package model;

import entity.BorrowedCoupon;
import entity.DetailBorrowedCoupon;
import entity.Supply;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BorrowedCouponModel extends CouponModel{
    private WorkerModel workerModel;
    private Date borrowedDate;
    private Date payedDate;
    private ConstructionModel constructionModel;
    private List<DetailBorrowedCouponModel> detailBorrowedCouponModelList;

    public BorrowedCouponModel(Integer couponId, EmployeeModel employeeModel, CouponStatusModel couponStatusModel, String note, WorkerModel workerModel, Date borrowedDate, Date payedDate, ConstructionModel constructionModel, List<DetailBorrowedCouponModel> detailBorrowedCouponModelList) {
        super(couponId, employeeModel, couponStatusModel, note);
        this.workerModel = workerModel;
        this.borrowedDate = borrowedDate;
        this.payedDate = payedDate;
        this.constructionModel = constructionModel;
        this.detailBorrowedCouponModelList = detailBorrowedCouponModelList;
    }

    public BorrowedCouponModel(WorkerModel workerModel, Date borrowedDate, Date payedDate, ConstructionModel constructionModel, List<DetailBorrowedCouponModel> detailBorrowedCouponModelList) {
        this.workerModel = workerModel;
        this.borrowedDate = borrowedDate;
        this.payedDate = payedDate;
        this.constructionModel = constructionModel;
        this.detailBorrowedCouponModelList = detailBorrowedCouponModelList;
    }

    public BorrowedCouponModel(BorrowedCoupon borrowedCoupon) {
        super.setCouponId(borrowedCoupon.getBrCpId());
        super.setEmployeeModel(new EmployeeModel(borrowedCoupon.getEmployee()));
        super.setCouponStatusModel(new CouponStatusModel(borrowedCoupon.getCpStatus()));
        super.setNote(borrowedCoupon.getNote());

        this.workerModel = new WorkerModel(borrowedCoupon.getWorker());
        this.borrowedDate = borrowedCoupon.getBorrowedDate();
        this.payedDate = borrowedCoupon.getPayedDate();
        this.constructionModel = new ConstructionModel(borrowedCoupon.getConstruction());
    }

    public BorrowedCouponModel() {
        this.payedDate = new Date();
        this.borrowedDate = new Date();
    }


    public WorkerModel getWorkerModel() {
        return workerModel;
    }

    public void setWorkerModel(WorkerModel workerModel) {
        this.workerModel = workerModel;
    }

    public Date getBorrowedDate() {
        return borrowedDate;
    }

    public void setBorrowedDate(Date borrowedDate) {
        this.borrowedDate = borrowedDate;
    }

    public Date getPayedDate() {
        return payedDate;
    }

    public void setPayedDate(Date payedDate) {
        this.payedDate = payedDate;
    }

    public ConstructionModel getConstructionModel() {
        return constructionModel;
    }

    public void setConstructionModel(ConstructionModel constructionModel) {
        this.constructionModel = constructionModel;
    }

    public List<DetailBorrowedCouponModel> getDetailBorrowedCouponModelList() {
        return detailBorrowedCouponModelList;
    }

    public void setDetailBorrowedCouponModelList(List<DetailBorrowedCouponModel> detailBorrowedCouponModelList) {
        this.detailBorrowedCouponModelList = detailBorrowedCouponModelList;
    }

    @Override
    public String toString() {
        return "BorrowedCouponModel{" +
                "workerModel=" + workerModel +
                ", borrowedDate=" + borrowedDate +
                ", payedDate=" + payedDate +
                ", constructionModel=" + constructionModel +
                ", detailBorrowedCouponModelList=" + detailBorrowedCouponModelList +
                '}';
    }
}
