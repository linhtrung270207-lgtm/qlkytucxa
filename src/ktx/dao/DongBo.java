package ktx.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public final class DongBo {

    private DongBo() { }

    private static PreparedStatement chuanBi(Connection c, String sql, Object... tham) throws SQLException {
        PreparedStatement ps = c.prepareStatement(sql);
        for (int i = 0; i < tham.length; i++) ps.setObject(i + 1, tham[i]);
        return ps;
    }

    public static String layChuoi(Connection c, String sql, Object... tham) throws SQLException {
        try (PreparedStatement ps = chuanBi(c, sql, tham); ResultSet rs = ps.executeQuery()) {
            return rs.next() ? rs.getString(1) : null;
        }
    }

    public static int layInt(Connection c, String sql, Object... tham) throws SQLException {
        try (PreparedStatement ps = chuanBi(c, sql, tham); ResultSet rs = ps.executeQuery()) {
            return rs.next() ? rs.getInt(1) : 0;
        }
    }

    public static int dem(Connection c, String sql, Object... tham) throws SQLException {
        return layInt(c, sql, tham);
    }

    public static BigDecimal layTien(Connection c, String sql, Object... tham) throws SQLException {
        try (PreparedStatement ps = chuanBi(c, sql, tham); ResultSet rs = ps.executeQuery()) {
            BigDecimal kq = rs.next() ? rs.getBigDecimal(1) : null;
            return kq == null ? BigDecimal.ZERO : kq;
        }
    }

    public static void chay(Connection c, String sql, Object... tham) throws SQLException {
        try (PreparedStatement ps = chuanBi(c, sql, tham)) {
            ps.executeUpdate();
        }
    }


    public static List<String> layDanhSachMa(String bang, String cot) throws SQLException {
        List<String> ds = new ArrayList<>();
        try (Connection c = KetNoi.layKetNoi();
             PreparedStatement ps = c.prepareStatement("SELECT " + cot + " FROM " + bang + " ORDER BY " + cot);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) ds.add(rs.getString(1));
        }
        return ds;
    }


    public static void dongBoGiuong(Connection c, String maGiuong) throws SQLException {
        if (maGiuong == null) return;
        chay(c, "UPDATE Giuong SET TrangThai = CASE WHEN EXISTS (SELECT 1 FROM HopDong h WHERE h.MaGiuong = Giuong.MaGiuong "
                + "AND h.TrangThai IN (N'Chưa bắt đầu', N'Đang hiệu lực')) THEN N'Đã thuê' ELSE N'Trống' END "
                + "WHERE MaGiuong = ? AND TrangThai <> N'Hỏng'", maGiuong);
        chay(c, "UPDATE Phong SET SoNguoiHienTai = (SELECT COUNT(*) FROM Giuong g WHERE g.MaPhong = Phong.MaPhong "
                + "AND g.TrangThai = N'Đã thuê') WHERE MaPhong = (SELECT MaPhong FROM Giuong WHERE MaGiuong = ?)", maGiuong);
    }


    public static void tinhLaiTongTien(Connection c, int maHoaDon) throws SQLException {
        if (maHoaDon == 0) return;
        chay(c, "UPDATE HoaDon SET TongTien = ISNULL((SELECT SUM(ThanhTien) FROM ChiTietHoaDon WHERE MaHoaDon = ?), 0) WHERE MaHD = ?",
                maHoaDon, maHoaDon);
        capNhatTrangThaiHoaDon(c, maHoaDon);
    }


    public static void capNhatTrangThaiHoaDon(Connection c, int maHoaDon) throws SQLException {
        if (maHoaDon == 0) return;
        String daNop = "ISNULL((SELECT SUM(SoTienNop) FROM ThanhToan WHERE MaHoaDon = HoaDon.MaHD), 0)";
        chay(c, "UPDATE HoaDon SET TrangThai = CASE "
                + "WHEN TongTien > 0 AND " + daNop + " >= TongTien THEN N'Đã thanh toán xong' "
                + "WHEN " + daNop + " > 0 THEN N'Đã thanh toán một phần' "
                + "ELSE N'Chưa thanh toán' END WHERE MaHD = ?", maHoaDon);
    }
}
