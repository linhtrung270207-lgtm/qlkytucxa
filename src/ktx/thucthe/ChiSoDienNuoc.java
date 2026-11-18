package ktx.thucthe;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

public class ChiSoDienNuoc {
    private int maCS;
    private String maPhong;
    private int thang;
    private int nam;
    private int soDienCu;
    private int soDienMoi;
    private int soNuocCu;
    private int soNuocMoi;
    private String maNVGhi;
    private Date ngayGhi;

    public ChiSoDienNuoc() { }

    public ChiSoDienNuoc(int maCS, String maPhong, int thang, int nam, int soDienCu, int soDienMoi, int soNuocCu, int soNuocMoi, String maNVGhi, Date ngayGhi) {
        this.maCS = maCS;
        this.maPhong = maPhong;
        this.thang = thang;
        this.nam = nam;
        this.soDienCu = soDienCu;
        this.soDienMoi = soDienMoi;
        this.soNuocCu = soNuocCu;
        this.soNuocMoi = soNuocMoi;
        this.maNVGhi = maNVGhi;
        this.ngayGhi = ngayGhi;
    }

    public int getMaCS() { return maCS; }
    public void setMaCS(int maCS) { this.maCS = maCS; }

    public String getMaPhong() { return maPhong; }
    public void setMaPhong(String maPhong) { this.maPhong = maPhong; }

    public int getThang() { return thang; }
    public void setThang(int thang) { this.thang = thang; }

    public int getNam() { return nam; }
    public void setNam(int nam) { this.nam = nam; }

    public int getSoDienCu() { return soDienCu; }
    public void setSoDienCu(int soDienCu) { this.soDienCu = soDienCu; }

    public int getSoDienMoi() { return soDienMoi; }
    public void setSoDienMoi(int soDienMoi) { this.soDienMoi = soDienMoi; }

    public int getSoNuocCu() { return soNuocCu; }
    public void setSoNuocCu(int soNuocCu) { this.soNuocCu = soNuocCu; }

    public int getSoNuocMoi() { return soNuocMoi; }
    public void setSoNuocMoi(int soNuocMoi) { this.soNuocMoi = soNuocMoi; }

    public String getMaNVGhi() { return maNVGhi; }
    public void setMaNVGhi(String maNVGhi) { this.maNVGhi = maNVGhi; }

    public Date getNgayGhi() { return ngayGhi; }
    public void setNgayGhi(Date ngayGhi) { this.ngayGhi = ngayGhi; }

    @Override
    public String toString() { return String.valueOf(maCS); }
}
