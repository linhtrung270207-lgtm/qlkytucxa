package ktx.thucthe;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
public class KhuNha {
    private String maKhu;
    private String tenKhu;
    private String loaiKhu;
    private String ghiChu;

    public KhuNha() { }

    public KhuNha(String maKhu, String tenKhu, String loaiKhu, String ghiChu) {
        this.maKhu = maKhu;
        this.tenKhu = tenKhu;
        this.loaiKhu = loaiKhu;
        this.ghiChu = ghiChu;
    }

    public String getMaKhu() { return maKhu; }
    public void setMaKhu(String maKhu) { this.maKhu = maKhu; }

    public String getTenKhu() { return tenKhu; }
    public void setTenKhu(String tenKhu) { this.tenKhu = tenKhu; }

    public String getLoaiKhu() { return loaiKhu; }
    public void setLoaiKhu(String loaiKhu) { this.loaiKhu = loaiKhu; }

    public String getGhiChu() { return ghiChu; }
    public void setGhiChu(String ghiChu) { this.ghiChu = ghiChu; }

    @Override
    public String toString() { return String.valueOf(maKhu); }
}
