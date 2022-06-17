package model;

import entity.Worker;

import java.util.Date;

public class WorkerModel {
    private Integer workerId;
    private String name;
    private String phone;
    private String address;
    private Date dob;

    public WorkerModel(Worker worker) {
        this.workerId = worker.getWorkerId();
        this.name = worker.getName();
        this.phone = worker.getPhone();
        this.address = worker.getAddress();
        this.dob = worker.getDob();
    }

    public Integer getWorkerId() {
        return workerId;
    }

    public void setWorkerId(Integer workerId) {
        this.workerId = workerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public WorkerModel() {
    }

    public WorkerModel(Integer workerId, String name, String phone, String address, Date dob) {
        this.workerId = workerId;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.dob = dob;
    }
}