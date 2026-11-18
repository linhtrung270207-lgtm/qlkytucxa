package ktx.dao;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import ktx.thucthe.ChiSoDienNuoc;
import ktx.tienich.*;

public class ChiSoDienNuocDAO {

    private ChiSoDienNuoc anh(ResultSet rs) throws SQLException {
        ChiSoDienNuoc e = new ChiSoDienNuoc();
        e.setMaCS(rs.getInt("MaCS"));
        e.setMaPhong(rs.getString("MaPhong"));
        e.setThang(rs.getInt("Thang"));
        e.setNam(rs.getInt("Nam"));
        e.setSoDienCu(rs.getInt("SoDienCu"));
        e.setSoDienMoi(rs.getInt("SoDienMoi"));
        e.setSoNuocCu(rs.getInt("SoNuocCu"));
        e.setSoNuocMoi(rs.getInt("SoNuocMoi"));
        e.setMaNVGhi(rs.getString("MaNVGhi"));
        e.setNgayGhi(rs.getDate("NgayGhi"));
        return e;
    }

    public List<ChiSoDienNuoc> layTatCa() throws SQLException {
        List<ChiSoDienNuoc> ds = new ArrayList<>();
        String sql = "SELECT * FROM ChiSoDienNuoc ORDER BY MaCS";
        try (Connection c = KetNoi.layKetNoi(); Statement st = c.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) ds.add(anh(rs));
        }
        return ds;
    }

    public void them(ChiSoDienNuoc e) throws SQLException {
        try (Connection c = KetNoi.layKetNoi()) {
            c.setAutoCommit(false);
            try {
                if (e.getNgayGhi() == null) e.setNgayGhi(new Date(System.currentTimeMillis()));

                String sql = "INSERT INTO ChiSoDienNuoc (MaPhong, Thang, Nam, SoDienCu, SoDienMoi, SoNuocCu, SoNuocMoi, MaNVGhi, NgayGhi) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                try (PreparedStatement ps = c.prepareStatement(sql)) {
                    int i = 1;
                KetNoi.gan(ps, i++, e.getMaPhong(), Types.NVARCHAR);
                KetNoi.gan(ps, i++, e.getThang(), Types.INTEGER);
                KetNoi.gan(ps, i++, e.getNam(), Types.INTEGER);
                KetNoi.gan(ps, i++, e.getSoDienCu(), Types.INTEGER);
                KetNoi.gan(ps, i++, e.getSoDienMoi(), Types.INTEGER);
                KetNoi.gan(ps, i++, e.getSoNuocCu(), Types.INTEGER);
                KetNoi.gan(ps, i++, e.getSoNuocMoi(), Types.INTEGER);
                KetNoi.gan(ps, i++, e.getMaNVGhi(), Types.NVARCHAR);
                KetNoi.gan(ps, i++, e.getNgayGhi(), Types.DATE);
                    ps.executeUpdate();
                }

                c.commit();
            } catch (SQLException | RuntimeException ex) {
                c.rollback();
                throw ex;
            }
        }
    }

    public void sua(ChiSoDienNuoc e) throws SQLException {
        try (Connection c = KetNoi.layKetNoi()) {
            c.setAutoCommit(false);
            try {
                if (e.getNgayGhi() == null) e.setNgayGhi(new Date(System.currentTimeMillis()));

                String sql = "UPDATE ChiSoDienNuoc SET MaPhong = ?, Thang = ?, Nam = ?, SoDienCu = ?, SoDienMoi = ?, SoNuocCu = ?, SoNuocMoi = ?, MaNVGhi = ?, NgayGhi = ? WHERE MaCS = ?";
                try (PreparedStatement ps = c.prepareStatement(sql)) {
                    int i = 1;
                KetNoi.gan(ps, i++, e.getMaPhong(), Types.NVARCHAR);
                KetNoi.gan(ps, i++, e.getThang(), Types.INTEGER);
                KetNoi.gan(ps, i++, e.getNam(), Types.INTEGER);
                KetNoi.gan(ps, i++, e.getSoDienCu(), Types.INTEGER);
                KetNoi.gan(ps, i++, e.getSoDienMoi(), Types.INTEGER);
                KetNoi.gan(ps, i++, e.getSoNuocCu(), Types.INTEGER);
                KetNoi.gan(ps, i++, e.getSoNuocMoi(), Types.INTEGER);
                KetNoi.gan(ps, i++, e.getMaNVGhi(), Types.NVARCHAR);
                KetNoi.gan(ps, i++, e.getNgayGhi(), Types.DATE);
                    KetNoi.gan(ps, i++, e.getMaCS(), Types.INTEGER);
                    ps.executeUpdate();
                }

                c.commit();
            } catch (SQLException | RuntimeException ex) {
                c.rollback();
                throw ex;
            }
        }
    }

    public void xoa(ChiSoDienNuoc e) throws SQLException {
        try (Connection c = KetNoi.layKetNoi()) {
            c.setAutoCommit(false);
            try {
                try (PreparedStatement ps = c.prepareStatement("DELETE FROM ChiSoDienNuoc WHERE MaCS = ?")) {
                    KetNoi.gan(ps, 1, e.getMaCS(), Types.INTEGER);
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
