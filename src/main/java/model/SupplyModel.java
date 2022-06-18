package model;

import entity.Category;
import entity.Supply;

import javax.persistence.*;

public class SupplyModel {

    private Integer supplyId;
    private String name;
    private String image;
    private String unit;
    private CategoryModel categoryModel;
    private String producer;
    private int quantity;

    public SupplyModel(Integer supplyId, String name, String image, String unit, CategoryModel categoryModel, String producer, int quantity) {
        this.supplyId = supplyId;
        this.name = name;
        this.image = image;
        this.unit = unit;
        this.categoryModel = categoryModel;
        this.producer = producer;
        this.quantity = quantity;
    }

    public SupplyModel() {
        this.quantity = 0;
    }

    public SupplyModel(Supply supply) {
        this.supplyId = supply.getSupplyId();
        this.name = supply.getName();
        this.image = supply.getImage();
        this.unit = supply.getUnit();
        this.categoryModel = new CategoryModel(supply.getCategory());
        this.producer = supply.getProducer();
        this.quantity = supply.getQuantity();
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public CategoryModel getCategoryModel() {
        return categoryModel;
    }

    public void setCategoryModel(CategoryModel categoryModel) {
        this.categoryModel = categoryModel;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public SupplyModel() {
    }

    @Override
    public String toString() {
        return "SupplyModel{" +
                "supplyId=" + supplyId +
                ", name='" + name + '\'' +
                ", img='" + img + '\'' +
                ", category='" + category + '\'' +
                ", producer='" + producer + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
