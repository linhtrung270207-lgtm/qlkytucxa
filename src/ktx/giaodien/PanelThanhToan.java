package ktx.giaodien;

import java.sql.SQLException;
import java.util.List;
import javax.swing.*;
import ktx.dao.ThanhToanDAO;
import ktx.thucthe.ThanhToan;
public class PanelThanhToan extends PanelQuanLy<ThanhToan> {

    private final ThanhToanDAO dao = new ThanhToanDAO();
    private final JTextField txtMaTT = new JTextField(14);
    private final ComboFK cbMaHoaDon = new ComboFK("HoaDon", "MaHD", false);
    private final ComboFK cbMaSV = new ComboFK("SinhVien", "MaSV", true);
    private final ComboFK cbMaNVThu = new ComboFK("NhanVien", "MaNV", true);
    private final JTextField txtNgayThanhToan = new JTextField(14);
    private final JTextField txtSoTienNop = new JTextField(14);
    private final JComboBox<String> cbPhuongThuc = new JComboBox<>(new String[]{"Tiền mặt", "Chuyển khoản", "Quẹt thẻ"});
    private final JTextField txtGhiChu = new JTextField(14);

    public PanelThanhToan() {
        super("Quản lý thanh toán", new String[]{"Mã TT", "Hóa đơn", "Sinh viên nộp", "NV thu", "Ngày thanh toán (yyyy-MM-dd HH:mm:ss)", "Số tiền nộp", "Phương thức", "Ghi chú"});
        themTruong("Mã TT", txtMaTT);
        txtMaTT.setEditable(false);
        themTruong("Hóa đơn *", cbMaHoaDon);
        themTruong("Sinh viên nộp", cbMaSV);
        themTruong("NV thu", cbMaNVThu);
        themTruong("Ngày thanh toán (yyyy-MM-dd HH:mm:ss)", txtNgayThanhToan);
        themTruong("Số tiền nộp *", txtSoTienNop);
        themTruong("Phương thức", cbPhuongThuc);
        themTruong("Ghi chú", txtGhiChu);
        khoiTao();
    }

    @Override
    protected List<ThanhToan> taiDuLieu() throws SQLException {
        return dao.layTatCa();
    }

    @Override
    protected Object[] chuyenHang(ThanhToan e) {
        return new Object[]{e.getMaTT(), e.getMaHoaDon(), e.getMaSV(), e.getMaNVThu(), dinhDang(e.getNgayThanhToan()), dinhDang(e.getSoTienNop()), e.getPhuongThuc(), e.getGhiChu()};
    }

    @Override
    protected void hienLenForm(ThanhToan e) {
        txtMaTT.setText(dinhDang(e.getMaTT()));
        cbMaHoaDon.chon(String.valueOf(e.getMaHoaDon()));
        cbMaSV.chon(e.getMaSV());
        cbMaNVThu.chon(e.getMaNVThu());
        txtNgayThanhToan.setText(dinhDang(e.getNgayThanhToan()));
        txtSoTienNop.setText(dinhDang(e.getSoTienNop()));
        cbPhuongThuc.setSelectedItem(e.getPhuongThuc());
        txtGhiChu.setText(dinhDang(e.getGhiChu()));
    }

    @Override
    protected void lamTrongForm() {
        txtMaTT.setText("");
        cbMaHoaDon.chon(null);
        cbMaSV.chon(null);
        cbMaNVThu.chonNhanVienHienTai();
        txtNgayThanhToan.setText("");
        txtSoTienNop.setText("");
        cbPhuongThuc.setSelectedIndex(0);
        txtGhiChu.setText("");
    }

    @Override
    protected ThanhToan docForm(boolean laThem) {
        ThanhToan e = new ThanhToan();
        e.setMaTT(laThem ? 0 : layInt(txtMaTT, "Mã TT"));
        e.setMaHoaDon(layIntFK(cbMaHoaDon, "Hóa đơn"));
        e.setMaSV(layFK(cbMaSV, "Sinh viên nộp", false));
        e.setMaNVThu(layFK(cbMaNVThu, "NV thu", false));
        e.setNgayThanhToan(layGio(txtNgayThanhToan, "Ngày thanh toán (yyyy-MM-dd HH:mm:ss)", false));
        e.setSoTienNop(layTien(txtSoTienNop, "Số tiền nộp", true, null));
        e.setPhuongThuc((String) cbPhuongThuc.getSelectedItem());
        e.setGhiChu(layChuoi(txtGhiChu, "Ghi chú", false));
        return e;
    }

    @Override
    protected void themBanGhi(ThanhToan e) throws SQLException { dao.them(e); }

    @Override
    protected void suaBanGhi(ThanhToan e) throws SQLException { dao.sua(e); }

    @Override
    protected void xoaBanGhi(ThanhToan e) throws SQLException { dao.xoa(e); }
}
