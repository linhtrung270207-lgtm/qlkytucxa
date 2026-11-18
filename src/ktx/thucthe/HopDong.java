package ktx.thucthe;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
public class HopDong {
    private int maHD;
    private String maSV;
    private String maGiuong;
    private String maNVLap;
    private Timestamp ngayLap;
    private Date ngayBatDau;
    private Date ngayKetThuc;
    private BigDecimal tienCoc;
    private String trangThai;

    public HopDong() { }

    public HopDong(int maHD, String maSV, String maGiuong, String maNVLap, Timestamp ngayLap, Date ngayBatDau, Date ngayKetThuc, BigDecimal tienCoc, String trangThai) {
        this.maHD = maHD;
        this.maSV = maSV;
        this.maGiuong = maGiuong;
        this.maNVLap = maNVLap;
        this.ngayLap = ngayLap;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.tienCoc = tienCoc;
        this.trangThai = trangThai;
    }

    public int getMaHD() { return maHD; }
    public void setMaHD(int maHD) { this.maHD = maHD; }

    public String getMaSV() { return maSV; }
    public void setMaSV(String maSV) { this.maSV = maSV; }

    public String getMaGiuong() { return maGiuong; }
    public void setMaGiuong(String maGiuong) { this.maGiuong = maGiuong; }

    public String getMaNVLap() { return maNVLap; }
    public void setMaNVLap(String maNVLap) { this.maNVLap = maNVLap; }

    public Timestamp getNgayLap() { return ngayLap; }
    public void setNgayLap(Timestamp ngayLap) { this.ngayLap = ngayLap; }

    public Date getNgayBatDau() { return ngayBatDau; }
    public void setNgayBatDau(Date ngayBatDau) { this.ngayBatDau = ngayBatDau; }

    public Date getNgayKetThuc() { return ngayKetThuc; }
    public void setNgayKetThuc(Date ngayKetThuc) { this.ngayKetThuc = ngayKetThuc; }

    public BigDecimal getTienCoc() { return tienCoc; }
    public void setTienCoc(BigDecimal tienCoc) { this.tienCoc = tienCoc; }

    public String getTrangThai() { return trangThai; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }

    @Override
    public String toString() { return String.valueOf(maHD); }
}
