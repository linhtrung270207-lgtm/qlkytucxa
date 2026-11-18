package ktx.dao;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import ktx.thucthe.KhuNha;
import ktx.tienich.*;


public class KhuNhaDAO {

    private KhuNha anh(ResultSet rs) throws SQLException {
        KhuNha e = new KhuNha();
        e.setMaKhu(rs.getString("MaKhu"));
        e.setTenKhu(rs.getString("TenKhu"));
        e.setLoaiKhu(rs.getString("LoaiKhu"));
        e.setGhiChu(rs.getString("GhiChu"));
        return e;
    }

    public List<KhuNha> layTatCa() throws SQLException {
        List<KhuNha> ds = new ArrayList<>();
        String sql = "SELECT * FROM KhuNha ORDER BY MaKhu";
        try (Connection c = KetNoi.layKetNoi(); Statement st = c.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) ds.add(anh(rs));
        }
        return ds;
    }

    public void them(KhuNha e) throws SQLException {
        try (Connection c = KetNoi.layKetNoi()) {
            c.setAutoCommit(false);
            try {

                String sql = "INSERT INTO KhuNha (MaKhu, TenKhu, LoaiKhu, GhiChu) VALUES (?, ?, ?, ?)";
                try (PreparedStatement ps = c.prepareStatement(sql)) {
                    int i = 1;
                KetNoi.gan(ps, i++, e.getMaKhu(), Types.NVARCHAR);
                KetNoi.gan(ps, i++, e.getTenKhu(), Types.NVARCHAR);
                KetNoi.gan(ps, i++, e.getLoaiKhu(), Types.NVARCHAR);
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

    public void sua(KhuNha e) throws SQLException {
        try (Connection c = KetNoi.layKetNoi()) {
            c.setAutoCommit(false);
            try {

                String sql = "UPDATE KhuNha SET TenKhu = ?, LoaiKhu = ?, GhiChu = ? WHERE MaKhu = ?";
                try (PreparedStatement ps = c.prepareStatement(sql)) {
                    int i = 1;
                KetNoi.gan(ps, i++, e.getTenKhu(), Types.NVARCHAR);
                KetNoi.gan(ps, i++, e.getLoaiKhu(), Types.NVARCHAR);
                KetNoi.gan(ps, i++, e.getGhiChu(), Types.NVARCHAR);
                    KetNoi.gan(ps, i++, e.getMaKhu(), Types.NVARCHAR);
                    ps.executeUpdate();
                }

                c.commit();
            } catch (SQLException | RuntimeException ex) {
                c.rollback();
                throw ex;
            }
        }
    }

    public void xoa(KhuNha e) throws SQLException {
        try (Connection c = KetNoi.layKetNoi()) {
            c.setAutoCommit(false);
            try {
                try (PreparedStatement ps = c.prepareStatement("DELETE FROM KhuNha WHERE MaKhu = ?")) {
                    KetNoi.gan(ps, 1, e.getMaKhu(), Types.NVARCHAR);
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
