package entity;

import javax.persistence.*;

@Entity
@Table(name = "CHITIETPHIEUCHUYENKHO")
public class DetailTransCoupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDCTPCK")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "MAVATTU")
    private Supply supply;

    @Column(name = "SOLUONG")
    private Integer quantity;


    @ManyToOne
    @JoinColumn(name = "MAPHIEUCHUYENKHO")
    private TransCoupon transCoupon;

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


    public TransCoupon getTransCoupon() {
        return transCoupon;
    }

    public void setTransCoupon(TransCoupon transCoupon) {
        this.transCoupon = transCoupon;
    }

    public DetailTransCoupon() {
    }

    public DetailTransCoupon(Integer id) {
        this.id = id;
    }

    public DetailTransCoupon(Integer id, Supply supply, Integer quantity, TransCoupon transCoupon) {
        this.id = id;
        this.supply = supply;
        this.quantity = quantity;
        this.transCoupon = transCoupon;
    }
    public DetailTransCoupon(Supply supply, Integer quantity, TransCoupon transCoupon) {
        this.supply = supply;
        this.quantity = quantity;
        this.transCoupon = transCoupon;
    }
}
