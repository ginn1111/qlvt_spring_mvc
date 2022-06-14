package entity;

import javax.persistence.*;

@Entity
public class Account {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "password")
    private String password;

    @Column(name = "enable")
    private Boolean enable;

    @Column(name = "id_role")
    private Integer idRole;

    public Account(String id, String password, Boolean enable, Integer idRole) {
        this.id = id;
        this.password = password;
        this.enable = enable;
        this.idRole = idRole;
    }

    public Account() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public Integer getIdRole() {
        return idRole;
    }

    public void setIdRole(Integer idRole) {
        this.idRole = idRole;
    }
}
