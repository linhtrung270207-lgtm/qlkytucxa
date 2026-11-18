package ktx.dao;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import ktx.thucthe.NhanVien;
import ktx.tienich.*;
public class NhanVienDAO {

    private NhanVien anh(ResultSet rs) throws SQLException {
        NhanVien e = new NhanVien();
        e.setMaNV(rs.getString("MaNV"));
        e.setHoTen(rs.getString("HoTen"));
        e.setChucVu(rs.getString("ChucVu"));
        e.setSoDienThoai(rs.getString("SoDienThoai"));
        e.setEmail(rs.getString("Email"));
        e.setTaiKhoan(rs.getString("TaiKhoan"));
        e.setMatKhau(rs.getString("MatKhau"));
        e.setTrangThai(rs.getBoolean("TrangThai"));
        return e;
    }

    public List<NhanVien> layTatCa() throws SQLException {
        List<NhanVien> ds = new ArrayList<>();
        String sql = "SELECT * FROM NhanVien ORDER BY MaNV";
        try (Connection c = KetNoi.layKetNoi(); Statement st = c.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) ds.add(anh(rs));
        }
        return ds;
    }

    public void them(NhanVien e) throws SQLException {
        try (Connection c = KetNoi.layKetNoi()) {
            c.setAutoCommit(false);
            try {
                if (e.getMatKhau() == null || e.getMatKhau().isEmpty()) throw new SQLException("Chưa nhập mật khẩu cho nhân viên mới.");
                e.setMatKhau(BaoMat.bam(e.getMatKhau()));

                String sql = "INSERT INTO NhanVien (MaNV, HoTen, ChucVu, SoDienThoai, Email, TaiKhoan, MatKhau, TrangThai) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                try (PreparedStatement ps = c.prepareStatement(sql)) {
                    int i = 1;
                KetNoi.gan(ps, i++, e.getMaNV(), Types.NVARCHAR);
                KetNoi.gan(ps, i++, e.getHoTen(), Types.NVARCHAR);
                KetNoi.gan(ps, i++, e.getChucVu(), Types.NVARCHAR);
                KetNoi.gan(ps, i++, e.getSoDienThoai(), Types.NVARCHAR);
                KetNoi.gan(ps, i++, e.getEmail(), Types.NVARCHAR);
                KetNoi.gan(ps, i++, e.getTaiKhoan(), Types.NVARCHAR);
                KetNoi.gan(ps, i++, e.getMatKhau(), Types.NVARCHAR);
                KetNoi.gan(ps, i++, e.isTrangThai(), Types.BIT);
                    ps.executeUpdate();
                }

                c.commit();
            } catch (SQLException | RuntimeException ex) {
                c.rollback();
                throw ex;
            }
        }
    }

    public void sua(NhanVien e) throws SQLException {
        try (Connection c = KetNoi.layKetNoi()) {
            c.setAutoCommit(false);
            try {
                if (e.getMatKhau() == null || e.getMatKhau().isEmpty())
                    e.setMatKhau(DongBo.layChuoi(c, "SELECT MatKhau FROM NhanVien WHERE MaNV = ?", e.getMaNV()));
                else
                    e.setMatKhau(BaoMat.bam(e.getMatKhau()));

                String sql = "UPDATE NhanVien SET HoTen = ?, ChucVu = ?, SoDienThoai = ?, Email = ?, TaiKhoan = ?, MatKhau = ?, TrangThai = ? WHERE MaNV = ?";
                try (PreparedStatement ps = c.prepareStatement(sql)) {
                    int i = 1;
                KetNoi.gan(ps, i++, e.getHoTen(), Types.NVARCHAR);
                KetNoi.gan(ps, i++, e.getChucVu(), Types.NVARCHAR);
                KetNoi.gan(ps, i++, e.getSoDienThoai(), Types.NVARCHAR);
                KetNoi.gan(ps, i++, e.getEmail(), Types.NVARCHAR);
                KetNoi.gan(ps, i++, e.getTaiKhoan(), Types.NVARCHAR);
                KetNoi.gan(ps, i++, e.getMatKhau(), Types.NVARCHAR);
                KetNoi.gan(ps, i++, e.isTrangThai(), Types.BIT);
                    KetNoi.gan(ps, i++, e.getMaNV(), Types.NVARCHAR);
                    ps.executeUpdate();
                }

                c.commit();
            } catch (SQLException | RuntimeException ex) {
                c.rollback();
                throw ex;
            }
        }
    }

    public void xoa(NhanVien e) throws SQLException {
        try (Connection c = KetNoi.layKetNoi()) {
            c.setAutoCommit(false);
            try {
                try (PreparedStatement ps = c.prepareStatement("DELETE FROM NhanVien WHERE MaNV = ?")) {
                    KetNoi.gan(ps, 1, e.getMaNV(), Types.NVARCHAR);
                    ps.executeUpdate();
                }

                c.commit();
            } catch (SQLException | RuntimeException ex) {
                c.rollback();
                throw ex;
            }
        }
    }


    public NhanVien dangNhap(String taiKhoan, String matKhau) throws SQLException {
        String sql = "SELECT * FROM NhanVien WHERE TaiKhoan = ? AND MatKhau = ? AND TrangThai = 1";
        try (Connection c = KetNoi.layKetNoi(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, taiKhoan);
            ps.setString(2, BaoMat.bam(matKhau));
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? anh(rs) : null;
            }
        }
    }
}
