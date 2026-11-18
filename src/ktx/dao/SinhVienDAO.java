package ktx.dao;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import ktx.thucthe.SinhVien;
import ktx.tienich.*;


public class SinhVienDAO {

    private SinhVien anh(ResultSet rs) throws SQLException {
        SinhVien e = new SinhVien();
        e.setMaSV(rs.getString("MaSV"));
        e.setHoTen(rs.getString("HoTen"));
        e.setNgaySinh(rs.getDate("NgaySinh"));
        e.setGioiTinh(rs.getString("GioiTinh"));
        e.setCCCD(rs.getString("CCCD"));
        e.setSoDienThoai(rs.getString("SoDienThoai"));
        e.setEmail(rs.getString("Email"));
        e.setTruongHoc(rs.getString("TruongHoc"));
        e.setQueQuan(rs.getString("QueQuan"));
        e.setHoTenNguoiThan(rs.getString("HoTenNguoiThan"));
        e.setSDTNguoiThan(rs.getString("SDTNguoiThan"));
        e.setTrangThai(rs.getString("TrangThai"));
        return e;
    }

    public List<SinhVien> layTatCa() throws SQLException {
        List<SinhVien> ds = new ArrayList<>();
        String sql = "SELECT * FROM SinhVien ORDER BY MaSV";
        try (Connection c = KetNoi.layKetNoi(); Statement st = c.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) ds.add(anh(rs));
        }
        return ds;
    }

    public void them(SinhVien e) throws SQLException {
        try (Connection c = KetNoi.layKetNoi()) {
            c.setAutoCommit(false);
            try {

                String sql = "INSERT INTO SinhVien (MaSV, HoTen, NgaySinh, GioiTinh, CCCD, SoDienThoai, Email, TruongHoc, QueQuan, HoTenNguoiThan, SDTNguoiThan, TrangThai) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                try (PreparedStatement ps = c.prepareStatement(sql)) {
                    int i = 1;
                KetNoi.gan(ps, i++, e.getMaSV(), Types.NVARCHAR);
                KetNoi.gan(ps, i++, e.getHoTen(), Types.NVARCHAR);
                KetNoi.gan(ps, i++, e.getNgaySinh(), Types.DATE);
                KetNoi.gan(ps, i++, e.getGioiTinh(), Types.NVARCHAR);
                KetNoi.gan(ps, i++, e.getCCCD(), Types.NVARCHAR);
                KetNoi.gan(ps, i++, e.getSoDienThoai(), Types.NVARCHAR);
                KetNoi.gan(ps, i++, e.getEmail(), Types.NVARCHAR);
                KetNoi.gan(ps, i++, e.getTruongHoc(), Types.NVARCHAR);
                KetNoi.gan(ps, i++, e.getQueQuan(), Types.NVARCHAR);
                KetNoi.gan(ps, i++, e.getHoTenNguoiThan(), Types.NVARCHAR);
                KetNoi.gan(ps, i++, e.getSDTNguoiThan(), Types.NVARCHAR);
                KetNoi.gan(ps, i++, e.getTrangThai(), Types.NVARCHAR);
                    ps.executeUpdate();
                }

                c.commit();
            } catch (SQLException | RuntimeException ex) {
                c.rollback();
                throw ex;
            }
        }
    }

    public void sua(SinhVien e) throws SQLException {
        try (Connection c = KetNoi.layKetNoi()) {
            c.setAutoCommit(false);
            try {

                String sql = "UPDATE SinhVien SET HoTen = ?, NgaySinh = ?, GioiTinh = ?, CCCD = ?, SoDienThoai = ?, Email = ?, TruongHoc = ?, QueQuan = ?, HoTenNguoiThan = ?, SDTNguoiThan = ?, TrangThai = ? WHERE MaSV = ?";
                try (PreparedStatement ps = c.prepareStatement(sql)) {
                    int i = 1;
                KetNoi.gan(ps, i++, e.getHoTen(), Types.NVARCHAR);
                KetNoi.gan(ps, i++, e.getNgaySinh(), Types.DATE);
                KetNoi.gan(ps, i++, e.getGioiTinh(), Types.NVARCHAR);
                KetNoi.gan(ps, i++, e.getCCCD(), Types.NVARCHAR);
                KetNoi.gan(ps, i++, e.getSoDienThoai(), Types.NVARCHAR);
                KetNoi.gan(ps, i++, e.getEmail(), Types.NVARCHAR);
                KetNoi.gan(ps, i++, e.getTruongHoc(), Types.NVARCHAR);
                KetNoi.gan(ps, i++, e.getQueQuan(), Types.NVARCHAR);
                KetNoi.gan(ps, i++, e.getHoTenNguoiThan(), Types.NVARCHAR);
                KetNoi.gan(ps, i++, e.getSDTNguoiThan(), Types.NVARCHAR);
                KetNoi.gan(ps, i++, e.getTrangThai(), Types.NVARCHAR);
                    KetNoi.gan(ps, i++, e.getMaSV(), Types.NVARCHAR);
                    ps.executeUpdate();
                }

                c.commit();
            } catch (SQLException | RuntimeException ex) {
                c.rollback();
                throw ex;
            }
        }
    }

    public void xoa(SinhVien e) throws SQLException {
        try (Connection c = KetNoi.layKetNoi()) {
            c.setAutoCommit(false);
            try {
                try (PreparedStatement ps = c.prepareStatement("DELETE FROM SinhVien WHERE MaSV = ?")) {
                    KetNoi.gan(ps, 1, e.getMaSV(), Types.NVARCHAR);
                    ps.executeUpdate();
                }

                c.commit();
            } catch (SQLException | RuntimeException ex) {
                c.rollback();
                throw ex;
            }
        }
    }
}
