package model;

import entity.Category;
import entity.Supply;

public class SupplyModel {
    private Integer supplyId;
    private String name;
    private String img;
    private String category;
    private String producer;
    private Integer quantity;

    public SupplyModel(Integer supplyId, String name, String img, String category, String producer, Integer quantity) {
        this.supplyId = supplyId;
        this.name = name;
        this.img = img;
        this.category = category;
        this.producer = producer;
        this.quantity = quantity;
    }

    public SupplyModel(Supply supply) {
        this.supplyId = supply.getSupplyId();
        this.name = supply.getName();
        this.img = supply.getImage();
        this.category = supply.getCategory().getName();
        this.quantity = supply.getQuantity();
        this.producer = supply.getProducer();
    }

    public Integer getSupplyId() {
        return supplyId;
    }

    public void setSupplyId(Integer supplyId) {
        this.supplyId = supplyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public SupplyModel() {
    }
}
