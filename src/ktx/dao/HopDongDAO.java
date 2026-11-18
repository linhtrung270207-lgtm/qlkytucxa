package ktx.dao;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import ktx.thucthe.HopDong;
import ktx.tienich.*;


public class HopDongDAO {

    private HopDong anh(ResultSet rs) throws SQLException {
        HopDong e = new HopDong();
        e.setMaHD(rs.getInt("MaHD"));
        e.setMaSV(rs.getString("MaSV"));
        e.setMaGiuong(rs.getString("MaGiuong"));
        e.setMaNVLap(rs.getString("MaNVLap"));
        e.setNgayLap(rs.getTimestamp("NgayLap"));
        e.setNgayBatDau(rs.getDate("NgayBatDau"));
        e.setNgayKetThuc(rs.getDate("NgayKetThuc"));
        e.setTienCoc(rs.getBigDecimal("TienCoc"));
        e.setTrangThai(rs.getString("TrangThai"));
        return e;
    }

    public List<HopDong> layTatCa() throws SQLException {
        List<HopDong> ds = new ArrayList<>();
        String sql = "SELECT * FROM HopDong ORDER BY MaHD";
        try (Connection c = KetNoi.layKetNoi(); Statement st = c.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) ds.add(anh(rs));
        }
        return ds;
    }

    public void them(HopDong e) throws SQLException {
        try (Connection c = KetNoi.layKetNoi()) {
            c.setAutoCommit(false);
            try {
                if (e.getNgayLap() == null) e.setNgayLap(new Timestamp(System.currentTimeMillis()));
                kiemTraHopDong(c, e, 0);

                String sql = "INSERT INTO HopDong (MaSV, MaGiuong, MaNVLap, NgayLap, NgayBatDau, NgayKetThuc, TienCoc, TrangThai) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                try (PreparedStatement ps = c.prepareStatement(sql)) {
                    int i = 1;
                KetNoi.gan(ps, i++, e.getMaSV(), Types.NVARCHAR);
                KetNoi.gan(ps, i++, e.getMaGiuong(), Types.NVARCHAR);
                KetNoi.gan(ps, i++, e.getMaNVLap(), Types.NVARCHAR);
                KetNoi.gan(ps, i++, e.getNgayLap(), Types.TIMESTAMP);
                KetNoi.gan(ps, i++, e.getNgayBatDau(), Types.DATE);
                KetNoi.gan(ps, i++, e.getNgayKetThuc(), Types.DATE);
                KetNoi.gan(ps, i++, e.getTienCoc(), Types.DECIMAL);
                KetNoi.gan(ps, i++, e.getTrangThai(), Types.NVARCHAR);
                    ps.executeUpdate();
                }
                DongBo.dongBoGiuong(c, e.getMaGiuong());

                c.commit();
            } catch (SQLException | RuntimeException ex) {
                c.rollback();
                throw ex;
            }
        }
    }

    public void sua(HopDong e) throws SQLException {
        try (Connection c = KetNoi.layKetNoi()) {
            c.setAutoCommit(false);
            try {
                if (e.getNgayLap() == null) e.setNgayLap(new Timestamp(System.currentTimeMillis()));
                kiemTraHopDong(c, e, e.getMaHD());
                String giuongCu = DongBo.layChuoi(c, "SELECT MaGiuong FROM HopDong WHERE MaHD = ?", e.getMaHD());

                String sql = "UPDATE HopDong SET MaSV = ?, MaGiuong = ?, MaNVLap = ?, NgayLap = ?, NgayBatDau = ?, NgayKetThuc = ?, TienCoc = ?, TrangThai = ? WHERE MaHD = ?";
                try (PreparedStatement ps = c.prepareStatement(sql)) {
                    int i = 1;
                KetNoi.gan(ps, i++, e.getMaSV(), Types.NVARCHAR);
                KetNoi.gan(ps, i++, e.getMaGiuong(), Types.NVARCHAR);
                KetNoi.gan(ps, i++, e.getMaNVLap(), Types.NVARCHAR);
                KetNoi.gan(ps, i++, e.getNgayLap(), Types.TIMESTAMP);
                KetNoi.gan(ps, i++, e.getNgayBatDau(), Types.DATE);
                KetNoi.gan(ps, i++, e.getNgayKetThuc(), Types.DATE);
                KetNoi.gan(ps, i++, e.getTienCoc(), Types.DECIMAL);
                KetNoi.gan(ps, i++, e.getTrangThai(), Types.NVARCHAR);
                    KetNoi.gan(ps, i++, e.getMaHD(), Types.INTEGER);
                    ps.executeUpdate();
                }
                DongBo.dongBoGiuong(c, giuongCu);
                DongBo.dongBoGiuong(c, e.getMaGiuong());

                c.commit();
            } catch (SQLException | RuntimeException ex) {
                c.rollback();
                throw ex;
            }
        }
    }

    public void xoa(HopDong e) throws SQLException {
        try (Connection c = KetNoi.layKetNoi()) {
            c.setAutoCommit(false);
            try {
                try (PreparedStatement ps = c.prepareStatement("DELETE FROM HopDong WHERE MaHD = ?")) {
                    KetNoi.gan(ps, 1, e.getMaHD(), Types.INTEGER);
                    ps.executeUpdate();
                }
                DongBo.dongBoGiuong(c, e.getMaGiuong());

                c.commit();
            } catch (SQLException | RuntimeException ex) {
                c.rollback();
                throw ex;
            }
        }
    }

    private static final String HOAT_DONG = "TrangThai IN (N'Chưa bắt đầu', N'Đang hiệu lực')";


    private void kiemTraHopDong(Connection c, HopDong e, int maHDHienTai) throws SQLException {
        if (e.getNgayKetThuc() != null && e.getNgayBatDau() != null && !e.getNgayKetThuc().after(e.getNgayBatDau()))
            throw new SQLException("Ngày kết thúc phải sau ngày bắt đầu.");
        boolean conHan = "Chưa bắt đầu".equals(e.getTrangThai()) || "Đang hiệu lực".equals(e.getTrangThai());
        if (!conHan) return;
        String ttGiuong = DongBo.layChuoi(c, "SELECT TrangThai FROM Giuong WHERE MaGiuong = ?", e.getMaGiuong());
        if (ttGiuong == null) throw new SQLException("Giường không tồn tại.");
        if ("Hỏng".equals(ttGiuong)) throw new SQLException("Giường đang hỏng, không thể làm hợp đồng.");
        if (DongBo.dem(c, "SELECT COUNT(*) FROM HopDong WHERE MaGiuong = ? AND MaHD <> ? AND " + HOAT_DONG, e.getMaGiuong(), maHDHienTai) > 0)
            throw new SQLException("Giường này đã có hợp đồng còn hiệu lực.");
        if (DongBo.dem(c, "SELECT COUNT(*) FROM HopDong WHERE MaSV = ? AND MaHD <> ? AND " + HOAT_DONG, e.getMaSV(), maHDHienTai) > 0)
            throw new SQLException("Sinh viên này đã có hợp đồng còn hiệu lực.");
    }
}
