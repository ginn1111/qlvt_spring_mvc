package entity;

import javax.persistence.*;

@Entity
@Table(name = "NHACUNGCAP")
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "timNhaCungCap",
                query = "exec sp_TimNhaCungCap :key",
                resultClass = Supplier.class
        )
})
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MANHACUNGCAP")
    private int supplierId;

    @Column(name = "TENNHACUNGCAP")
    private String name;

    @Column(name = "SDT")
    private String phone;

    @Column(name = "DIACHI")
    private String address;

    @Column(name = "TRANGTHAI")
    private Boolean status;

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Supplier() {
    }

    public Supplier(int supplierId) {
        this.supplierId = supplierId;
    }
    public Supplier(int supplierId, String name, String phone, String address, Boolean status) {
        this.supplierId = supplierId;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "supplierId=" + supplierId +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", status=" + status +
                '}';
    }
}
