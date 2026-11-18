package ktx.thucthe;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
public class SinhVien {
    private String maSV;
    private String hoTen;
    private Date ngaySinh;
    private String gioiTinh;
    private String cccd;
    private String soDienThoai;
    private String email;
    private String truongHoc;
    private String queQuan;
    private String hoTenNguoiThan;
    private String sdtNguoiThan;
    private String trangThai;

    public SinhVien() { }

    public SinhVien(String maSV, String hoTen, Date ngaySinh, String gioiTinh, String cccd, String soDienThoai, String email, String truongHoc, String queQuan, String hoTenNguoiThan, String sdtNguoiThan, String trangThai) {
        this.maSV = maSV;
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.cccd = cccd;
        this.soDienThoai = soDienThoai;
        this.email = email;
        this.truongHoc = truongHoc;
        this.queQuan = queQuan;
        this.hoTenNguoiThan = hoTenNguoiThan;
        this.sdtNguoiThan = sdtNguoiThan;
        this.trangThai = trangThai;
    }

    public String getMaSV() { return maSV; }
    public void setMaSV(String maSV) { this.maSV = maSV; }

    public String getHoTen() { return hoTen; }
    public void setHoTen(String hoTen) { this.hoTen = hoTen; }

    public Date getNgaySinh() { return ngaySinh; }
    public void setNgaySinh(Date ngaySinh) { this.ngaySinh = ngaySinh; }

    public String getGioiTinh() { return gioiTinh; }
    public void setGioiTinh(String gioiTinh) { this.gioiTinh = gioiTinh; }

    public String getCCCD() { return cccd; }
    public void setCCCD(String cccd) { this.cccd = cccd; }

    public String getSoDienThoai() { return soDienThoai; }
    public void setSoDienThoai(String soDienThoai) { this.soDienThoai = soDienThoai; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTruongHoc() { return truongHoc; }
    public void setTruongHoc(String truongHoc) { this.truongHoc = truongHoc; }

    public String getQueQuan() { return queQuan; }
    public void setQueQuan(String queQuan) { this.queQuan = queQuan; }

    public String getHoTenNguoiThan() { return hoTenNguoiThan; }
    public void setHoTenNguoiThan(String hoTenNguoiThan) { this.hoTenNguoiThan = hoTenNguoiThan; }

    public String getSDTNguoiThan() { return sdtNguoiThan; }
    public void setSDTNguoiThan(String sdtNguoiThan) { this.sdtNguoiThan = sdtNguoiThan; }

    public String getTrangThai() { return trangThai; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }

    @Override
    public String toString() { return String.valueOf(maSV); }
}
