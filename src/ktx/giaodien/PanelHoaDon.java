package ktx.giaodien;

import java.sql.SQLException;
import java.util.List;
import javax.swing.*;
import ktx.dao.HoaDonDAO;
import ktx.thucthe.HoaDon;
public class PanelHoaDon extends PanelQuanLy<HoaDon> {

    private final HoaDonDAO dao = new HoaDonDAO();
    private final JTextField txtMaHD = new JTextField(14);
    private final ComboFK cbMaPhong = new ComboFK("Phong", "MaPhong", false);
    private final ComboFK cbMaNVLap = new ComboFK("NhanVien", "MaNV", true);
    private final JTextField txtThang = new JTextField(14);
    private final JTextField txtNam = new JTextField(14);
    private final JTextField txtNgayLap = new JTextField(14);
    private final JTextField txtHanThanhToan = new JTextField(14);
    private final JTextField txtTongTien = new JTextField(14);
    private final JComboBox<String> cbTrangThai = new JComboBox<>(new String[]{"Chưa thanh toán", "Đã thanh toán một phần", "Đã thanh toán xong"});
    private final JTextField txtGhiChu = new JTextField(14);

    public PanelHoaDon() {
        super("Quản lý hóa đơn", new String[]{"Mã HĐ", "Phòng", "NV lập", "Tháng", "Năm", "Ngày lập (yyyy-MM-dd HH:mm:ss)", "Hạn thanh toán (yyyy-MM-dd)", "Tổng tiền (tự tính)", "Trạng thái", "Ghi chú"});
        themTruong("Mã HĐ", txtMaHD);
        txtMaHD.setEditable(false);
        themTruong("Phòng *", cbMaPhong);
        themTruong("NV lập", cbMaNVLap);
        themTruong("Tháng *", txtThang);
        themTruong("Năm *", txtNam);
        themTruong("Ngày lập (yyyy-MM-dd HH:mm:ss)", txtNgayLap);
        themTruong("Hạn thanh toán (yyyy-MM-dd) *", txtHanThanhToan);
        themTruong("Tổng tiền (tự tính)", txtTongTien);
        txtTongTien.setEditable(false);
        themTruong("Trạng thái", cbTrangThai);
        themTruong("Ghi chú", txtGhiChu);
        khoiTao();
    }

    @Override
    protected List<HoaDon> taiDuLieu() throws SQLException {
        return dao.layTatCa();
    }

    @Override
    protected Object[] chuyenHang(HoaDon e) {
        return new Object[]{e.getMaHD(), e.getMaPhong(), e.getMaNVLap(), e.getThang(), e.getNam(), dinhDang(e.getNgayLap()), dinhDang(e.getHanThanhToan()), dinhDang(e.getTongTien()), e.getTrangThai(), e.getGhiChu()};
    }

    @Override
    protected void hienLenForm(HoaDon e) {
        txtMaHD.setText(dinhDang(e.getMaHD()));
        cbMaPhong.chon(e.getMaPhong());
        cbMaNVLap.chon(e.getMaNVLap());
        txtThang.setText(dinhDang(e.getThang()));
        txtNam.setText(dinhDang(e.getNam()));
        txtNgayLap.setText(dinhDang(e.getNgayLap()));
        txtHanThanhToan.setText(dinhDang(e.getHanThanhToan()));
        txtTongTien.setText(dinhDang(e.getTongTien()));
        cbTrangThai.setSelectedItem(e.getTrangThai());
        txtGhiChu.setText(dinhDang(e.getGhiChu()));
    }

    @Override
    protected void lamTrongForm() {
        txtMaHD.setText("");
        cbMaPhong.chon(null);
        cbMaNVLap.chonNhanVienHienTai();
        txtThang.setText("");
        txtNam.setText("");
        txtNgayLap.setText("");
        txtHanThanhToan.setText("");
        txtTongTien.setText("0");
        cbTrangThai.setSelectedIndex(0);
        txtGhiChu.setText("");
    }

    @Override
    protected HoaDon docForm(boolean laThem) {
        HoaDon e = new HoaDon();
        e.setMaHD(laThem ? 0 : layInt(txtMaHD, "Mã HĐ"));
        e.setMaPhong(layFK(cbMaPhong, "Phòng", true));
        e.setMaNVLap(layFK(cbMaNVLap, "NV lập", false));
        e.setThang(layInt(txtThang, "Tháng"));
        e.setNam(layInt(txtNam, "Năm"));
        e.setNgayLap(layGio(txtNgayLap, "Ngày lập (yyyy-MM-dd HH:mm:ss)", false));
        e.setHanThanhToan(layNgay(txtHanThanhToan, "Hạn thanh toán (yyyy-MM-dd)", true));
        e.setTongTien(layTien(txtTongTien, "Tổng tiền (tự tính)", false, "0"));
        e.setTrangThai((String) cbTrangThai.getSelectedItem());
        e.setGhiChu(layChuoi(txtGhiChu, "Ghi chú", false));
        return e;
    }

    @Override
    protected void themBanGhi(HoaDon e) throws SQLException { dao.them(e); }

    @Override
    protected void suaBanGhi(HoaDon e) throws SQLException { dao.sua(e); }

    @Override
    protected void xoaBanGhi(HoaDon e) throws SQLException { dao.xoa(e); }
}
