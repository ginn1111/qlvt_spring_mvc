package model;

public enum RoleName {
    MANAGER("Quản lý"), EMPLOYEE("Nhân viên");

    private final String value;

    RoleName(String value) {
        this.value = value;
    }

    public String getRoleName() {
        return this.value;
    }
}
