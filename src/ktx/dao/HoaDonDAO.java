package ktx.dao;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import ktx.thucthe.HoaDon;
import ktx.tienich.*;


public class HoaDonDAO {

    private HoaDon anh(ResultSet rs) throws SQLException {
        HoaDon e = new HoaDon();
        e.setMaHD(rs.getInt("MaHD"));
        e.setMaPhong(rs.getString("MaPhong"));
        e.setMaNVLap(rs.getString("MaNVLap"));
        e.setThang(rs.getInt("Thang"));
        e.setNam(rs.getInt("Nam"));
        e.setNgayLap(rs.getTimestamp("NgayLap"));
        e.setHanThanhToan(rs.getDate("HanThanhToan"));
        e.setTongTien(rs.getBigDecimal("TongTien"));
        e.setTrangThai(rs.getString("TrangThai"));
        e.setGhiChu(rs.getString("GhiChu"));
        return e;
    }

    public List<HoaDon> layTatCa() throws SQLException {
        List<HoaDon> ds = new ArrayList<>();
        String sql = "SELECT * FROM HoaDon ORDER BY MaHD";
        try (Connection c = KetNoi.layKetNoi(); Statement st = c.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) ds.add(anh(rs));
        }
        return ds;
    }

    public void them(HoaDon e) throws SQLException {
        try (Connection c = KetNoi.layKetNoi()) {
            c.setAutoCommit(false);
            try {
                if (e.getNgayLap() == null) e.setNgayLap(new Timestamp(System.currentTimeMillis()));

                String sql = "INSERT INTO HoaDon (MaPhong, MaNVLap, Thang, Nam, NgayLap, HanThanhToan, TongTien, TrangThai, GhiChu) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                try (PreparedStatement ps = c.prepareStatement(sql)) {
                    int i = 1;
                KetNoi.gan(ps, i++, e.getMaPhong(), Types.NVARCHAR);
                KetNoi.gan(ps, i++, e.getMaNVLap(), Types.NVARCHAR);
                KetNoi.gan(ps, i++, e.getThang(), Types.INTEGER);
                KetNoi.gan(ps, i++, e.getNam(), Types.INTEGER);
                KetNoi.gan(ps, i++, e.getNgayLap(), Types.TIMESTAMP);
                KetNoi.gan(ps, i++, e.getHanThanhToan(), Types.DATE);
                KetNoi.gan(ps, i++, e.getTongTien(), Types.DECIMAL);
                KetNoi.gan(ps, i++, e.getTrangThai(), Types.NVARCHAR);
                KetNoi.gan(ps, i++, e.getGhiChu(), Types.NVARCHAR);
                    ps.executeUpdate();
                }

                c.commit();
            } catch (SQLException | RuntimeException ex) {
                c.rollback();
                throw ex;
            }
        }
    }

    public void sua(HoaDon e) throws SQLException {
        try (Connection c = KetNoi.layKetNoi()) {
            c.setAutoCommit(false);
            try {
                if (e.getNgayLap() == null) e.setNgayLap(new Timestamp(System.currentTimeMillis()));

                String sql = "UPDATE HoaDon SET MaPhong = ?, MaNVLap = ?, Thang = ?, Nam = ?, NgayLap = ?, HanThanhToan = ?, TrangThai = ?, GhiChu = ? WHERE MaHD = ?";
                try (PreparedStatement ps = c.prepareStatement(sql)) {
                    int i = 1;
                KetNoi.gan(ps, i++, e.getMaPhong(), Types.NVARCHAR);
                KetNoi.gan(ps, i++, e.getMaNVLap(), Types.NVARCHAR);
                KetNoi.gan(ps, i++, e.getThang(), Types.INTEGER);
                KetNoi.gan(ps, i++, e.getNam(), Types.INTEGER);
                KetNoi.gan(ps, i++, e.getNgayLap(), Types.TIMESTAMP);
                KetNoi.gan(ps, i++, e.getHanThanhToan(), Types.DATE);
                KetNoi.gan(ps, i++, e.getTrangThai(), Types.NVARCHAR);
                KetNoi.gan(ps, i++, e.getGhiChu(), Types.NVARCHAR);
                    KetNoi.gan(ps, i++, e.getMaHD(), Types.INTEGER);
                    ps.executeUpdate();
                }

                c.commit();
            } catch (SQLException | RuntimeException ex) {
                c.rollback();
                throw ex;
            }
        }
    }

    public void xoa(HoaDon e) throws SQLException {
        try (Connection c = KetNoi.layKetNoi()) {
            c.setAutoCommit(false);
            try {
                try (PreparedStatement ps = c.prepareStatement("DELETE FROM HoaDon WHERE MaHD = ?")) {
                    KetNoi.gan(ps, 1, e.getMaHD(), Types.INTEGER);
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
