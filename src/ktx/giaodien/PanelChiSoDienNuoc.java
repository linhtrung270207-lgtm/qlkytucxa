package ktx.giaodien;

import java.sql.SQLException;
import java.util.List;
import javax.swing.*;
import ktx.dao.ChiSoDienNuocDAO;
import ktx.thucthe.ChiSoDienNuoc;
public class PanelChiSoDienNuoc extends PanelQuanLy<ChiSoDienNuoc> {

    private final ChiSoDienNuocDAO dao = new ChiSoDienNuocDAO();
    private final JTextField txtMaCS = new JTextField(14);
    private final ComboFK cbMaPhong = new ComboFK("Phong", "MaPhong", false);
    private final JTextField txtThang = new JTextField(14);
    private final JTextField txtNam = new JTextField(14);
    private final JTextField txtSoDienCu = new JTextField(14);
    private final JTextField txtSoDienMoi = new JTextField(14);
    private final JTextField txtSoNuocCu = new JTextField(14);
    private final JTextField txtSoNuocMoi = new JTextField(14);
    private final ComboFK cbMaNVGhi = new ComboFK("NhanVien", "MaNV", true);
    private final JTextField txtNgayGhi = new JTextField(14);

    public PanelChiSoDienNuoc() {
        super("Quản lý chỉ số điện nước", new String[]{"Mã CS", "Phòng", "Tháng (1-12)", "Năm", "Số điện cũ", "Số điện mới", "Số nước cũ", "Số nước mới", "NV ghi", "Ngày ghi (yyyy-MM-dd)"});
        themTruong("Mã CS", txtMaCS);
        txtMaCS.setEditable(false);
        themTruong("Phòng *", cbMaPhong);
        themTruong("Tháng (1-12) *", txtThang);
        themTruong("Năm *", txtNam);
        themTruong("Số điện cũ *", txtSoDienCu);
        themTruong("Số điện mới *", txtSoDienMoi);
        themTruong("Số nước cũ *", txtSoNuocCu);
        themTruong("Số nước mới *", txtSoNuocMoi);
        themTruong("NV ghi", cbMaNVGhi);
        themTruong("Ngày ghi (yyyy-MM-dd)", txtNgayGhi);
        khoiTao();
    }

    @Override
    protected List<ChiSoDienNuoc> taiDuLieu() throws SQLException {
        return dao.layTatCa();
    }

    @Override
    protected Object[] chuyenHang(ChiSoDienNuoc e) {
        return new Object[]{e.getMaCS(), e.getMaPhong(), e.getThang(), e.getNam(), e.getSoDienCu(), e.getSoDienMoi(), e.getSoNuocCu(), e.getSoNuocMoi(), e.getMaNVGhi(), dinhDang(e.getNgayGhi())};
    }

    @Override
    protected void hienLenForm(ChiSoDienNuoc e) {
        txtMaCS.setText(dinhDang(e.getMaCS()));
        cbMaPhong.chon(e.getMaPhong());
        txtThang.setText(dinhDang(e.getThang()));
        txtNam.setText(dinhDang(e.getNam()));
        txtSoDienCu.setText(dinhDang(e.getSoDienCu()));
        txtSoDienMoi.setText(dinhDang(e.getSoDienMoi()));
        txtSoNuocCu.setText(dinhDang(e.getSoNuocCu()));
        txtSoNuocMoi.setText(dinhDang(e.getSoNuocMoi()));
        cbMaNVGhi.chon(e.getMaNVGhi());
        txtNgayGhi.setText(dinhDang(e.getNgayGhi()));
    }

    @Override
    protected void lamTrongForm() {
        txtMaCS.setText("");
        cbMaPhong.chon(null);
        txtThang.setText("");
        txtNam.setText("");
        txtSoDienCu.setText("");
        txtSoDienMoi.setText("");
        txtSoNuocCu.setText("");
        txtSoNuocMoi.setText("");
        cbMaNVGhi.chonNhanVienHienTai();
        txtNgayGhi.setText("");
    }

    @Override
    protected ChiSoDienNuoc docForm(boolean laThem) {
        ChiSoDienNuoc e = new ChiSoDienNuoc();
        e.setMaCS(laThem ? 0 : layInt(txtMaCS, "Mã CS"));
        e.setMaPhong(layFK(cbMaPhong, "Phòng", true));
        e.setThang(layInt(txtThang, "Tháng (1-12)"));
        e.setNam(layInt(txtNam, "Năm"));
        e.setSoDienCu(layInt(txtSoDienCu, "Số điện cũ"));
        e.setSoDienMoi(layInt(txtSoDienMoi, "Số điện mới"));
        e.setSoNuocCu(layInt(txtSoNuocCu, "Số nước cũ"));
        e.setSoNuocMoi(layInt(txtSoNuocMoi, "Số nước mới"));
        e.setMaNVGhi(layFK(cbMaNVGhi, "NV ghi", false));
        e.setNgayGhi(layNgay(txtNgayGhi, "Ngày ghi (yyyy-MM-dd)", false));
        return e;
    }

    @Override
    protected void themBanGhi(ChiSoDienNuoc e) throws SQLException { dao.them(e); }

    @Override
    protected void suaBanGhi(ChiSoDienNuoc e) throws SQLException { dao.sua(e); }

    @Override
    protected void xoaBanGhi(ChiSoDienNuoc e) throws SQLException { dao.xoa(e); }
}
