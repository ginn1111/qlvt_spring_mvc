package entity;

import javax.persistence.*;

@Entity
@Table(name = "CHITIETPHIEUMUON")
public class DetailBorrowedCoupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDCTPM")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "MAVATTU")
    private Supply supply;

    @Column(name = "SOLUONG")
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "MAPHIEUMUON")
    private BorrowedCoupon borrowedCoupon;

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

    public BorrowedCoupon getBorrowedCoupon() {
        return borrowedCoupon;
    }

    public void setBorrowedCoupon(BorrowedCoupon borrowedCoupon) {
        this.borrowedCoupon = borrowedCoupon;
    }

    public DetailBorrowedCoupon() {
    }

    public DetailBorrowedCoupon(Integer id) {
        this.id = id;
    }

    public DetailBorrowedCoupon(Integer id, Supply supply, Integer quantity, BorrowedCoupon borrowedCoupon) {
        this.id = id;
        this.supply = supply;
        this.quantity = quantity;
        this.borrowedCoupon = borrowedCoupon;
    }
}
