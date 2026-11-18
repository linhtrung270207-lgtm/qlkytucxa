package ktx.thucthe;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
public class Phong {
    private String maPhong;
    private String maKhu;
    private String maLoai;
    private String tenPhong;
    private int soNguoiHienTai;
    private String trangThai;

    public Phong() { }

    public Phong(String maPhong, String maKhu, String maLoai, String tenPhong, int soNguoiHienTai, String trangThai) {
        this.maPhong = maPhong;
        this.maKhu = maKhu;
        this.maLoai = maLoai;
        this.tenPhong = tenPhong;
        this.soNguoiHienTai = soNguoiHienTai;
        this.trangThai = trangThai;
    }

    public String getMaPhong() { return maPhong; }
    public void setMaPhong(String maPhong) { this.maPhong = maPhong; }

    public String getMaKhu() { return maKhu; }
    public void setMaKhu(String maKhu) { this.maKhu = maKhu; }

    public String getMaLoai() { return maLoai; }
    public void setMaLoai(String maLoai) { this.maLoai = maLoai; }

    public String getTenPhong() { return tenPhong; }
    public void setTenPhong(String tenPhong) { this.tenPhong = tenPhong; }

    public int getSoNguoiHienTai() { return soNguoiHienTai; }
    public void setSoNguoiHienTai(int soNguoiHienTai) { this.soNguoiHienTai = soNguoiHienTai; }

    public String getTrangThai() { return trangThai; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }

    @Override
    public String toString() { return String.valueOf(maPhong); }
}
