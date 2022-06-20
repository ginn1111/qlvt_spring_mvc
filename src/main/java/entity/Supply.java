package entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="VATTU")
@NamedNativeQueries(
    {
        @NamedNativeQuery(
            name="timVatTu", query = "exec sp_TimVatTu :key", resultClass = Supply.class
        )
    }
)
public class Supply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="MAVATTU")
    private Integer supplyId;

    @Column(name="TENVATTU")
    private String name;

    @Column(name = "HINHANH")
    private String image;

    @Column(name = "DONVI")
    private String unit;

    @ManyToOne
    @JoinColumn(name = "MADANHMUC")
    private Category category;

    @Column(name = "NHASANXUAT")
    private String producer;

    @Column(name = "SOLUONG")
    private Integer quantity;

    @Column(name = "TRANGTHAI")
    private Boolean status;

    public Supply() {

    }
    public Supply(Integer supplyId) {
        this.supplyId = supplyId;
    }

    public Supply(Integer supplyId, String name, String image, String unit, Category category, String producer, int quantity, Boolean status) {
        this.supplyId = supplyId;
        this.name = name;
        this.image = image;
        this.unit = unit;
        this.category = category;
        this.producer = producer;
        this.quantity = quantity;
        this.status = status;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
