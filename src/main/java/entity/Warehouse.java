package entity;

import javax.persistence.*;

@Entity
@Table(name = "KHO")
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MAKHO")
    private Integer warehouseId;

    @Column(name = "TENKHO")
    private String name;

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

    public Warehouse() {
    }

    public Warehouse(Integer warehouseId, String name) {
        this.warehouseId = warehouseId;
        this.name = name;
    }
}
