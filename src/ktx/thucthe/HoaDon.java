package ktx.thucthe;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
public class HoaDon {
    private int maHD;
    private String maPhong;
    private String maNVLap;
    private int thang;
    private int nam;
    private Timestamp ngayLap;
    private Date hanThanhToan;
    private BigDecimal tongTien;
    private String trangThai;
    private String ghiChu;

    public HoaDon() { }

    public HoaDon(int maHD, String maPhong, String maNVLap, int thang, int nam, Timestamp ngayLap, Date hanThanhToan, BigDecimal tongTien, String trangThai, String ghiChu) {
        this.maHD = maHD;
        this.maPhong = maPhong;
        this.maNVLap = maNVLap;
        this.thang = thang;
        this.nam = nam;
        this.ngayLap = ngayLap;
        this.hanThanhToan = hanThanhToan;
        this.tongTien = tongTien;
        this.trangThai = trangThai;
        this.ghiChu = ghiChu;
    }

    public int getMaHD() { return maHD; }
    public void setMaHD(int maHD) { this.maHD = maHD; }

    public String getMaPhong() { return maPhong; }
    public void setMaPhong(String maPhong) { this.maPhong = maPhong; }

    public String getMaNVLap() { return maNVLap; }
    public void setMaNVLap(String maNVLap) { this.maNVLap = maNVLap; }

    public int getThang() { return thang; }
    public void setThang(int thang) { this.thang = thang; }

    public int getNam() { return nam; }
    public void setNam(int nam) { this.nam = nam; }

    public Timestamp getNgayLap() { return ngayLap; }
    public void setNgayLap(Timestamp ngayLap) { this.ngayLap = ngayLap; }

    public Date getHanThanhToan() { return hanThanhToan; }
    public void setHanThanhToan(Date hanThanhToan) { this.hanThanhToan = hanThanhToan; }

    public BigDecimal getTongTien() { return tongTien; }
    public void setTongTien(BigDecimal tongTien) { this.tongTien = tongTien; }

    public String getTrangThai() { return trangThai; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }

    public String getGhiChu() { return ghiChu; }
    public void setGhiChu(String ghiChu) { this.ghiChu = ghiChu; }

    @Override
    public String toString() { return String.valueOf(maHD); }
}
