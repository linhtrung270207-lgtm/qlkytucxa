package ktx.giaodien;

import java.sql.SQLException;
import java.util.List;
import javax.swing.*;
import ktx.dao.KhuNhaDAO;
import ktx.thucthe.KhuNha;
public class PanelKhuNha extends PanelQuanLy<KhuNha> {

    private final KhuNhaDAO dao = new KhuNhaDAO();
    private final JTextField txtMaKhu = new JTextField(14);
    private final JTextField txtTenKhu = new JTextField(14);
    private final JComboBox<String> cbLoaiKhu = new JComboBox<>(new String[]{"Nam", "Nữ", "Hỗn hợp"});
    private final JTextField txtGhiChu = new JTextField(14);

    public PanelKhuNha() {
        super("Quản lý khu nhà", new String[]{"Mã khu", "Tên khu", "Loại khu", "Ghi chú"});
        themTruong("Mã khu *", txtMaKhu);
        themTruong("Tên khu *", txtTenKhu);
        themTruong("Loại khu *", cbLoaiKhu);
        themTruong("Ghi chú", txtGhiChu);
        khoiTao();
    }

    @Override
    protected List<KhuNha> taiDuLieu() throws SQLException {
        return dao.layTatCa();
    }

    @Override
    protected Object[] chuyenHang(KhuNha e) {
        return new Object[]{e.getMaKhu(), e.getTenKhu(), e.getLoaiKhu(), e.getGhiChu()};
    }

    @Override
    protected void hienLenForm(KhuNha e) {
        txtMaKhu.setText(dinhDang(e.getMaKhu()));
        txtMaKhu.setEditable(false);
        txtTenKhu.setText(dinhDang(e.getTenKhu()));
        cbLoaiKhu.setSelectedItem(e.getLoaiKhu());
        txtGhiChu.setText(dinhDang(e.getGhiChu()));
    }

    @Override
    protected void lamTrongForm() {
        txtMaKhu.setText("");
        txtMaKhu.setEditable(true);
        txtTenKhu.setText("");
        cbLoaiKhu.setSelectedIndex(0);
        txtGhiChu.setText("");
    }

    @Override
    protected KhuNha docForm(boolean laThem) {
        KhuNha e = new KhuNha();
        e.setMaKhu(layChuoi(txtMaKhu, "Mã khu", true));
        e.setTenKhu(layChuoi(txtTenKhu, "Tên khu", true));
        e.setLoaiKhu((String) cbLoaiKhu.getSelectedItem());
        e.setGhiChu(layChuoi(txtGhiChu, "Ghi chú", false));
        return e;
    }

    @Override
    protected void themBanGhi(KhuNha e) throws SQLException { dao.them(e); }

    @Override
    protected void suaBanGhi(KhuNha e) throws SQLException { dao.sua(e); }

    @Override
    protected void xoaBanGhi(KhuNha e) throws SQLException { dao.xoa(e); }
}
