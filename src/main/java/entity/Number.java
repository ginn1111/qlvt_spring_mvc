package entity;

import javax.persistence.*;

@Entity
public class Number {
    @Id
    @Column(name = "number")
    private Integer number;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Number() {
    }

    public Number(Integer number) {
        this.number = number;
    }
}

