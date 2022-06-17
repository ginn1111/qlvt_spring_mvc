package model;


enum CouponStatus {
    UNFINISHED("Chưa hoàn thành"),
    FINISHED("Đã hoàn thành"),
    NOT_PAID_COMP("Chưa trả hết"),
    PAID_COMP("Đã trả hết"),
    WRONG("Phiếu sai"),
    CANCEL("Phiếu huỷ"),
    CONFIRM("Đang xác nhận");

    private final String value;

    CouponStatus(String value) {
        this.value = value;
    }

    public String getStatus() {
        return this.value;
    }
}

public class CouponModel {
    private Integer couponId;
    private EmployeeModel employeeModel;
    private String status;
}
