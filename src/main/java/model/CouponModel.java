package model;


public class CouponModel {
    private Integer couponId;
    private EmployeeModel employeeModel;
    private CouponStatusModel couponStatusModel;
    private String note;

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    public EmployeeModel getEmployeeModel() {
        return employeeModel;
    }

    public void setEmployeeModel(EmployeeModel employeeModel) {
        this.employeeModel = employeeModel;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public CouponModel() {
        this.couponStatusModel = new CouponStatusModel(7);
    }

    public CouponStatusModel getCouponStatusModel() {
        return couponStatusModel;
    }

    public void setCouponStatusModel(CouponStatusModel couponStatusModel) {
        this.couponStatusModel = couponStatusModel;
    }

    public CouponModel(Integer couponId, EmployeeModel employeeModel, CouponStatusModel couponStatusModel, String note) {
        this.couponId = couponId;
        this.employeeModel = employeeModel;
        this.couponStatusModel = couponStatusModel;
        this.note = note;
    }

    @Override
    public String toString() {
        return "CouponModel{" +
                "couponId=" + couponId +
                ", employeeModel=" + employeeModel +
                ", couponStatusModel=" + couponStatusModel +
                ", note='" + note + '\'' +
                '}';
    }
}
