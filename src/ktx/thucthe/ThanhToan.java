package ktx.thucthe;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
public class ThanhToan {
    private int maTT;
    private int maHoaDon;
    private String maSV;
    private String maNVThu;
    private Timestamp ngayThanhToan;
    private BigDecimal soTienNop;
    private String phuongThuc;
    private String ghiChu;

    public ThanhToan() { }

    public ThanhToan(int maTT, int maHoaDon, String maSV, String maNVThu, Timestamp ngayThanhToan, BigDecimal soTienNop, String phuongThuc, String ghiChu) {
        this.maTT = maTT;
        this.maHoaDon = maHoaDon;
        this.maSV = maSV;
        this.maNVThu = maNVThu;
        this.ngayThanhToan = ngayThanhToan;
        this.soTienNop = soTienNop;
        this.phuongThuc = phuongThuc;
        this.ghiChu = ghiChu;
    }

    public int getMaTT() { return maTT; }
    public void setMaTT(int maTT) { this.maTT = maTT; }

    public int getMaHoaDon() { return maHoaDon; }
    public void setMaHoaDon(int maHoaDon) { this.maHoaDon = maHoaDon; }

    public String getMaSV() { return maSV; }
    public void setMaSV(String maSV) { this.maSV = maSV; }

    public String getMaNVThu() { return maNVThu; }
    public void setMaNVThu(String maNVThu) { this.maNVThu = maNVThu; }

    public Timestamp getNgayThanhToan() { return ngayThanhToan; }
    public void setNgayThanhToan(Timestamp ngayThanhToan) { this.ngayThanhToan = ngayThanhToan; }

    public BigDecimal getSoTienNop() { return soTienNop; }
    public void setSoTienNop(BigDecimal soTienNop) { this.soTienNop = soTienNop; }

    public String getPhuongThuc() { return phuongThuc; }
    public void setPhuongThuc(String phuongThuc) { this.phuongThuc = phuongThuc; }

    public String getGhiChu() { return ghiChu; }
    public void setGhiChu(String ghiChu) { this.ghiChu = ghiChu; }

    @Override
    public String toString() { return String.valueOf(maTT); }
}
