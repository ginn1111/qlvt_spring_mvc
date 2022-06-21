package model;

import entity.Sector;

import javax.persistence.Column;

public class SectorModel {
    private Integer sectorId;
    private String name;

    public SectorModel(Integer sectorId, String name) {
        this.sectorId = sectorId;
        this.name = name;
    }

    public SectorModel() {

    }
    public SectorModel(Sector sector) {
        this.sectorId = sector.getSectorId();
        this.name = sector.getName();
    }
    public Integer getSectorId() {
        return sectorId;
    }

    public void setSectorId(Integer sectorId) {
        this.sectorId = sectorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "SectorModel{" +
                "sectorId=" + sectorId +
                ", name='" + name + '\'' +
                '}';
    }
}
