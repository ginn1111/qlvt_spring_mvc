package model;

import entity.Warehouse;

public class WarehouseModel {
    private Integer warehouseId;
    private String name;
    private String address;

    public Integer getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Integer warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public WarehouseModel() {
    }

    public WarehouseModel(Warehouse warehouse) {
        this.warehouseId = warehouse.getWarehouseId();
        this.name = warehouse.getName();
        this.address = warehouse.getAddress();
    }

    public WarehouseModel(Integer warehouseId, String name, String address) {
        this.warehouseId = warehouseId;
        this.name = name;
        this.address = address;
    }
}
