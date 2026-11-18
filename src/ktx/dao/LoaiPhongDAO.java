package ktx.dao;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import ktx.thucthe.LoaiPhong;
import ktx.tienich.*;


public class LoaiPhongDAO {

    private LoaiPhong anh(ResultSet rs) throws SQLException {
        LoaiPhong e = new LoaiPhong();
        e.setMaLoai(rs.getString("MaLoai"));
        e.setTenLoai(rs.getString("TenLoai"));
        e.setDienTich(rs.getObject("DienTich") == null ? null : Double.valueOf(rs.getDouble("DienTich")));
        e.setSoGiuong(rs.getInt("SoGiuong"));
        e.setGiaPhong(rs.getBigDecimal("GiaPhong"));
        e.setMoTa(rs.getString("MoTa"));
        return e;
    }

    public List<LoaiPhong> layTatCa() throws SQLException {
        List<LoaiPhong> ds = new ArrayList<>();
        String sql = "SELECT * FROM LoaiPhong ORDER BY MaLoai";
        try (Connection c = KetNoi.layKetNoi(); Statement st = c.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) ds.add(anh(rs));
        }
        return ds;
    }

    public void them(LoaiPhong e) throws SQLException {
        try (Connection c = KetNoi.layKetNoi()) {
            c.setAutoCommit(false);
            try {

                String sql = "INSERT INTO LoaiPhong (MaLoai, TenLoai, DienTich, SoGiuong, GiaPhong, MoTa) VALUES (?, ?, ?, ?, ?, ?)";
                try (PreparedStatement ps = c.prepareStatement(sql)) {
                    int i = 1;
                KetNoi.gan(ps, i++, e.getMaLoai(), Types.NVARCHAR);
                KetNoi.gan(ps, i++, e.getTenLoai(), Types.NVARCHAR);
                KetNoi.gan(ps, i++, e.getDienTich(), Types.DOUBLE);
                KetNoi.gan(ps, i++, e.getSoGiuong(), Types.INTEGER);
                KetNoi.gan(ps, i++, e.getGiaPhong(), Types.DECIMAL);
                KetNoi.gan(ps, i++, e.getMoTa(), Types.NVARCHAR);
                    ps.executeUpdate();
                }

                c.commit();
            } catch (SQLException | RuntimeException ex) {
                c.rollback();
                throw ex;
            }
        }
    }

    public void sua(LoaiPhong e) throws SQLException {
        try (Connection c = KetNoi.layKetNoi()) {
            c.setAutoCommit(false);
            try {

                String sql = "UPDATE LoaiPhong SET TenLoai = ?, DienTich = ?, SoGiuong = ?, GiaPhong = ?, MoTa = ? WHERE MaLoai = ?";
                try (PreparedStatement ps = c.prepareStatement(sql)) {
                    int i = 1;
                KetNoi.gan(ps, i++, e.getTenLoai(), Types.NVARCHAR);
                KetNoi.gan(ps, i++, e.getDienTich(), Types.DOUBLE);
                KetNoi.gan(ps, i++, e.getSoGiuong(), Types.INTEGER);
                KetNoi.gan(ps, i++, e.getGiaPhong(), Types.DECIMAL);
                KetNoi.gan(ps, i++, e.getMoTa(), Types.NVARCHAR);
                    KetNoi.gan(ps, i++, e.getMaLoai(), Types.NVARCHAR);
                    ps.executeUpdate();
                }

                c.commit();
            } catch (SQLException | RuntimeException ex) {
                c.rollback();
                throw ex;
            }
        }
    }

    public void xoa(LoaiPhong e) throws SQLException {
        try (Connection c = KetNoi.layKetNoi()) {
            c.setAutoCommit(false);
            try {
                try (PreparedStatement ps = c.prepareStatement("DELETE FROM LoaiPhong WHERE MaLoai = ?")) {
                    KetNoi.gan(ps, 1, e.getMaLoai(), Types.NVARCHAR);
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
