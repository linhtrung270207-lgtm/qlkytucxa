package ktx.dao;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import ktx.thucthe.YeuCauSuaChua;
import ktx.tienich.*;
public class YeuCauSuaChuaDAO {

    private YeuCauSuaChua anh(ResultSet rs) throws SQLException {
        YeuCauSuaChua e = new YeuCauSuaChua();
        e.setMaYC(rs.getInt("MaYC"));
        e.setMaPhong(rs.getString("MaPhong"));
        e.setMaSV(rs.getString("MaSV"));
        e.setThietBiHong(rs.getString("ThietBiHong"));
        e.setMoTaChiTiet(rs.getString("MoTaChiTiet"));
        e.setNgayBao(rs.getTimestamp("NgayBao"));
        e.setTrangThai(rs.getString("TrangThai"));
        e.setNgayHoanThanh(rs.getTimestamp("NgayHoanThanh"));
        e.setChiPhiSuaChua(rs.getBigDecimal("ChiPhiSuaChua"));
        e.setGhiChu(rs.getString("GhiChu"));
        return e;
    }

    public List<YeuCauSuaChua> layTatCa() throws SQLException {
        List<YeuCauSuaChua> ds = new ArrayList<>();
        String sql = "SELECT * FROM YeuCauSuaChua ORDER BY MaYC";
        try (Connection c = KetNoi.layKetNoi(); Statement st = c.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) ds.add(anh(rs));
        }
        return ds;
    }

    public void them(YeuCauSuaChua e) throws SQLException {
        try (Connection c = KetNoi.layKetNoi()) {
            c.setAutoCommit(false);
            try {
                if (e.getNgayBao() == null) e.setNgayBao(new Timestamp(System.currentTimeMillis()));

                String sql = "INSERT INTO YeuCauSuaChua (MaPhong, MaSV, ThietBiHong, MoTaChiTiet, NgayBao, TrangThai, NgayHoanThanh, ChiPhiSuaChua, GhiChu) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                try (PreparedStatement ps = c.prepareStatement(sql)) {
                    int i = 1;
                KetNoi.gan(ps, i++, e.getMaPhong(), Types.NVARCHAR);
                KetNoi.gan(ps, i++, e.getMaSV(), Types.NVARCHAR);
                KetNoi.gan(ps, i++, e.getThietBiHong(), Types.NVARCHAR);
                KetNoi.gan(ps, i++, e.getMoTaChiTiet(), Types.NVARCHAR);
                KetNoi.gan(ps, i++, e.getNgayBao(), Types.TIMESTAMP);
                KetNoi.gan(ps, i++, e.getTrangThai(), Types.NVARCHAR);
                KetNoi.gan(ps, i++, e.getNgayHoanThanh(), Types.TIMESTAMP);
                KetNoi.gan(ps, i++, e.getChiPhiSuaChua(), Types.DECIMAL);
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

    public void sua(YeuCauSuaChua e) throws SQLException {
        try (Connection c = KetNoi.layKetNoi()) {
            c.setAutoCommit(false);
            try {
                if (e.getNgayBao() == null) e.setNgayBao(new Timestamp(System.currentTimeMillis()));

                String sql = "UPDATE YeuCauSuaChua SET MaPhong = ?, MaSV = ?, ThietBiHong = ?, MoTaChiTiet = ?, NgayBao = ?, TrangThai = ?, NgayHoanThanh = ?, ChiPhiSuaChua = ?, GhiChu = ? WHERE MaYC = ?";
                try (PreparedStatement ps = c.prepareStatement(sql)) {
                    int i = 1;
                KetNoi.gan(ps, i++, e.getMaPhong(), Types.NVARCHAR);
                KetNoi.gan(ps, i++, e.getMaSV(), Types.NVARCHAR);
                KetNoi.gan(ps, i++, e.getThietBiHong(), Types.NVARCHAR);
                KetNoi.gan(ps, i++, e.getMoTaChiTiet(), Types.NVARCHAR);
                KetNoi.gan(ps, i++, e.getNgayBao(), Types.TIMESTAMP);
                KetNoi.gan(ps, i++, e.getTrangThai(), Types.NVARCHAR);
                KetNoi.gan(ps, i++, e.getNgayHoanThanh(), Types.TIMESTAMP);
                KetNoi.gan(ps, i++, e.getChiPhiSuaChua(), Types.DECIMAL);
                KetNoi.gan(ps, i++, e.getGhiChu(), Types.NVARCHAR);
                    KetNoi.gan(ps, i++, e.getMaYC(), Types.INTEGER);
                    ps.executeUpdate();
                }

                c.commit();
            } catch (SQLException | RuntimeException ex) {
                c.rollback();
                throw ex;
            }
        }
    }

    public void xoa(YeuCauSuaChua e) throws SQLException {
        try (Connection c = KetNoi.layKetNoi()) {
            c.setAutoCommit(false);
            try {
                try (PreparedStatement ps = c.prepareStatement("DELETE FROM YeuCauSuaChua WHERE MaYC = ?")) {
                    KetNoi.gan(ps, 1, e.getMaYC(), Types.INTEGER);
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
