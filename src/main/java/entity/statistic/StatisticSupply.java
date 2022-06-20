package entity.statistic;

import javax.persistence.*;

@Entity
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "getTopVatTuXuatTrongThang",
                query = "exec sp_getTopVatTuXuatTrongThang :num, :m, :y",
                resultClass = StatisticSupply.class
        ),

        @NamedNativeQuery(
                name = "getTopVatTuNhapTrongThang",
                query = "exec sp_getTopVatTuNhapTrongThang :num, :m, :y",
                resultClass = StatisticSupply.class
        )
})
public class StatisticSupply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MAVATTU")
    private Integer supplyId;

    @Column(name = "SOLUONG")
    private  Integer quantity;

    public Integer getSupplyId() {
        return supplyId;
    }

    public void setSupplyId(Integer supplyId) {
        this.supplyId = supplyId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public StatisticSupply() {
    }

    public StatisticSupply(Integer supplyId) {
        this.supplyId = supplyId;
    }

    public StatisticSupply(Integer supplyId, Integer quantity) {
        this.supplyId = supplyId;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "StatisticSupply{" +
                "supplyId=" + supplyId +
                ", quantity=" + quantity +
                '}';
    }
}
