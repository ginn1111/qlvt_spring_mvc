package entity;

import javax.persistence.*;

@Entity
@Table(name = "CHITIETPHIEUNHAP")
public class DetailInCoupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDCTPN")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "MAVATTU")
    private Supply supply;

    @Column(name = "SOLUONG")
    private Integer quantity;

    @Column(name = "GIATHANH")
    private Double price;

    @ManyToOne
    @JoinColumn(name = "MAPHIEUNHAP")
    private InCoupon inCoupon;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Supply getSupply() {
        return supply;
    }

    public void setSupply(Supply supply) {
        this.supply = supply;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public InCoupon getInCoupon() {
        return inCoupon;
    }

    public void setInCoupon(InCoupon inCoupon) {
        this.inCoupon = inCoupon;
    }

    public DetailInCoupon() {
    }

    public DetailInCoupon(Integer id) {
        this.id = id;
    }

    public DetailInCoupon(Integer id, Supply supply, Integer quantity, Double price, InCoupon inCoupon) {
        this.id = id;
        this.supply = supply;
        this.quantity = quantity;
        this.price = price;
        this.inCoupon = inCoupon;
    }
    public DetailInCoupon(Supply supply, Integer quantity, Double price, InCoupon inCoupon) {
        this.supply = supply;
        this.quantity = quantity;
        this.price = price;
        this.inCoupon = inCoupon;
    }
}
