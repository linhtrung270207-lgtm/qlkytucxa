package ktx.thucthe;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
public class YeuCauSuaChua {
    private int maYC;
    private String maPhong;
    private String maSV;
    private String thietBiHong;
    private String moTaChiTiet;
    private Timestamp ngayBao;
    private String trangThai;
    private Timestamp ngayHoanThanh;
    private BigDecimal chiPhiSuaChua;
    private String ghiChu;

    public YeuCauSuaChua() { }

    public YeuCauSuaChua(int maYC, String maPhong, String maSV, String thietBiHong, String moTaChiTiet, Timestamp ngayBao, String trangThai, Timestamp ngayHoanThanh, BigDecimal chiPhiSuaChua, String ghiChu) {
        this.maYC = maYC;
        this.maPhong = maPhong;
        this.maSV = maSV;
        this.thietBiHong = thietBiHong;
        this.moTaChiTiet = moTaChiTiet;
        this.ngayBao = ngayBao;
        this.trangThai = trangThai;
        this.ngayHoanThanh = ngayHoanThanh;
        this.chiPhiSuaChua = chiPhiSuaChua;
        this.ghiChu = ghiChu;
    }

    public int getMaYC() { return maYC; }
    public void setMaYC(int maYC) { this.maYC = maYC; }

    public String getMaPhong() { return maPhong; }
    public void setMaPhong(String maPhong) { this.maPhong = maPhong; }

    public String getMaSV() { return maSV; }
    public void setMaSV(String maSV) { this.maSV = maSV; }

    public String getThietBiHong() { return thietBiHong; }
    public void setThietBiHong(String thietBiHong) { this.thietBiHong = thietBiHong; }

    public String getMoTaChiTiet() { return moTaChiTiet; }
    public void setMoTaChiTiet(String moTaChiTiet) { this.moTaChiTiet = moTaChiTiet; }

    public Timestamp getNgayBao() { return ngayBao; }
    public void setNgayBao(Timestamp ngayBao) { this.ngayBao = ngayBao; }

    public String getTrangThai() { return trangThai; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }

    public Timestamp getNgayHoanThanh() { return ngayHoanThanh; }
    public void setNgayHoanThanh(Timestamp ngayHoanThanh) { this.ngayHoanThanh = ngayHoanThanh; }

    public BigDecimal getChiPhiSuaChua() { return chiPhiSuaChua; }
    public void setChiPhiSuaChua(BigDecimal chiPhiSuaChua) { this.chiPhiSuaChua = chiPhiSuaChua; }

    public String getGhiChu() { return ghiChu; }
    public void setGhiChu(String ghiChu) { this.ghiChu = ghiChu; }

    @Override
    public String toString() { return String.valueOf(maYC); }
}
