package entity;


import javax.persistence.*;

@Entity
@Table(name = "CHITIETPHIEUXUAT")
public class DetailExCoupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDCTPX")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "MAVATTU")
    private Supply supply;

    @Column(name = "SOLUONG")
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "MAPHIEUXUAT")
    private ExCoupon exCoupon;

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


    public ExCoupon getExCoupon() {
        return exCoupon;
    }

    public void setExCoupon(ExCoupon exCoupon) {
        this.exCoupon = exCoupon;
    }

    public DetailExCoupon() {
    }

    public DetailExCoupon(Integer id) {
        this.id = id;
    }

    public DetailExCoupon(Integer id, Supply supply, Integer quantity, ExCoupon exCoupon) {
        this.id = id;
        this.supply = supply;
        this.quantity = quantity;
        this.exCoupon = exCoupon;
    }
    public DetailExCoupon(Supply supply, Integer quantity, ExCoupon exCoupon) {
        this.supply = supply;
        this.quantity = quantity;
        this.exCoupon = exCoupon;
    }
}
