package model;

import entity.Supplier;

import java.util.Date;

public class SupplierModel {

    public Integer supplierId;
    public String address;
    public String phone;
    public String name;

    public SupplierModel(Integer supplierId, String address, String phone, String name) {
        this.supplierId = supplierId;
        this.address = address;
        this.phone = phone;
        this.name = name;
    }

    public SupplierModel(){
    }
    public SupplierModel(Supplier supplier){
        this.supplierId = supplier.getSupplierId();
        this.address = supplier.getAddress();
        this.phone = supplier.getPhone();
        this.name = supplier.getName();
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
