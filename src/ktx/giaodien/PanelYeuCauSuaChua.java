package ktx.giaodien;

import java.sql.SQLException;
import java.util.List;
import javax.swing.*;
import ktx.dao.YeuCauSuaChuaDAO;
import ktx.thucthe.YeuCauSuaChua;
public class PanelYeuCauSuaChua extends PanelQuanLy<YeuCauSuaChua> {

    private final YeuCauSuaChuaDAO dao = new YeuCauSuaChuaDAO();
    private final JTextField txtMaYC = new JTextField(14);
    private final ComboFK cbMaPhong = new ComboFK("Phong", "MaPhong", false);
    private final ComboFK cbMaSV = new ComboFK("SinhVien", "MaSV", true);
    private final JTextField txtThietBiHong = new JTextField(14);
    private final JTextField txtMoTaChiTiet = new JTextField(14);
    private final JTextField txtNgayBao = new JTextField(14);
    private final JComboBox<String> cbTrangThai = new JComboBox<>(new String[]{"Chờ tiếp nhận", "Đang xử lý", "Đã hoàn thành", "Từ chối"});
    private final JTextField txtNgayHoanThanh = new JTextField(14);
    private final JTextField txtChiPhiSuaChua = new JTextField(14);
    private final JTextField txtGhiChu = new JTextField(14);

    public PanelYeuCauSuaChua() {
        super("Quản lý yêu cầu sửa chữa", new String[]{"Mã YC", "Phòng", "Sinh viên báo", "Thiết bị hỏng", "Mô tả chi tiết", "Ngày báo (yyyy-MM-dd HH:mm:ss)", "Trạng thái", "Ngày hoàn thành (yyyy-MM-dd HH:mm:ss)", "Chi phí sửa chữa", "Ghi chú"});
        themTruong("Mã YC", txtMaYC);
        txtMaYC.setEditable(false);
        themTruong("Phòng *", cbMaPhong);
        themTruong("Sinh viên báo", cbMaSV);
        themTruong("Thiết bị hỏng *", txtThietBiHong);
        themTruong("Mô tả chi tiết", txtMoTaChiTiet);
        themTruong("Ngày báo (yyyy-MM-dd HH:mm:ss)", txtNgayBao);
        themTruong("Trạng thái", cbTrangThai);
        themTruong("Ngày hoàn thành (yyyy-MM-dd HH:mm:ss)", txtNgayHoanThanh);
        themTruong("Chi phí sửa chữa", txtChiPhiSuaChua);
        themTruong("Ghi chú", txtGhiChu);
        khoiTao();
    }

    @Override
    protected List<YeuCauSuaChua> taiDuLieu() throws SQLException {
        return dao.layTatCa();
    }

    @Override
    protected Object[] chuyenHang(YeuCauSuaChua e) {
        return new Object[]{e.getMaYC(), e.getMaPhong(), e.getMaSV(), e.getThietBiHong(), e.getMoTaChiTiet(), dinhDang(e.getNgayBao()), e.getTrangThai(), dinhDang(e.getNgayHoanThanh()), dinhDang(e.getChiPhiSuaChua()), e.getGhiChu()};
    }

    @Override
    protected void hienLenForm(YeuCauSuaChua e) {
        txtMaYC.setText(dinhDang(e.getMaYC()));
        cbMaPhong.chon(e.getMaPhong());
        cbMaSV.chon(e.getMaSV());
        txtThietBiHong.setText(dinhDang(e.getThietBiHong()));
        txtMoTaChiTiet.setText(dinhDang(e.getMoTaChiTiet()));
        txtNgayBao.setText(dinhDang(e.getNgayBao()));
        cbTrangThai.setSelectedItem(e.getTrangThai());
        txtNgayHoanThanh.setText(dinhDang(e.getNgayHoanThanh()));
        txtChiPhiSuaChua.setText(dinhDang(e.getChiPhiSuaChua()));
        txtGhiChu.setText(dinhDang(e.getGhiChu()));
    }

    @Override
    protected void lamTrongForm() {
        txtMaYC.setText("");
        cbMaPhong.chon(null);
        cbMaSV.chon(null);
        txtThietBiHong.setText("");
        txtMoTaChiTiet.setText("");
        txtNgayBao.setText("");
        cbTrangThai.setSelectedIndex(0);
        txtNgayHoanThanh.setText("");
        txtChiPhiSuaChua.setText("0");
        txtGhiChu.setText("");
    }

    @Override
    protected YeuCauSuaChua docForm(boolean laThem) {
        YeuCauSuaChua e = new YeuCauSuaChua();
        e.setMaYC(laThem ? 0 : layInt(txtMaYC, "Mã YC"));
        e.setMaPhong(layFK(cbMaPhong, "Phòng", true));
        e.setMaSV(layFK(cbMaSV, "Sinh viên báo", false));
        e.setThietBiHong(layChuoi(txtThietBiHong, "Thiết bị hỏng", true));
        e.setMoTaChiTiet(layChuoi(txtMoTaChiTiet, "Mô tả chi tiết", false));
        e.setNgayBao(layGio(txtNgayBao, "Ngày báo (yyyy-MM-dd HH:mm:ss)", false));
        e.setTrangThai((String) cbTrangThai.getSelectedItem());
        e.setNgayHoanThanh(layGio(txtNgayHoanThanh, "Ngày hoàn thành (yyyy-MM-dd HH:mm:ss)", false));
        e.setChiPhiSuaChua(layTien(txtChiPhiSuaChua, "Chi phí sửa chữa", false, "0"));
        e.setGhiChu(layChuoi(txtGhiChu, "Ghi chú", false));
        return e;
    }

    @Override
    protected void themBanGhi(YeuCauSuaChua e) throws SQLException { dao.them(e); }

    @Override
    protected void suaBanGhi(YeuCauSuaChua e) throws SQLException { dao.sua(e); }

    @Override
    protected void xoaBanGhi(YeuCauSuaChua e) throws SQLException { dao.xoa(e); }
}
