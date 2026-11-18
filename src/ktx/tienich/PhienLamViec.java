package ktx.tienich;

import ktx.thucthe.NhanVien;

public final class PhienLamViec {
    private static NhanVien nhanVienHienTai;

    private PhienLamViec() { }

    public static NhanVien getNhanVienHienTai() { return nhanVienHienTai; }
    public static void setNhanVienHienTai(NhanVien nv) { nhanVienHienTai = nv; }
    public static String maNhanVien() { return nhanVienHienTai == null ? null : nhanVienHienTai.getMaNV(); }
}
