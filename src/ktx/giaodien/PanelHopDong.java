package ktx.giaodien;

import java.sql.SQLException;
import java.util.List;
import javax.swing.*;
import ktx.dao.HopDongDAO;
import ktx.thucthe.HopDong;


public class PanelHopDong extends PanelQuanLy<HopDong> {

    private final HopDongDAO dao = new HopDongDAO();
    private final JTextField txtMaHD = new JTextField(14);
    private final ComboFK cbMaSV = new ComboFK("SinhVien", "MaSV", false);
    private final ComboFK cbMaGiuong = new ComboFK("Giuong", "MaGiuong", false);
    private final ComboFK cbMaNVLap = new ComboFK("NhanVien", "MaNV", true);
    private final JTextField txtNgayLap = new JTextField(14);
    private final JTextField txtNgayBatDau = new JTextField(14);
    private final JTextField txtNgayKetThuc = new JTextField(14);
    private final JTextField txtTienCoc = new JTextField(14);
    private final JComboBox<String> cbTrangThai = new JComboBox<>(new String[]{"Chưa bắt đầu", "Đang hiệu lực", "Đã thanh lý", "Đã hủy"});

    public PanelHopDong() {
        super("Quản lý hợp đồng", new String[]{"Mã HĐ", "Sinh viên", "Giường", "NV lập", "Ngày lập (yyyy-MM-dd HH:mm:ss)", "Ngày bắt đầu (yyyy-MM-dd)", "Ngày kết thúc (yyyy-MM-dd)", "Tiền cọc", "Trạng thái"});
        themTruong("Mã HĐ", txtMaHD);
        txtMaHD.setEditable(false);
        themTruong("Sinh viên *", cbMaSV);
        themTruong("Giường *", cbMaGiuong);
        themTruong("NV lập", cbMaNVLap);
        themTruong("Ngày lập (yyyy-MM-dd HH:mm:ss)", txtNgayLap);
        themTruong("Ngày bắt đầu (yyyy-MM-dd) *", txtNgayBatDau);
        themTruong("Ngày kết thúc (yyyy-MM-dd) *", txtNgayKetThuc);
        themTruong("Tiền cọc", txtTienCoc);
        themTruong("Trạng thái", cbTrangThai);
        khoiTao();
    }

    @Override
    protected List<HopDong> taiDuLieu() throws SQLException {
        return dao.layTatCa();
    }

    @Override
    protected Object[] chuyenHang(HopDong e) {
        return new Object[]{e.getMaHD(), e.getMaSV(), e.getMaGiuong(), e.getMaNVLap(), dinhDang(e.getNgayLap()), dinhDang(e.getNgayBatDau()), dinhDang(e.getNgayKetThuc()), dinhDang(e.getTienCoc()), e.getTrangThai()};
    }

    @Override
    protected void hienLenForm(HopDong e) {
        txtMaHD.setText(dinhDang(e.getMaHD()));
        cbMaSV.chon(e.getMaSV());
        cbMaGiuong.chon(e.getMaGiuong());
        cbMaNVLap.chon(e.getMaNVLap());
        txtNgayLap.setText(dinhDang(e.getNgayLap()));
        txtNgayBatDau.setText(dinhDang(e.getNgayBatDau()));
        txtNgayKetThuc.setText(dinhDang(e.getNgayKetThuc()));
        txtTienCoc.setText(dinhDang(e.getTienCoc()));
        cbTrangThai.setSelectedItem(e.getTrangThai());
    }

    @Override
    protected void lamTrongForm() {
        txtMaHD.setText("");
        cbMaSV.chon(null);
        cbMaGiuong.chon(null);
        cbMaNVLap.chonNhanVienHienTai();
        txtNgayLap.setText("");
        txtNgayBatDau.setText("");
        txtNgayKetThuc.setText("");
        txtTienCoc.setText("0");
        cbTrangThai.setSelectedIndex(0);
    }

    @Override
    protected HopDong docForm(boolean laThem) {
        HopDong e = new HopDong();
        e.setMaHD(laThem ? 0 : layInt(txtMaHD, "Mã HĐ"));
        e.setMaSV(layFK(cbMaSV, "Sinh viên", true));
        e.setMaGiuong(layFK(cbMaGiuong, "Giường", true));
        e.setMaNVLap(layFK(cbMaNVLap, "NV lập", false));
        e.setNgayLap(layGio(txtNgayLap, "Ngày lập (yyyy-MM-dd HH:mm:ss)", false));
        e.setNgayBatDau(layNgay(txtNgayBatDau, "Ngày bắt đầu (yyyy-MM-dd)", true));
        e.setNgayKetThuc(layNgay(txtNgayKetThuc, "Ngày kết thúc (yyyy-MM-dd)", true));
        e.setTienCoc(layTien(txtTienCoc, "Tiền cọc", false, "0"));
        e.setTrangThai((String) cbTrangThai.getSelectedItem());
        return e;
    }

    @Override
    protected void themBanGhi(HopDong e) throws SQLException { dao.them(e); }

    @Override
    protected void suaBanGhi(HopDong e) throws SQLException { dao.sua(e); }

    @Override
    protected void xoaBanGhi(HopDong e) throws SQLException { dao.xoa(e); }
}
