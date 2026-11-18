package ktx.thucthe;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
public class Giuong {
    private String maGiuong;
    private String maPhong;
    private int soThuTu;
    private String trangThai;

    public Giuong() { }

    public Giuong(String maGiuong, String maPhong, int soThuTu, String trangThai) {
        this.maGiuong = maGiuong;
        this.maPhong = maPhong;
        this.soThuTu = soThuTu;
        this.trangThai = trangThai;
    }

    public String getMaGiuong() { return maGiuong; }
    public void setMaGiuong(String maGiuong) { this.maGiuong = maGiuong; }

    public String getMaPhong() { return maPhong; }
    public void setMaPhong(String maPhong) { this.maPhong = maPhong; }

    public int getSoThuTu() { return soThuTu; }
    public void setSoThuTu(int soThuTu) { this.soThuTu = soThuTu; }

    public String getTrangThai() { return trangThai; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }

    @Override
    public String toString() { return String.valueOf(maGiuong); }
}
