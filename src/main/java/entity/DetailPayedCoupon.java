package entity;

import javax.persistence.*;

@Entity
@Table(name = "CHITIETPHIEUTRA")
public class DetailPayedCoupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDCTPT")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "MAVATTU")
    private Supply supply;

    @Column(name = "SOLUONG")
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "MAPHIEUTRA")
    private PayedCoupon payedCoupon;

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

    public PayedCoupon getPayedCoupon() {
        return payedCoupon;
    }

    public void setPayedCoupon(PayedCoupon payedCoupon) {
        this.payedCoupon = payedCoupon;
    }

    public DetailPayedCoupon() {
    }

    public DetailPayedCoupon(Integer id) {
        this.id = id;
    }

    public DetailPayedCoupon(Integer id, Supply supply, Integer quantity, PayedCoupon payedCoupon) {
        this.id = id;
        this.supply = supply;
        this.quantity = quantity;
        this.payedCoupon = payedCoupon;
    }
}
