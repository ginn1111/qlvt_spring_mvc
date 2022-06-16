package model;

public class TaiKhoanModel {
    private String email;
    private String matkhau;
    private Integer maNV;
    private Integer maVaiTro;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public Integer getMaNV() {
        return maNV;
    }

    public void setMaNV(Integer maNV) {
        this.maNV = maNV;
    }

    public Integer getMaVaiTro() {
        return maVaiTro;
    }

    public void setMaVaiTro(Integer maVaiTro) {
        this.maVaiTro = maVaiTro;
    }

    public TaiKhoanModel() {
    }

    public TaiKhoanModel(String email, String matkhau, Integer maNV, Integer maVaiTro) {
        this.email = email;
        this.matkhau = matkhau;
        this.maNV = maNV;
        this.maVaiTro = maVaiTro;
    }
}
