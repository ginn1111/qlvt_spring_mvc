package entity;

import javax.persistence.*;

@Entity
@Table(name="DANHMUC")
//@NamedNativeQueries(
//        @NamedNativeQuery(name = "findEmployeeByName", query="exec sp_findEmployeeByName", resultClass = Employee.class)
//)
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="MADANHMUC")
    private Integer categoryId;

    @Column(name="TENDANHMUC")
    private String name;

    @ManyToOne
    @JoinColumn(name = "MAKHUVUC")
    private Sector sector;

    @Column(name = "TRANGTHAI")
    private Boolean status;



    public Category() {

    }
    public Category(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Category(Integer categoryId, String name, Sector sector, Boolean status) {
        this.categoryId = categoryId;
        this.name = name;
        this.sector = sector;
        this.status = status;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}

