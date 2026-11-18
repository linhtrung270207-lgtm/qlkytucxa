package ktx.giaodien;

import java.sql.SQLException;
import java.util.List;
import javax.swing.*;
import ktx.dao.NhanVienDAO;
import ktx.thucthe.NhanVien;

public class PanelNhanVien extends PanelQuanLy<NhanVien> {

    private final NhanVienDAO dao = new NhanVienDAO();
    private final JTextField txtMaNV = new JTextField(14);
    private final JTextField txtHoTen = new JTextField(14);
    private final JTextField txtChucVu = new JTextField(14);
    private final JTextField txtSoDienThoai = new JTextField(14);
    private final JTextField txtEmail = new JTextField(14);
    private final JTextField txtTaiKhoan = new JTextField(14);
    private final JPasswordField txtMatKhau = new JPasswordField(14);
    private final JComboBox<String> cbTrangThai = new JComboBox<>(new String[]{"Hoạt động", "Đã khóa"});

    public PanelNhanVien() {
        super("Quản lý nhân viên", new String[]{"Mã NV", "Họ tên", "Chức vụ", "Số điện thoại", "Email", "Tài khoản", "Mật khẩu", "Trạng thái"});
        themTruong("Mã NV *", txtMaNV);
        themTruong("Họ tên *", txtHoTen);
        themTruong("Chức vụ", txtChucVu);
        themTruong("Số điện thoại", txtSoDienThoai);
        themTruong("Email", txtEmail);
        themTruong("Tài khoản *", txtTaiKhoan);
        themTruong("Mật khẩu", txtMatKhau);
        txtMatKhau.setToolTipText("Khi sửa: để trống nếu không muốn đổi mật khẩu");
        themTruong("Trạng thái", cbTrangThai);
        khoiTao();
    }

    @Override
    protected List<NhanVien> taiDuLieu() throws SQLException {
        return dao.layTatCa();
    }

    @Override
    protected Object[] chuyenHang(NhanVien e) {
        return new Object[]{e.getMaNV(), e.getHoTen(), e.getChucVu(), e.getSoDienThoai(), e.getEmail(), e.getTaiKhoan(), "******", e.isTrangThai() ? "Hoạt động" : "Đã khóa"};
    }

    @Override
    protected void hienLenForm(NhanVien e) {
        txtMaNV.setText(dinhDang(e.getMaNV()));
        txtMaNV.setEditable(false);
        txtHoTen.setText(dinhDang(e.getHoTen()));
        txtChucVu.setText(dinhDang(e.getChucVu()));
        txtSoDienThoai.setText(dinhDang(e.getSoDienThoai()));
        txtEmail.setText(dinhDang(e.getEmail()));
        txtTaiKhoan.setText(dinhDang(e.getTaiKhoan()));
        txtMatKhau.setText("");
        cbTrangThai.setSelectedIndex(e.isTrangThai() ? 0 : 1);
    }

    @Override
    protected void lamTrongForm() {
        txtMaNV.setText("");
        txtMaNV.setEditable(true);
        txtHoTen.setText("");
        txtChucVu.setText("");
        txtSoDienThoai.setText("");
        txtEmail.setText("");
        txtTaiKhoan.setText("");
        txtMatKhau.setText("");
        cbTrangThai.setSelectedIndex(0);
    }

    @Override
    protected NhanVien docForm(boolean laThem) {
        NhanVien e = new NhanVien();
        e.setMaNV(layChuoi(txtMaNV, "Mã NV", true));
        e.setHoTen(layChuoi(txtHoTen, "Họ tên", true));
        e.setChucVu(layChuoi(txtChucVu, "Chức vụ", false));
        e.setSoDienThoai(layChuoi(txtSoDienThoai, "Số điện thoại", false));
        e.setEmail(layChuoi(txtEmail, "Email", false));
        e.setTaiKhoan(layChuoi(txtTaiKhoan, "Tài khoản", true));
        e.setMatKhau(new String(txtMatKhau.getPassword()));
        e.setTrangThai(cbTrangThai.getSelectedIndex() == 0);
        return e;
    }

    @Override
    protected void themBanGhi(NhanVien e) throws SQLException { dao.them(e); }

    @Override
    protected void suaBanGhi(NhanVien e) throws SQLException { dao.sua(e); }

    @Override
    protected void xoaBanGhi(NhanVien e) throws SQLException { dao.xoa(e); }
}
