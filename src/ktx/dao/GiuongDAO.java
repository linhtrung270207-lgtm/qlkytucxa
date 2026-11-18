package ktx.dao;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import ktx.thucthe.Giuong;
import ktx.tienich.*;


public class GiuongDAO {

    private Giuong anh(ResultSet rs) throws SQLException {
        Giuong e = new Giuong();
        e.setMaGiuong(rs.getString("MaGiuong"));
        e.setMaPhong(rs.getString("MaPhong"));
        e.setSoThuTu(rs.getInt("SoThuTu"));
        e.setTrangThai(rs.getString("TrangThai"));
        return e;
    }

    public List<Giuong> layTatCa() throws SQLException {
        List<Giuong> ds = new ArrayList<>();
        String sql = "SELECT * FROM Giuong ORDER BY MaGiuong";
        try (Connection c = KetNoi.layKetNoi(); Statement st = c.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) ds.add(anh(rs));
        }
        return ds;
    }

    public void them(Giuong e) throws SQLException {
        try (Connection c = KetNoi.layKetNoi()) {
            c.setAutoCommit(false);
            try {

                String sql = "INSERT INTO Giuong (MaGiuong, MaPhong, SoThuTu, TrangThai) VALUES (?, ?, ?, ?)";
                try (PreparedStatement ps = c.prepareStatement(sql)) {
                    int i = 1;
                KetNoi.gan(ps, i++, e.getMaGiuong(), Types.NVARCHAR);
                KetNoi.gan(ps, i++, e.getMaPhong(), Types.NVARCHAR);
                KetNoi.gan(ps, i++, e.getSoThuTu(), Types.INTEGER);
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

    public void sua(Giuong e) throws SQLException {
        try (Connection c = KetNoi.layKetNoi()) {
            c.setAutoCommit(false);
            try {

                String sql = "UPDATE Giuong SET MaPhong = ?, SoThuTu = ?, TrangThai = ? WHERE MaGiuong = ?";
                try (PreparedStatement ps = c.prepareStatement(sql)) {
                    int i = 1;
                KetNoi.gan(ps, i++, e.getMaPhong(), Types.NVARCHAR);
                KetNoi.gan(ps, i++, e.getSoThuTu(), Types.INTEGER);
                KetNoi.gan(ps, i++, e.getTrangThai(), Types.NVARCHAR);
                    KetNoi.gan(ps, i++, e.getMaGiuong(), Types.NVARCHAR);
                    ps.executeUpdate();
                }

                c.commit();
            } catch (SQLException | RuntimeException ex) {
                c.rollback();
                throw ex;
            }
        }
    }

    public void xoa(Giuong e) throws SQLException {
        try (Connection c = KetNoi.layKetNoi()) {
            c.setAutoCommit(false);
            try {
                try (PreparedStatement ps = c.prepareStatement("DELETE FROM Giuong WHERE MaGiuong = ?")) {
                    KetNoi.gan(ps, 1, e.getMaGiuong(), Types.NVARCHAR);
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
