package model;


public class CouponModel {
    private Integer couponId;
    private EmployeeModel employeeModel;
    private String status;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public CouponModel() {
    }

    public CouponModel(Integer couponId, EmployeeModel employeeModel, String status, String note) {
        this.couponId = couponId;
        this.employeeModel = employeeModel;
        this.status = status;
        this.note = note;
    }
}
