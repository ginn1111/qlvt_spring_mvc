package model;

import entity.ExCoupon;

import java.util.Date;
import java.util.List;

public class ExCouponModel extends CouponModel{
    private WorkerModel workerModel;
    private ConstructionModel constructionModel;
    private Date date;
    private CouponStatusModel couponStatusModel;

    private List<DetailExCouponModel> detailExCouponModelList;

    public ExCouponModel(WorkerModel workerModel, ConstructionModel constructionModel, Date date) {
        this.workerModel = workerModel;
        this.constructionModel = constructionModel;
        this.date = date;
    }

    public ExCouponModel(WorkerModel workerModel, ConstructionModel constructionModel, Date date, List<DetailExCouponModel> detailExCouponModelList) {
        this.workerModel = workerModel;
        this.constructionModel = constructionModel;
        this.date = date;
        this.detailExCouponModelList = detailExCouponModelList;
    }

    public List<DetailExCouponModel> getDetailExCouponModelList() {
        return detailExCouponModelList;
    }

    public void setDetailExCouponModelList(List<DetailExCouponModel> detailExCouponModelList) {
        this.detailExCouponModelList = detailExCouponModelList;
    }

    public ExCouponModel() {
        super();
        this.date = new Date();
    }

    public ExCouponModel(ExCoupon exCoupon) {
        super(exCoupon.getExCpId(), new EmployeeModel(exCoupon.getEmployee()), new CouponStatusModel(exCoupon.getCpStatus()), exCoupon.getNote());
        this.date = exCoupon.getDate();
        this.workerModel = new WorkerModel(exCoupon.getWorker());
        this.constructionModel = new ConstructionModel(exCoupon.getConstruction());
        this.couponStatusModel = new CouponStatusModel(exCoupon.getCpStatus());
    }

    public ExCouponModel(Integer couponId, EmployeeModel employeeModel, CouponStatusModel couponStatusModel, String note, WorkerModel workerModel, ConstructionModel constructionModel,Date date, List<DetailExCouponModel> detailExCouponModelList) {
        super(couponId, employeeModel, couponStatusModel, note);
        this.workerModel = workerModel;
        this.constructionModel = constructionModel;
        this.date = date;
        this.detailExCouponModelList = detailExCouponModelList;
    }

    public WorkerModel getWorkerModel() {
        return workerModel;
    }

    public void setWorkerModel(WorkerModel workerModel) {
        this.workerModel = workerModel;
    }

    public ConstructionModel getConstructionModel() {
        return constructionModel;
    }

    public void setConstructionModel(ConstructionModel constructionModel) {
        this.constructionModel = constructionModel;
    }

    @Override
    public CouponStatusModel getCouponStatusModel() {
        return couponStatusModel;
    }

    @Override
    public void setCouponStatusModel(CouponStatusModel couponStatusModel) {
        this.couponStatusModel = couponStatusModel;
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
                "workerModel=" + workerModel +
                ", constructionModel=" + constructionModel +
                ", date=" + date +
                ", detailInCouponModelList=" + detailExCouponModelList +
                '}';
    }
}
