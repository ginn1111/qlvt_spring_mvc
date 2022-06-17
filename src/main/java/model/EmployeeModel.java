package model;

import entity.Employee;

import java.util.Date;

public class EmployeeModel {
    public Integer employeeId;
    public String address;
    public String phone;
    public Date dob;
    public String name;

    public EmployeeModel(Integer employeeId, String address, String phone, Date dob, String name) {
        this.employeeId = employeeId;
        this.address = address;
        this.phone = phone;
        this.dob = dob;
        this.name = name;
    }

    public EmployeeModel(Employee employee) {
        this.employeeId = employee.getEmployeeId();
        this.address = employee.getAddress();
        this.phone = employee.getPhone();
        this.dob = employee.getDob();
        this.name = employee.getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EmployeeModel() {
        this.dob = new Date();
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }
}
