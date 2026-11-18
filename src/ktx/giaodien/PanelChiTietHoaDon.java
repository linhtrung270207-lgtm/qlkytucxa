package ktx.giaodien;

import java.sql.SQLException;
import java.util.List;
import javax.swing.*;
import ktx.dao.ChiTietHoaDonDAO;
import ktx.thucthe.ChiTietHoaDon;
public class PanelChiTietHoaDon extends PanelQuanLy<ChiTietHoaDon> {

    private final ChiTietHoaDonDAO dao = new ChiTietHoaDonDAO();
    private final JTextField txtMaCTHD = new JTextField(14);
    private final ComboFK cbMaHoaDon = new ComboFK("HoaDon", "MaHD", false);
    private final JTextField txtTenLoaiPhi = new JTextField(14);
    private final JTextField txtSoLuong = new JTextField(14);
    private final JTextField txtDonGia = new JTextField(14);

    public PanelChiTietHoaDon() {
        super("Quản lý chi tiết hóa đơn", new String[]{"Mã CT", "Hóa đơn", "Tên loại phí", "Số lượng", "Đơn giá", "Thành tiền"});
        themTruong("Mã CT", txtMaCTHD);
        txtMaCTHD.setEditable(false);
        themTruong("Hóa đơn *", cbMaHoaDon);
        themTruong("Tên loại phí *", txtTenLoaiPhi);
        themTruong("Số lượng *", txtSoLuong);
        themTruong("Đơn giá *", txtDonGia);
        khoiTao();
    }

    @Override
    protected List<ChiTietHoaDon> taiDuLieu() throws SQLException {
        return dao.layTatCa();
    }

    @Override
    protected Object[] chuyenHang(ChiTietHoaDon e) {
        return new Object[]{e.getMaCTHD(), e.getMaHoaDon(), e.getTenLoaiPhi(), dinhDang(e.getSoLuong()), dinhDang(e.getDonGia()), dinhDang(e.getThanhTien())};
    }

    @Override
    protected void hienLenForm(ChiTietHoaDon e) {
        txtMaCTHD.setText(dinhDang(e.getMaCTHD()));
        cbMaHoaDon.chon(String.valueOf(e.getMaHoaDon()));
        txtTenLoaiPhi.setText(dinhDang(e.getTenLoaiPhi()));
        txtSoLuong.setText(dinhDang(e.getSoLuong()));
        txtDonGia.setText(dinhDang(e.getDonGia()));
    }

    @Override
    protected void lamTrongForm() {
        txtMaCTHD.setText("");
        cbMaHoaDon.chon(null);
        txtTenLoaiPhi.setText("");
        txtSoLuong.setText("1");
        txtDonGia.setText("");
    }

    @Override
    protected ChiTietHoaDon docForm(boolean laThem) {
        ChiTietHoaDon e = new ChiTietHoaDon();
        e.setMaCTHD(laThem ? 0 : layInt(txtMaCTHD, "Mã CT"));
        e.setMaHoaDon(layIntFK(cbMaHoaDon, "Hóa đơn"));
        e.setTenLoaiPhi(layChuoi(txtTenLoaiPhi, "Tên loại phí", true));
        e.setSoLuong(layDouble(txtSoLuong, "Số lượng", true, "1"));
        e.setDonGia(layTien(txtDonGia, "Đơn giá", true, null));
        return e;
    }

    @Override
    protected void themBanGhi(ChiTietHoaDon e) throws SQLException { dao.them(e); }

    @Override
    protected void suaBanGhi(ChiTietHoaDon e) throws SQLException { dao.sua(e); }

    @Override
    protected void xoaBanGhi(ChiTietHoaDon e) throws SQLException { dao.xoa(e); }
}
