package ktx.dao;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import ktx.thucthe.KyLuat;
import ktx.tienich.*;

public class KyLuatDAO {

    private KyLuat anh(ResultSet rs) throws SQLException {
        KyLuat e = new KyLuat();
        e.setMaKL(rs.getInt("MaKL"));
        e.setMaSV(rs.getString("MaSV"));
        e.setMaNVLap(rs.getString("MaNVLap"));
        e.setLoai(rs.getString("Loai"));
        e.setHinhThuc(rs.getString("HinhThuc"));
        e.setNoiDung(rs.getString("NoiDung"));
        e.setNgayQuyetDinh(rs.getTimestamp("NgayQuyetDinh"));
        e.setTienPhat(rs.getBigDecimal("TienPhat"));
        return e;
    }

    public List<KyLuat> layTatCa() throws SQLException {
        List<KyLuat> ds = new ArrayList<>();
        String sql = "SELECT * FROM KyLuat ORDER BY MaKL";
        try (Connection c = KetNoi.layKetNoi(); Statement st = c.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) ds.add(anh(rs));
        }
        return ds;
    }

    public void them(KyLuat e) throws SQLException {
        try (Connection c = KetNoi.layKetNoi()) {
            c.setAutoCommit(false);
            try {
                if (e.getNgayQuyetDinh() == null) e.setNgayQuyetDinh(new Timestamp(System.currentTimeMillis()));

                String sql = "INSERT INTO KyLuat (MaSV, MaNVLap, Loai, HinhThuc, NoiDung, NgayQuyetDinh, TienPhat) VALUES (?, ?, ?, ?, ?, ?, ?)";
                try (PreparedStatement ps = c.prepareStatement(sql)) {
                    int i = 1;
                KetNoi.gan(ps, i++, e.getMaSV(), Types.NVARCHAR);
                KetNoi.gan(ps, i++, e.getMaNVLap(), Types.NVARCHAR);
                KetNoi.gan(ps, i++, e.getLoai(), Types.NVARCHAR);
                KetNoi.gan(ps, i++, e.getHinhThuc(), Types.NVARCHAR);
                KetNoi.gan(ps, i++, e.getNoiDung(), Types.NVARCHAR);
                KetNoi.gan(ps, i++, e.getNgayQuyetDinh(), Types.TIMESTAMP);
                KetNoi.gan(ps, i++, e.getTienPhat(), Types.DECIMAL);
                    ps.executeUpdate();
                }

                c.commit();
            } catch (SQLException | RuntimeException ex) {
                c.rollback();
                throw ex;
            }
        }
    }

    public void sua(KyLuat e) throws SQLException {
        try (Connection c = KetNoi.layKetNoi()) {
            c.setAutoCommit(false);
            try {
                if (e.getNgayQuyetDinh() == null) e.setNgayQuyetDinh(new Timestamp(System.currentTimeMillis()));

                String sql = "UPDATE KyLuat SET MaSV = ?, MaNVLap = ?, Loai = ?, HinhThuc = ?, NoiDung = ?, NgayQuyetDinh = ?, TienPhat = ? WHERE MaKL = ?";
                try (PreparedStatement ps = c.prepareStatement(sql)) {
                    int i = 1;
                KetNoi.gan(ps, i++, e.getMaSV(), Types.NVARCHAR);
                KetNoi.gan(ps, i++, e.getMaNVLap(), Types.NVARCHAR);
                KetNoi.gan(ps, i++, e.getLoai(), Types.NVARCHAR);
                KetNoi.gan(ps, i++, e.getHinhThuc(), Types.NVARCHAR);
                KetNoi.gan(ps, i++, e.getNoiDung(), Types.NVARCHAR);
                KetNoi.gan(ps, i++, e.getNgayQuyetDinh(), Types.TIMESTAMP);
                KetNoi.gan(ps, i++, e.getTienPhat(), Types.DECIMAL);
                    KetNoi.gan(ps, i++, e.getMaKL(), Types.INTEGER);
                    ps.executeUpdate();
                }

                c.commit();
            } catch (SQLException | RuntimeException ex) {
                c.rollback();
                throw ex;
            }
        }
    }

    public void xoa(KyLuat e) throws SQLException {
        try (Connection c = KetNoi.layKetNoi()) {
            c.setAutoCommit(false);
            try {
                try (PreparedStatement ps = c.prepareStatement("DELETE FROM KyLuat WHERE MaKL = ?")) {
                    KetNoi.gan(ps, 1, e.getMaKL(), Types.INTEGER);
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
