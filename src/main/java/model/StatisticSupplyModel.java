package model;

public class StatisticSupplyModel {
    private String supplyName;
    private Integer quantity;

    public String getSupplyName() {
        return supplyName;
    }

    public void setSupplyName(String supplyName) {
        this.supplyName = supplyName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public StatisticSupplyModel() {
    }

    public StatisticSupplyModel(String supplyName, Integer quantity) {
        this.supplyName = supplyName;
        this.quantity = quantity;
    }
}
