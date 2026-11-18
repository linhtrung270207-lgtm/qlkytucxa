package ktx.thucthe;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
public class KyLuat {
    private int maKL;
    private String maSV;
    private String maNVLap;
    private String loai;
    private String hinhThuc;
    private String noiDung;
    private Timestamp ngayQuyetDinh;
    private BigDecimal tienPhat;

    public KyLuat() { }

    public KyLuat(int maKL, String maSV, String maNVLap, String loai, String hinhThuc, String noiDung, Timestamp ngayQuyetDinh, BigDecimal tienPhat) {
        this.maKL = maKL;
        this.maSV = maSV;
        this.maNVLap = maNVLap;
        this.loai = loai;
        this.hinhThuc = hinhThuc;
        this.noiDung = noiDung;
        this.ngayQuyetDinh = ngayQuyetDinh;
        this.tienPhat = tienPhat;
    }

    public int getMaKL() { return maKL; }
    public void setMaKL(int maKL) { this.maKL = maKL; }

    public String getMaSV() { return maSV; }
    public void setMaSV(String maSV) { this.maSV = maSV; }

    public String getMaNVLap() { return maNVLap; }
    public void setMaNVLap(String maNVLap) { this.maNVLap = maNVLap; }

    public String getLoai() { return loai; }
    public void setLoai(String loai) { this.loai = loai; }

    public String getHinhThuc() { return hinhThuc; }
    public void setHinhThuc(String hinhThuc) { this.hinhThuc = hinhThuc; }

    public String getNoiDung() { return noiDung; }
    public void setNoiDung(String noiDung) { this.noiDung = noiDung; }

    public Timestamp getNgayQuyetDinh() { return ngayQuyetDinh; }
    public void setNgayQuyetDinh(Timestamp ngayQuyetDinh) { this.ngayQuyetDinh = ngayQuyetDinh; }

    public BigDecimal getTienPhat() { return tienPhat; }
    public void setTienPhat(BigDecimal tienPhat) { this.tienPhat = tienPhat; }

    @Override
    public String toString() { return String.valueOf(maKL); }
}
