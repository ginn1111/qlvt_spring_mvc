package model;

import entity.Account;

public class AccountModel {
    private String email;
    private String password;
    private EmployeeModel employeeModel;
    private Integer roleId;
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AccountModel() {
    }

    public AccountModel(Account account) {
       this.email = account.getEmail();
       this.password = account.getPassword();
       this.employeeModel = new EmployeeModel(account.getEmployee());
       this.roleId = account.getRole().getRoleId();
    }

    public EmployeeModel getEmployeeModel() {
        return employeeModel;
    }

    public void setEmployeeModel(EmployeeModel employeeModel) {
        this.employeeModel = employeeModel;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public AccountModel(String email, String password, EmployeeModel employeeModel, Integer roleId) {
        this.email = email;
        this.password = password;
        this.employeeModel = employeeModel;
        this.roleId = roleId;
    }
}
