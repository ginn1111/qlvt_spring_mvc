package model;

import entity.Category;
import entity.Sector;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class CategoryModel {
    private Integer categoryId;
    private String name;
    private SectorModel sectorModel;

    public CategoryModel(Integer categoryId, String name, SectorModel sectorModel) {
        this.categoryId = categoryId;
        this.name = name;
        this.sectorModel = sectorModel;
    }
    public CategoryModel(Category category) {
        this.categoryId = category.getCategoryId();
        this.name = category.getName();
        this.sectorModel = new SectorModel(category.getSector());
    }
    public CategoryModel() {
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SectorModel getSectorModel() {
        return sectorModel;
    }

    public void setSectorModel(SectorModel sectorModel) {
        this.sectorModel = sectorModel;
    }
}
