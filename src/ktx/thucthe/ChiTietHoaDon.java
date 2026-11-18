package ktx.thucthe;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
public class ChiTietHoaDon {
    private int maCTHD;
    private int maHoaDon;
    private String tenLoaiPhi;
    private double soLuong;
    private BigDecimal donGia;
    private BigDecimal thanhTien;

    public ChiTietHoaDon() { }

    public ChiTietHoaDon(int maCTHD, int maHoaDon, String tenLoaiPhi, double soLuong, BigDecimal donGia, BigDecimal thanhTien) {
        this.maCTHD = maCTHD;
        this.maHoaDon = maHoaDon;
        this.tenLoaiPhi = tenLoaiPhi;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.thanhTien = thanhTien;
    }

    public int getMaCTHD() { return maCTHD; }
    public void setMaCTHD(int maCTHD) { this.maCTHD = maCTHD; }

    public int getMaHoaDon() { return maHoaDon; }
    public void setMaHoaDon(int maHoaDon) { this.maHoaDon = maHoaDon; }

    public String getTenLoaiPhi() { return tenLoaiPhi; }
    public void setTenLoaiPhi(String tenLoaiPhi) { this.tenLoaiPhi = tenLoaiPhi; }

    public double getSoLuong() { return soLuong; }
    public void setSoLuong(double soLuong) { this.soLuong = soLuong; }

    public BigDecimal getDonGia() { return donGia; }
    public void setDonGia(BigDecimal donGia) { this.donGia = donGia; }

    public BigDecimal getThanhTien() { return thanhTien; }
    public void setThanhTien(BigDecimal thanhTien) { this.thanhTien = thanhTien; }

    @Override
    public String toString() { return String.valueOf(maCTHD); }
}
