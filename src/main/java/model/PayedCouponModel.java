package model;

import entity.PayedCoupon;

import java.util.Date;
import java.util.List;

public class PayedCouponModel extends CouponModel {
    private WorkerModel workerModel;
    private BorrowedCouponModel borrowedCouponModel;
    private Date payedDate;

    public List<DetailPayedCouponModel> getDetailPayedCouponModelList() {
        return detailPayedCouponModelList;
    }

    public void setDetailPayedCouponModelList(List<DetailPayedCouponModel> detailPayedCouponModelList) {
        this.detailPayedCouponModelList = detailPayedCouponModelList;
    }

    private List<DetailPayedCouponModel> detailPayedCouponModelList;

    public PayedCouponModel() {
        super();
        this.payedDate = new Date();
    }

    public WorkerModel getWorkerModel() {
        return workerModel;
    }

    public void setWorkerModel(WorkerModel workerModel) {
        this.workerModel = workerModel;
    }

    public BorrowedCouponModel getBorrowedCouponModel() {
        return borrowedCouponModel;
    }

    public void setBorrowedCouponModel(BorrowedCouponModel borrowedCouponModel) {
        this.borrowedCouponModel = borrowedCouponModel;
    }

    public Date getPayedDate() {
        return payedDate;
    }

    public void setPayedDate(Date payedDate) {
        this.payedDate = payedDate;
    }

    public PayedCouponModel(PayedCoupon payedCoupon) {
        super(payedCoupon.getPyCpId(), new EmployeeModel(payedCoupon.getEmployee()), new CouponStatusModel(payedCoupon.getCpStatus()), payedCoupon.getNote());
        this.payedDate = payedCoupon.getPayedDate();
        this.borrowedCouponModel = new BorrowedCouponModel(payedCoupon.getBorrowedCoupon());
        this.workerModel = new WorkerModel(payedCoupon.getWorker());
    }

    public PayedCouponModel(Integer couponId, EmployeeModel employeeModel, CouponStatusModel couponStatusModel, String note, WorkerModel workerModel, BorrowedCouponModel borrowedCouponModel, Date payedDate) {
        super(couponId, employeeModel, couponStatusModel, note);
        this.workerModel = workerModel;
        this.borrowedCouponModel = borrowedCouponModel;
        this.payedDate = payedDate;
    }

    public PayedCouponModel(WorkerModel workerModel, BorrowedCouponModel borrowedCouponModel, Date payedDate) {
        this.workerModel = workerModel;
        this.borrowedCouponModel = borrowedCouponModel;
        this.payedDate = payedDate;
    }
}
