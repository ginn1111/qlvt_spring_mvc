package entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="KHUVUC")
@NamedNativeQueries(
        @NamedNativeQuery(
                name = "timKhuVuc",
                query="exec sp_TimKhuVuc :key",
                resultClass = Sector.class)
)
public class Sector {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="MAKHUVUC")
    private Integer sectorId;

    @Column(name="TENKHUVUC")
    private String name;

    @Column(name = "TRANGTHAI")
    private Boolean status;

    public Sector(Integer sectorId, String name, Boolean status) {
        this.sectorId = sectorId;
        this.name = name;
        this.status = status;

    }

    public Sector() {

    }
    public Sector(Integer sectorId) {
        this.sectorId = sectorId;
    }


    public Integer getSectorId() {
        return sectorId;
    }
    public void setSectorId(Integer sectorId) {
        this.sectorId = sectorId;
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
}
