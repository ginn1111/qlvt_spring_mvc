package entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="NHANVIEN")
public class NhanVien {
    @Id
    @Column(name="MANHANVIEN")
    private Integer maNhanVien;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name="NGAYSINH")
    private Date ngaySinh;
    @Column(name="TEN")
    private String ten;

    @Column(name = "SDT")
    private String SDT;

    @Column(name = "DIACHI")
    private String diaChi;

    @Column(name = "TRANGTHAI")
    private Boolean trangThai;

    public NhanVien(Integer maNhanVien, String ten, String SDT, String diaChi, Boolean trangThai, Date ngaySinh) {
        this.maNhanVien = maNhanVien;
        this.ten = ten;
        this.SDT = SDT;
        this.diaChi = diaChi;
        this.trangThai = trangThai;
    }

    public NhanVien() {

    }

    public Date getNgaySinh() {
        return this.ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public Integer getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(Integer maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public Boolean getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Boolean trangThai) {
        this.trangThai = trangThai;
    }
}
