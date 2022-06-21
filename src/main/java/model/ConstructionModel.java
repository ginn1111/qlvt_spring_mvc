package model;

import entity.Construction;

import javax.persistence.Column;

public class ConstructionModel {
    private Integer constructionId;
    private String name;

    public ConstructionModel(Integer constructionId, String name) {
        this.constructionId = constructionId;
        this.name = name;
    }

    public ConstructionModel() {

    }

    public ConstructionModel(Construction construction) {
        this.constructionId = construction.getConstructionId();
        this.name = construction.getName();
    }

    public Integer getConstructionId() {
        return constructionId;
    }

    public void setConstructionId(Integer constructionId) {
        this.constructionId = constructionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
