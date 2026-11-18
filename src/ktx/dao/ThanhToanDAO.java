package ktx.dao;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import ktx.thucthe.ThanhToan;
import ktx.tienich.*;
public class ThanhToanDAO {

    private ThanhToan anh(ResultSet rs) throws SQLException {
        ThanhToan e = new ThanhToan();
        e.setMaTT(rs.getInt("MaTT"));
        e.setMaHoaDon(rs.getInt("MaHoaDon"));
        e.setMaSV(rs.getString("MaSV"));
        e.setMaNVThu(rs.getString("MaNVThu"));
        e.setNgayThanhToan(rs.getTimestamp("NgayThanhToan"));
        e.setSoTienNop(rs.getBigDecimal("SoTienNop"));
        e.setPhuongThuc(rs.getString("PhuongThuc"));
        e.setGhiChu(rs.getString("GhiChu"));
        return e;
    }

    public List<ThanhToan> layTatCa() throws SQLException {
        List<ThanhToan> ds = new ArrayList<>();
        String sql = "SELECT * FROM ThanhToan ORDER BY MaTT";
        try (Connection c = KetNoi.layKetNoi(); Statement st = c.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) ds.add(anh(rs));
        }
        return ds;
    }

    public void them(ThanhToan e) throws SQLException {
        try (Connection c = KetNoi.layKetNoi()) {
            c.setAutoCommit(false);
            try {
                if (e.getNgayThanhToan() == null) e.setNgayThanhToan(new Timestamp(System.currentTimeMillis()));
                kiemTraSoTien(c, e, 0);

                String sql = "INSERT INTO ThanhToan (MaHoaDon, MaSV, MaNVThu, NgayThanhToan, SoTienNop, PhuongThuc, GhiChu) VALUES (?, ?, ?, ?, ?, ?, ?)";
                try (PreparedStatement ps = c.prepareStatement(sql)) {
                    int i = 1;
                KetNoi.gan(ps, i++, e.getMaHoaDon(), Types.INTEGER);
                KetNoi.gan(ps, i++, e.getMaSV(), Types.NVARCHAR);
                KetNoi.gan(ps, i++, e.getMaNVThu(), Types.NVARCHAR);
                KetNoi.gan(ps, i++, e.getNgayThanhToan(), Types.TIMESTAMP);
                KetNoi.gan(ps, i++, e.getSoTienNop(), Types.DECIMAL);
                KetNoi.gan(ps, i++, e.getPhuongThuc(), Types.NVARCHAR);
                KetNoi.gan(ps, i++, e.getGhiChu(), Types.NVARCHAR);
                    ps.executeUpdate();
                }
                DongBo.capNhatTrangThaiHoaDon(c, e.getMaHoaDon());

                c.commit();
            } catch (SQLException | RuntimeException ex) {
                c.rollback();
                throw ex;
            }
        }
    }

    public void sua(ThanhToan e) throws SQLException {
        try (Connection c = KetNoi.layKetNoi()) {
            c.setAutoCommit(false);
            try {
                if (e.getNgayThanhToan() == null) e.setNgayThanhToan(new Timestamp(System.currentTimeMillis()));
                kiemTraSoTien(c, e, e.getMaTT());
                int hdCu = DongBo.layInt(c, "SELECT MaHoaDon FROM ThanhToan WHERE MaTT = ?", e.getMaTT());

                String sql = "UPDATE ThanhToan SET MaHoaDon = ?, MaSV = ?, MaNVThu = ?, NgayThanhToan = ?, SoTienNop = ?, PhuongThuc = ?, GhiChu = ? WHERE MaTT = ?";
                try (PreparedStatement ps = c.prepareStatement(sql)) {
                    int i = 1;
                KetNoi.gan(ps, i++, e.getMaHoaDon(), Types.INTEGER);
                KetNoi.gan(ps, i++, e.getMaSV(), Types.NVARCHAR);
                KetNoi.gan(ps, i++, e.getMaNVThu(), Types.NVARCHAR);
                KetNoi.gan(ps, i++, e.getNgayThanhToan(), Types.TIMESTAMP);
                KetNoi.gan(ps, i++, e.getSoTienNop(), Types.DECIMAL);
                KetNoi.gan(ps, i++, e.getPhuongThuc(), Types.NVARCHAR);
                KetNoi.gan(ps, i++, e.getGhiChu(), Types.NVARCHAR);
                    KetNoi.gan(ps, i++, e.getMaTT(), Types.INTEGER);
                    ps.executeUpdate();
                }
                DongBo.capNhatTrangThaiHoaDon(c, hdCu);
                DongBo.capNhatTrangThaiHoaDon(c, e.getMaHoaDon());

                c.commit();
            } catch (SQLException | RuntimeException ex) {
                c.rollback();
                throw ex;
            }
        }
    }

    public void xoa(ThanhToan e) throws SQLException {
        try (Connection c = KetNoi.layKetNoi()) {
            c.setAutoCommit(false);
            try {
                try (PreparedStatement ps = c.prepareStatement("DELETE FROM ThanhToan WHERE MaTT = ?")) {
                    KetNoi.gan(ps, 1, e.getMaTT(), Types.INTEGER);
                    ps.executeUpdate();
                }
                DongBo.capNhatTrangThaiHoaDon(c, e.getMaHoaDon());

                c.commit();
            } catch (SQLException | RuntimeException ex) {
                c.rollback();
                throw ex;
            }
        }
    }


    private void kiemTraSoTien(Connection c, ThanhToan e, int maTTHienTai) throws SQLException {
        if (e.getSoTienNop() == null || e.getSoTienNop().signum() <= 0)
            throw new SQLException("Số tiền nộp phải lớn hơn 0.");
        BigDecimal tong = DongBo.layTien(c, "SELECT TongTien FROM HoaDon WHERE MaHD = ?", e.getMaHoaDon());
        BigDecimal da = DongBo.layTien(c, "SELECT ISNULL(SUM(SoTienNop), 0) FROM ThanhToan WHERE MaHoaDon = ? AND MaTT <> ?", e.getMaHoaDon(), maTTHienTai);
        if (da.add(e.getSoTienNop()).compareTo(tong) > 0)
            throw new SQLException("Số tiền nộp vượt quá số tiền còn phải thanh toán của hóa đơn (tổng " + tong.toPlainString() + ", đã nộp " + da.toPlainString() + ").");
    }
}
