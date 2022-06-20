package entity;

import javax.persistence.*;

@Entity
@Table(name = "KHO")
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "timKho",
                query = "exec sp_TimKho :key",
                resultClass = Warehouse.class
        )
})
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MAKHO")
    private Integer warehouseId;

    @Column(name = "TENKHO")
    private String name;

    @Column(name = "DIACHI")
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

    public Warehouse(Integer warehouseId, String name, String address) {
        this.warehouseId = warehouseId;
        this.name = name;
        this.address = address;
    }
    public Warehouse() {
    }
    public Warehouse(Integer warehouseId) {
        this.warehouseId = warehouseId;
    }
}
