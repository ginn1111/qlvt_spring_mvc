package model;

import entity.Employee;

import java.util.Date;

public class InformationModel extends EmployeeModel {
    private String oldPassword;
    private String newPassword;

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public InformationModel(String oldPassword, String newPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    public InformationModel(Employee employee, String oldPassword, String newPassword) {
        super(employee);
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    public InformationModel(Integer employeeId, String address, String phone, Date dob, String name, String oldPassword, String newPassword) {
        super(employeeId, address, phone, dob, name);
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }
}
