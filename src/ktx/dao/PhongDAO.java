package ktx.dao;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import ktx.thucthe.Phong;
import ktx.tienich.*;


public class PhongDAO {

    private Phong anh(ResultSet rs) throws SQLException {
        Phong e = new Phong();
        e.setMaPhong(rs.getString("MaPhong"));
        e.setMaKhu(rs.getString("MaKhu"));
        e.setMaLoai(rs.getString("MaLoai"));
        e.setTenPhong(rs.getString("TenPhong"));
        e.setSoNguoiHienTai(rs.getInt("SoNguoiHienTai"));
        e.setTrangThai(rs.getString("TrangThai"));
        return e;
    }

    public List<Phong> layTatCa() throws SQLException {
        List<Phong> ds = new ArrayList<>();
        String sql = "SELECT * FROM Phong ORDER BY MaPhong";
        try (Connection c = KetNoi.layKetNoi(); Statement st = c.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) ds.add(anh(rs));
        }
        return ds;
    }

    public void them(Phong e) throws SQLException {
        try (Connection c = KetNoi.layKetNoi()) {
            c.setAutoCommit(false);
            try {

                String sql = "INSERT INTO Phong (MaPhong, MaKhu, MaLoai, TenPhong, SoNguoiHienTai, TrangThai) VALUES (?, ?, ?, ?, ?, ?)";
                try (PreparedStatement ps = c.prepareStatement(sql)) {
                    int i = 1;
                KetNoi.gan(ps, i++, e.getMaPhong(), Types.NVARCHAR);
                KetNoi.gan(ps, i++, e.getMaKhu(), Types.NVARCHAR);
                KetNoi.gan(ps, i++, e.getMaLoai(), Types.NVARCHAR);
                KetNoi.gan(ps, i++, e.getTenPhong(), Types.NVARCHAR);
                KetNoi.gan(ps, i++, e.getSoNguoiHienTai(), Types.INTEGER);
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

    public void sua(Phong e) throws SQLException {
        try (Connection c = KetNoi.layKetNoi()) {
            c.setAutoCommit(false);
            try {

                String sql = "UPDATE Phong SET MaKhu = ?, MaLoai = ?, TenPhong = ?, TrangThai = ? WHERE MaPhong = ?";
                try (PreparedStatement ps = c.prepareStatement(sql)) {
                    int i = 1;
                KetNoi.gan(ps, i++, e.getMaKhu(), Types.NVARCHAR);
                KetNoi.gan(ps, i++, e.getMaLoai(), Types.NVARCHAR);
                KetNoi.gan(ps, i++, e.getTenPhong(), Types.NVARCHAR);
                KetNoi.gan(ps, i++, e.getTrangThai(), Types.NVARCHAR);
                    KetNoi.gan(ps, i++, e.getMaPhong(), Types.NVARCHAR);
                    ps.executeUpdate();
                }

                c.commit();
            } catch (SQLException | RuntimeException ex) {
                c.rollback();
                throw ex;
            }
        }
    }

    public void xoa(Phong e) throws SQLException {
        try (Connection c = KetNoi.layKetNoi()) {
            c.setAutoCommit(false);
            try {
                try (PreparedStatement ps = c.prepareStatement("DELETE FROM Phong WHERE MaPhong = ?")) {
                    KetNoi.gan(ps, 1, e.getMaPhong(), Types.NVARCHAR);
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
