package ktx.giaodien;

import java.sql.SQLException;
import java.util.List;
import javax.swing.*;
import ktx.dao.LoaiPhongDAO;
import ktx.thucthe.LoaiPhong;
public class PanelLoaiPhong extends PanelQuanLy<LoaiPhong> {

    private final LoaiPhongDAO dao = new LoaiPhongDAO();
    private final JTextField txtMaLoai = new JTextField(14);
    private final JTextField txtTenLoai = new JTextField(14);
    private final JTextField txtDienTich = new JTextField(14);
    private final JTextField txtSoGiuong = new JTextField(14);
    private final JTextField txtGiaPhong = new JTextField(14);
    private final JTextField txtMoTa = new JTextField(14);

    public PanelLoaiPhong() {
        super("Quản lý loại phòng", new String[]{"Mã loại", "Tên loại", "Diện tích (m²)", "Số giường", "Giá phòng", "Mô tả"});
        themTruong("Mã loại *", txtMaLoai);
        themTruong("Tên loại *", txtTenLoai);
        themTruong("Diện tích (m²)", txtDienTich);
        themTruong("Số giường *", txtSoGiuong);
        themTruong("Giá phòng *", txtGiaPhong);
        themTruong("Mô tả", txtMoTa);
        khoiTao();
    }

    @Override
    protected List<LoaiPhong> taiDuLieu() throws SQLException {
        return dao.layTatCa();
    }

    @Override
    protected Object[] chuyenHang(LoaiPhong e) {
        return new Object[]{e.getMaLoai(), e.getTenLoai(), dinhDang(e.getDienTich()), e.getSoGiuong(), dinhDang(e.getGiaPhong()), e.getMoTa()};
    }

    @Override
    protected void hienLenForm(LoaiPhong e) {
        txtMaLoai.setText(dinhDang(e.getMaLoai()));
        txtMaLoai.setEditable(false);
        txtTenLoai.setText(dinhDang(e.getTenLoai()));
        txtDienTich.setText(dinhDang(e.getDienTich()));
        txtSoGiuong.setText(dinhDang(e.getSoGiuong()));
        txtGiaPhong.setText(dinhDang(e.getGiaPhong()));
        txtMoTa.setText(dinhDang(e.getMoTa()));
    }

    @Override
    protected void lamTrongForm() {
        txtMaLoai.setText("");
        txtMaLoai.setEditable(true);
        txtTenLoai.setText("");
        txtDienTich.setText("");
        txtSoGiuong.setText("");
        txtGiaPhong.setText("");
        txtMoTa.setText("");
    }

    @Override
    protected LoaiPhong docForm(boolean laThem) {
        LoaiPhong e = new LoaiPhong();
        e.setMaLoai(layChuoi(txtMaLoai, "Mã loại", true));
        e.setTenLoai(layChuoi(txtTenLoai, "Tên loại", true));
        e.setDienTich(layDouble(txtDienTich, "Diện tích (m²)", false, null));
        e.setSoGiuong(layInt(txtSoGiuong, "Số giường"));
        e.setGiaPhong(layTien(txtGiaPhong, "Giá phòng", true, null));
        e.setMoTa(layChuoi(txtMoTa, "Mô tả", false));
        return e;
    }

    @Override
    protected void themBanGhi(LoaiPhong e) throws SQLException { dao.them(e); }

    @Override
    protected void suaBanGhi(LoaiPhong e) throws SQLException { dao.sua(e); }

    @Override
    protected void xoaBanGhi(LoaiPhong e) throws SQLException { dao.xoa(e); }
}
