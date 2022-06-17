package model;

import entity.Worker;

import java.util.Date;

public class WorkerModel {
    public Integer workerId;
    public String address;
    public String phone;
    public Date dob;
    public String name;

    public WorkerModel(Integer workerId, String address, String phone, Date dob, String name) {
        this.workerId = workerId;
        this.address = address;
        this.phone = phone;
        this.dob = dob;
        this.name = name;
    }

    public WorkerModel(Worker worker) {
        this.workerId = worker.getWorkerId();
        this.address = worker.getAddress();
        this.phone = worker.getPhone();
        this.dob = worker.getDob();
        this.name = worker.getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WorkerModel() {
        this.dob = new Date();
    }

    public Integer getWorkerId() {
        return workerId;
    }

    public void setWorkerId(Integer workerId) {
        this.workerId = workerId;
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
