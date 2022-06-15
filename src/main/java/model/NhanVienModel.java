package model;

import entity.NhanVien;

import java.util.Date;

public class NhanVienModel {
    public Integer maNhanVien;
    public String diaChi;
    public String SDT;
    public Date ngaySinh;
    public String ten;

    public NhanVienModel(Integer maNhanVien, String diaChi, String SDT, Date ngaySinh, String ten) {
        this.maNhanVien = maNhanVien;
        this.diaChi = diaChi;
        this.SDT = SDT;
        this.ngaySinh = ngaySinh;
        this.ten = ten;
    }

    public NhanVienModel(NhanVien nhanVien) {
        this.maNhanVien = nhanVien.getMaNhanVien();
        this.diaChi = nhanVien.getDiaChi();
        this.SDT = nhanVien.getSDT();
        this.ngaySinh = nhanVien.getNgaySinh();
        this.ten = nhanVien.getTen();
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public NhanVienModel() {

    }

    public Integer getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(Integer maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }
}
