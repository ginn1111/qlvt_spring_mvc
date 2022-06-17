package entity;

import javax.persistence.*;

@Entity
@Table(name = "CONGTRINH")
public class Construction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MACONGTRINH")
    private Integer constructionId;

    @Column(name = "TENCONGTRINH")
    private String name;

    @Column(name = "TRANGTHAI")
    private Boolean status;

    public Integer getConstructionId() {
        return constructionId;
    }

    public void setConstructionId(Integer constructionId) {
        this.constructionId = constructionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Construction() {
    }
    public Construction(Integer constructionId) {
        this.constructionId = constructionId;
    }

    public Construction(Integer constructionId, String name, Boolean status) {
        this.constructionId = constructionId;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Construction{" +
                "constructionId=" + constructionId +
                ", name='" + name + '\'' +
                ", status=" + status +
                '}';
    }

}
