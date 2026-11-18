package ktx.thucthe;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
public class LoaiPhong {
    private String maLoai;
    private String tenLoai;
    private Double dienTich;
    private int soGiuong;
    private BigDecimal giaPhong;
    private String moTa;

    public LoaiPhong() { }

    public LoaiPhong(String maLoai, String tenLoai, Double dienTich, int soGiuong, BigDecimal giaPhong, String moTa) {
        this.maLoai = maLoai;
        this.tenLoai = tenLoai;
        this.dienTich = dienTich;
        this.soGiuong = soGiuong;
        this.giaPhong = giaPhong;
        this.moTa = moTa;
    }

    public String getMaLoai() { return maLoai; }
    public void setMaLoai(String maLoai) { this.maLoai = maLoai; }

    public String getTenLoai() { return tenLoai; }
    public void setTenLoai(String tenLoai) { this.tenLoai = tenLoai; }

    public Double getDienTich() { return dienTich; }
    public void setDienTich(Double dienTich) { this.dienTich = dienTich; }

    public int getSoGiuong() { return soGiuong; }
    public void setSoGiuong(int soGiuong) { this.soGiuong = soGiuong; }

    public BigDecimal getGiaPhong() { return giaPhong; }
    public void setGiaPhong(BigDecimal giaPhong) { this.giaPhong = giaPhong; }

    public String getMoTa() { return moTa; }
    public void setMoTa(String moTa) { this.moTa = moTa; }

    @Override
    public String toString() { return String.valueOf(maLoai); }
}
