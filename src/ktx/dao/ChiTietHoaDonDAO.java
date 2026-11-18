package ktx.dao;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import ktx.thucthe.ChiTietHoaDon;
import ktx.tienich.*;


public class ChiTietHoaDonDAO {

    private ChiTietHoaDon anh(ResultSet rs) throws SQLException {
        ChiTietHoaDon e = new ChiTietHoaDon();
        e.setMaCTHD(rs.getInt("MaCTHD"));
        e.setMaHoaDon(rs.getInt("MaHoaDon"));
        e.setTenLoaiPhi(rs.getString("TenLoaiPhi"));
        e.setSoLuong(rs.getDouble("SoLuong"));
        e.setDonGia(rs.getBigDecimal("DonGia"));
        e.setThanhTien(rs.getBigDecimal("ThanhTien"));
        return e;
    }

    public List<ChiTietHoaDon> layTatCa() throws SQLException {
        List<ChiTietHoaDon> ds = new ArrayList<>();
        String sql = "SELECT * FROM ChiTietHoaDon ORDER BY MaCTHD";
        try (Connection c = KetNoi.layKetNoi(); Statement st = c.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) ds.add(anh(rs));
        }
        return ds;
    }

    public void them(ChiTietHoaDon e) throws SQLException {
        try (Connection c = KetNoi.layKetNoi()) {
            c.setAutoCommit(false);
            try {

                String sql = "INSERT INTO ChiTietHoaDon (MaHoaDon, TenLoaiPhi, SoLuong, DonGia) VALUES (?, ?, ?, ?)";
                try (PreparedStatement ps = c.prepareStatement(sql)) {
                    int i = 1;
                KetNoi.gan(ps, i++, e.getMaHoaDon(), Types.INTEGER);
                KetNoi.gan(ps, i++, e.getTenLoaiPhi(), Types.NVARCHAR);
                KetNoi.gan(ps, i++, e.getSoLuong(), Types.DOUBLE);
                KetNoi.gan(ps, i++, e.getDonGia(), Types.DECIMAL);
                    ps.executeUpdate();
                }
                DongBo.tinhLaiTongTien(c, e.getMaHoaDon());

                c.commit();
            } catch (SQLException | RuntimeException ex) {
                c.rollback();
                throw ex;
            }
        }
    }

    public void sua(ChiTietHoaDon e) throws SQLException {
        try (Connection c = KetNoi.layKetNoi()) {
            c.setAutoCommit(false);
            try {
                int hdCu = DongBo.layInt(c, "SELECT MaHoaDon FROM ChiTietHoaDon WHERE MaCTHD = ?", e.getMaCTHD());

                String sql = "UPDATE ChiTietHoaDon SET MaHoaDon = ?, TenLoaiPhi = ?, SoLuong = ?, DonGia = ? WHERE MaCTHD = ?";
                try (PreparedStatement ps = c.prepareStatement(sql)) {
                    int i = 1;
                KetNoi.gan(ps, i++, e.getMaHoaDon(), Types.INTEGER);
                KetNoi.gan(ps, i++, e.getTenLoaiPhi(), Types.NVARCHAR);
                KetNoi.gan(ps, i++, e.getSoLuong(), Types.DOUBLE);
                KetNoi.gan(ps, i++, e.getDonGia(), Types.DECIMAL);
                    KetNoi.gan(ps, i++, e.getMaCTHD(), Types.INTEGER);
                    ps.executeUpdate();
                }
                DongBo.tinhLaiTongTien(c, hdCu);
                DongBo.tinhLaiTongTien(c, e.getMaHoaDon());

                c.commit();
            } catch (SQLException | RuntimeException ex) {
                c.rollback();
                throw ex;
            }
        }
    }

    public void xoa(ChiTietHoaDon e) throws SQLException {
        try (Connection c = KetNoi.layKetNoi()) {
            c.setAutoCommit(false);
            try {
                try (PreparedStatement ps = c.prepareStatement("DELETE FROM ChiTietHoaDon WHERE MaCTHD = ?")) {
                    KetNoi.gan(ps, 1, e.getMaCTHD(), Types.INTEGER);
                    ps.executeUpdate();
                }
                DongBo.tinhLaiTongTien(c, e.getMaHoaDon());

                c.commit();
            } catch (SQLException | RuntimeException ex) {
                c.rollback();
                throw ex;
            }
        }
    }
}
