package model;

import entity.VaiTro;

enum TenVaiTro {
    QUANLY("Quản lý"), NHANVIEN("Nhân viên");

    private String value;

    TenVaiTro(String value) {
        this.value = value;
    }

    public String getTenVaiTro() {
        return this.value;
    }
}
public class VaiTroModel {
    private Integer maVaiTro;
    private TenVaiTro tenVaiTro;

   public VaiTroModel(VaiTro vaiTro)  {
       this.maVaiTro = vaiTro.getMaVaiTro();
       this.tenVaiTro = vaiTro.getTenVaiTro() == "QUANLY" ? TenVaiTro.QUANLY : TenVaiTro.NHANVIEN;
   }

    public Integer getMaVaiTro() {
        return maVaiTro;
    }

    public void setMaVaiTro(Integer maVaiTro) {
        this.maVaiTro = maVaiTro;
    }

    public TenVaiTro getTenVaiTro() {
        return tenVaiTro;
    }

    public void setTenVaiTro(TenVaiTro tenVaiTro) {
        this.tenVaiTro = tenVaiTro;
    }

    public VaiTroModel(Integer maVaiTro, TenVaiTro tenVaiTro) {
        this.maVaiTro = maVaiTro;
        this.tenVaiTro = tenVaiTro;
    }
}
