package ktx.giaodien;

import java.sql.SQLException;
import java.util.List;
import javax.swing.*;
import ktx.dao.SinhVienDAO;
import ktx.thucthe.SinhVien;

public class PanelSinhVien extends PanelQuanLy<SinhVien> {

    private final SinhVienDAO dao = new SinhVienDAO();
    private final JTextField txtMaSV = new JTextField(14);
    private final JTextField txtHoTen = new JTextField(14);
    private final JTextField txtNgaySinh = new JTextField(14);
    private final JComboBox<String> cbGioiTinh = new JComboBox<>(new String[]{"Nam", "Nữ"});
    private final JTextField txtCCCD = new JTextField(14);
    private final JTextField txtSoDienThoai = new JTextField(14);
    private final JTextField txtEmail = new JTextField(14);
    private final JTextField txtTruongHoc = new JTextField(14);
    private final JTextField txtQueQuan = new JTextField(14);
    private final JTextField txtHoTenNguoiThan = new JTextField(14);
    private final JTextField txtSDTNguoiThan = new JTextField(14);
    private final JComboBox<String> cbTrangThai = new JComboBox<>(new String[]{"Chờ duyệt", "Đang lưu trú", "Đã rời đi", "Bị đuổi"});

    public PanelSinhVien() {
        super("Quản lý sinh viên", new String[]{"Mã SV", "Họ tên", "Ngày sinh (yyyy-MM-dd)", "Giới tính", "CCCD", "Số điện thoại", "Email", "Trường học", "Quê quán", "Họ tên người thân", "SĐT người thân", "Trạng thái"});
        themTruong("Mã SV *", txtMaSV);
        themTruong("Họ tên *", txtHoTen);
        themTruong("Ngày sinh (yyyy-MM-dd)", txtNgaySinh);
        themTruong("Giới tính", cbGioiTinh);
        themTruong("CCCD *", txtCCCD);
        themTruong("Số điện thoại", txtSoDienThoai);
        themTruong("Email", txtEmail);
        themTruong("Trường học", txtTruongHoc);
        themTruong("Quê quán", txtQueQuan);
        themTruong("Họ tên người thân", txtHoTenNguoiThan);
        themTruong("SĐT người thân", txtSDTNguoiThan);
        themTruong("Trạng thái", cbTrangThai);
        khoiTao();
    }

    @Override
    protected List<SinhVien> taiDuLieu() throws SQLException {
        return dao.layTatCa();
    }

    @Override
    protected Object[] chuyenHang(SinhVien e) {
        return new Object[]{e.getMaSV(), e.getHoTen(), dinhDang(e.getNgaySinh()), e.getGioiTinh(), e.getCCCD(), e.getSoDienThoai(), e.getEmail(), e.getTruongHoc(), e.getQueQuan(), e.getHoTenNguoiThan(), e.getSDTNguoiThan(), e.getTrangThai()};
    }

    @Override
    protected void hienLenForm(SinhVien e) {
        txtMaSV.setText(dinhDang(e.getMaSV()));
        txtMaSV.setEditable(false);
        txtHoTen.setText(dinhDang(e.getHoTen()));
        txtNgaySinh.setText(dinhDang(e.getNgaySinh()));
        cbGioiTinh.setSelectedItem(e.getGioiTinh());
        txtCCCD.setText(dinhDang(e.getCCCD()));
        txtSoDienThoai.setText(dinhDang(e.getSoDienThoai()));
        txtEmail.setText(dinhDang(e.getEmail()));
        txtTruongHoc.setText(dinhDang(e.getTruongHoc()));
        txtQueQuan.setText(dinhDang(e.getQueQuan()));
        txtHoTenNguoiThan.setText(dinhDang(e.getHoTenNguoiThan()));
        txtSDTNguoiThan.setText(dinhDang(e.getSDTNguoiThan()));
        cbTrangThai.setSelectedItem(e.getTrangThai());
    }

    @Override
    protected void lamTrongForm() {
        txtMaSV.setText("");
        txtMaSV.setEditable(true);
        txtHoTen.setText("");
        txtNgaySinh.setText("");
        cbGioiTinh.setSelectedIndex(0);
        txtCCCD.setText("");
        txtSoDienThoai.setText("");
        txtEmail.setText("");
        txtTruongHoc.setText("");
        txtQueQuan.setText("");
        txtHoTenNguoiThan.setText("");
        txtSDTNguoiThan.setText("");
        cbTrangThai.setSelectedIndex(0);
    }

    @Override
    protected SinhVien docForm(boolean laThem) {
        SinhVien e = new SinhVien();
        e.setMaSV(layChuoi(txtMaSV, "Mã SV", true));
        e.setHoTen(layChuoi(txtHoTen, "Họ tên", true));
        e.setNgaySinh(layNgay(txtNgaySinh, "Ngày sinh (yyyy-MM-dd)", false));
        e.setGioiTinh((String) cbGioiTinh.getSelectedItem());
        e.setCCCD(layChuoi(txtCCCD, "CCCD", true));
        e.setSoDienThoai(layChuoi(txtSoDienThoai, "Số điện thoại", false));
        e.setEmail(layChuoi(txtEmail, "Email", false));
        e.setTruongHoc(layChuoi(txtTruongHoc, "Trường học", false));
        e.setQueQuan(layChuoi(txtQueQuan, "Quê quán", false));
        e.setHoTenNguoiThan(layChuoi(txtHoTenNguoiThan, "Họ tên người thân", false));
        e.setSDTNguoiThan(layChuoi(txtSDTNguoiThan, "SĐT người thân", false));
        e.setTrangThai((String) cbTrangThai.getSelectedItem());
        return e;
    }

    @Override
    protected void themBanGhi(SinhVien e) throws SQLException { dao.them(e); }

    @Override
    protected void suaBanGhi(SinhVien e) throws SQLException { dao.sua(e); }

    @Override
    protected void xoaBanGhi(SinhVien e) throws SQLException { dao.xoa(e); }
}
