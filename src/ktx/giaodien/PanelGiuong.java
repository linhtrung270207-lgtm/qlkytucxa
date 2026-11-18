package ktx.giaodien;

import java.sql.SQLException;
import java.util.List;
import javax.swing.*;
import ktx.dao.GiuongDAO;
import ktx.thucthe.Giuong;


public class PanelGiuong extends PanelQuanLy<Giuong> {

    private final GiuongDAO dao = new GiuongDAO();
    private final JTextField txtMaGiuong = new JTextField(14);
    private final ComboFK cbMaPhong = new ComboFK("Phong", "MaPhong", false);
    private final JTextField txtSoThuTu = new JTextField(14);
    private final JComboBox<String> cbTrangThai = new JComboBox<>(new String[]{"Trống", "Đã thuê", "Hỏng"});

    public PanelGiuong() {
        super("Quản lý giường", new String[]{"Mã giường", "Phòng", "Số thứ tự", "Trạng thái"});
        themTruong("Mã giường *", txtMaGiuong);
        themTruong("Phòng *", cbMaPhong);
        themTruong("Số thứ tự *", txtSoThuTu);
        themTruong("Trạng thái", cbTrangThai);
        khoiTao();
    }

    @Override
    protected List<Giuong> taiDuLieu() throws SQLException {
        return dao.layTatCa();
    }

    @Override
    protected Object[] chuyenHang(Giuong e) {
        return new Object[]{e.getMaGiuong(), e.getMaPhong(), e.getSoThuTu(), e.getTrangThai()};
    }

    @Override
    protected void hienLenForm(Giuong e) {
        txtMaGiuong.setText(dinhDang(e.getMaGiuong()));
        txtMaGiuong.setEditable(false);
        cbMaPhong.chon(e.getMaPhong());
        txtSoThuTu.setText(dinhDang(e.getSoThuTu()));
        cbTrangThai.setSelectedItem(e.getTrangThai());
    }

    @Override
    protected void lamTrongForm() {
        txtMaGiuong.setText("");
        txtMaGiuong.setEditable(true);
        cbMaPhong.chon(null);
        txtSoThuTu.setText("");
        cbTrangThai.setSelectedIndex(0);
    }

    @Override
    protected Giuong docForm(boolean laThem) {
        Giuong e = new Giuong();
        e.setMaGiuong(layChuoi(txtMaGiuong, "Mã giường", true));
        e.setMaPhong(layFK(cbMaPhong, "Phòng", true));
        e.setSoThuTu(layInt(txtSoThuTu, "Số thứ tự"));
        e.setTrangThai((String) cbTrangThai.getSelectedItem());
        return e;
    }

    @Override
    protected void themBanGhi(Giuong e) throws SQLException { dao.them(e); }

    @Override
    protected void suaBanGhi(Giuong e) throws SQLException { dao.sua(e); }

    @Override
    protected void xoaBanGhi(Giuong e) throws SQLException { dao.xoa(e); }
}
