package ktx.giaodien;

import java.sql.SQLException;
import java.util.List;
import javax.swing.*;
import ktx.dao.PhongDAO;
import ktx.thucthe.Phong;

public class PanelPhong extends PanelQuanLy<Phong> {

    private final PhongDAO dao = new PhongDAO();
    private final JTextField txtMaPhong = new JTextField(14);
    private final ComboFK cbMaKhu = new ComboFK("KhuNha", "MaKhu", false);
    private final ComboFK cbMaLoai = new ComboFK("LoaiPhong", "MaLoai", false);
    private final JTextField txtTenPhong = new JTextField(14);
    private final JTextField txtSoNguoiHienTai = new JTextField(14);
    private final JComboBox<String> cbTrangThai = new JComboBox<>(new String[]{"Bình thường", "Đang sửa chữa", "Tạm ngưng sử dụng"});

    public PanelPhong() {
        super("Quản lý phòng", new String[]{"Mã phòng", "Khu nhà", "Loại phòng", "Tên phòng", "Số người hiện tại", "Trạng thái"});
        themTruong("Mã phòng *", txtMaPhong);
        themTruong("Khu nhà *", cbMaKhu);
        themTruong("Loại phòng *", cbMaLoai);
        themTruong("Tên phòng *", txtTenPhong);
        themTruong("Số người hiện tại", txtSoNguoiHienTai);
        txtSoNguoiHienTai.setEditable(false);
        themTruong("Trạng thái", cbTrangThai);
        khoiTao();
    }

    @Override
    protected List<Phong> taiDuLieu() throws SQLException {
        return dao.layTatCa();
    }

    @Override
    protected Object[] chuyenHang(Phong e) {
        return new Object[]{e.getMaPhong(), e.getMaKhu(), e.getMaLoai(), e.getTenPhong(), e.getSoNguoiHienTai(), e.getTrangThai()};
    }

    @Override
    protected void hienLenForm(Phong e) {
        txtMaPhong.setText(dinhDang(e.getMaPhong()));
        txtMaPhong.setEditable(false);
        cbMaKhu.chon(e.getMaKhu());
        cbMaLoai.chon(e.getMaLoai());
        txtTenPhong.setText(dinhDang(e.getTenPhong()));
        txtSoNguoiHienTai.setText(dinhDang(e.getSoNguoiHienTai()));
        cbTrangThai.setSelectedItem(e.getTrangThai());
    }

    @Override
    protected void lamTrongForm() {
        txtMaPhong.setText("");
        txtMaPhong.setEditable(true);
        cbMaKhu.chon(null);
        cbMaLoai.chon(null);
        txtTenPhong.setText("");
        txtSoNguoiHienTai.setText("0");
        cbTrangThai.setSelectedIndex(0);
    }

    @Override
    protected Phong docForm(boolean laThem) {
        Phong e = new Phong();
        e.setMaPhong(layChuoi(txtMaPhong, "Mã phòng", true));
        e.setMaKhu(layFK(cbMaKhu, "Khu nhà", true));
        e.setMaLoai(layFK(cbMaLoai, "Loại phòng", true));
        e.setTenPhong(layChuoi(txtTenPhong, "Tên phòng", true));
        e.setSoNguoiHienTai(layInt(txtSoNguoiHienTai, "Số người hiện tại"));
        e.setTrangThai((String) cbTrangThai.getSelectedItem());
        return e;
    }

    @Override
    protected void themBanGhi(Phong e) throws SQLException { dao.them(e); }

    @Override
    protected void suaBanGhi(Phong e) throws SQLException { dao.sua(e); }

    @Override
    protected void xoaBanGhi(Phong e) throws SQLException { dao.xoa(e); }
}
