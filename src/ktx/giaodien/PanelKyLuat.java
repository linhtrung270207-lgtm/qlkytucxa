package ktx.giaodien;

import java.sql.SQLException;
import java.util.List;
import javax.swing.*;
import ktx.dao.KyLuatDAO;
import ktx.thucthe.KyLuat;
public class PanelKyLuat extends PanelQuanLy<KyLuat> {

    private final KyLuatDAO dao = new KyLuatDAO();
    private final JTextField txtMaKL = new JTextField(14);
    private final ComboFK cbMaSV = new ComboFK("SinhVien", "MaSV", false);
    private final ComboFK cbMaNVLap = new ComboFK("NhanVien", "MaNV", true);
    private final JComboBox<String> cbLoai = new JComboBox<>(new String[]{"Khen thưởng", "Kỷ luật"});
    private final JTextField txtHinhThuc = new JTextField(14);
    private final JTextField txtNoiDung = new JTextField(14);
    private final JTextField txtNgayQuyetDinh = new JTextField(14);
    private final JTextField txtTienPhat = new JTextField(14);

    public PanelKyLuat() {
        super("Quản lý khen thưởng - kỷ luật", new String[]{"Mã KL", "Sinh viên", "NV lập", "Loại", "Hình thức", "Nội dung", "Ngày quyết định (yyyy-MM-dd HH:mm:ss)", "Tiền phạt"});
        themTruong("Mã KL", txtMaKL);
        txtMaKL.setEditable(false);
        themTruong("Sinh viên *", cbMaSV);
        themTruong("NV lập", cbMaNVLap);
        themTruong("Loại", cbLoai);
        themTruong("Hình thức *", txtHinhThuc);
        themTruong("Nội dung *", txtNoiDung);
        themTruong("Ngày quyết định (yyyy-MM-dd HH:mm:ss)", txtNgayQuyetDinh);
        themTruong("Tiền phạt", txtTienPhat);
        khoiTao();
    }

    @Override
    protected List<KyLuat> taiDuLieu() throws SQLException {
        return dao.layTatCa();
    }

    @Override
    protected Object[] chuyenHang(KyLuat e) {
        return new Object[]{e.getMaKL(), e.getMaSV(), e.getMaNVLap(), e.getLoai(), e.getHinhThuc(), e.getNoiDung(), dinhDang(e.getNgayQuyetDinh()), dinhDang(e.getTienPhat())};
    }

    @Override
    protected void hienLenForm(KyLuat e) {
        txtMaKL.setText(dinhDang(e.getMaKL()));
        cbMaSV.chon(e.getMaSV());
        cbMaNVLap.chon(e.getMaNVLap());
        cbLoai.setSelectedItem(e.getLoai());
        txtHinhThuc.setText(dinhDang(e.getHinhThuc()));
        txtNoiDung.setText(dinhDang(e.getNoiDung()));
        txtNgayQuyetDinh.setText(dinhDang(e.getNgayQuyetDinh()));
        txtTienPhat.setText(dinhDang(e.getTienPhat()));
    }

    @Override
    protected void lamTrongForm() {
        txtMaKL.setText("");
        cbMaSV.chon(null);
        cbMaNVLap.chonNhanVienHienTai();
        cbLoai.setSelectedIndex(0);
        txtHinhThuc.setText("");
        txtNoiDung.setText("");
        txtNgayQuyetDinh.setText("");
        txtTienPhat.setText("0");
    }

    @Override
    protected KyLuat docForm(boolean laThem) {
        KyLuat e = new KyLuat();
        e.setMaKL(laThem ? 0 : layInt(txtMaKL, "Mã KL"));
        e.setMaSV(layFK(cbMaSV, "Sinh viên", true));
        e.setMaNVLap(layFK(cbMaNVLap, "NV lập", false));
        e.setLoai((String) cbLoai.getSelectedItem());
        e.setHinhThuc(layChuoi(txtHinhThuc, "Hình thức", true));
        e.setNoiDung(layChuoi(txtNoiDung, "Nội dung", true));
        e.setNgayQuyetDinh(layGio(txtNgayQuyetDinh, "Ngày quyết định (yyyy-MM-dd HH:mm:ss)", false));
        e.setTienPhat(layTien(txtTienPhat, "Tiền phạt", false, "0"));
        return e;
    }

    @Override
    protected void themBanGhi(KyLuat e) throws SQLException { dao.them(e); }

    @Override
    protected void suaBanGhi(KyLuat e) throws SQLException { dao.sua(e); }

    @Override
    protected void xoaBanGhi(KyLuat e) throws SQLException { dao.xoa(e); }
}
