package ktx.giaodien;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import ktx.thucthe.NhanVien;
import ktx.tienich.PhienLamViec;
public class ManHinhChinh extends JFrame {

    private final JTabbedPane tabs = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
    private final List<PanelQuanLy<?>> dsPanel = new ArrayList<>();

    public ManHinhChinh() {
        super("Hệ thống quản lý ký túc xá");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1200, 720);
        setLocationRelativeTo(null);

        NhanVien nv = PhienLamViec.getNhanVienHienTai();
        String ten = nv == null ? "" : nv.getHoTen() + (nv.getChucVu() == null ? "" : " - " + nv.getChucVu());
        JLabel chao = new JLabel("  Xin chào: " + ten);
        chao.setFont(chao.getFont().deriveFont(Font.BOLD));
        chao.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));

        JMenuBar menu = new JMenuBar();
        JMenu heThong = new JMenu("Hệ thống");
        JMenuItem dangXuat = new JMenuItem("Đăng xuất");
        JMenuItem thoat = new JMenuItem("Thoát");
        dangXuat.addActionListener(e -> {
            PhienLamViec.setNhanVienHienTai(null);
            new DangNhapFrame().setVisible(true);
            dispose();
        });
        thoat.addActionListener(e -> System.exit(0));
        heThong.add(dangXuat);
        heThong.add(thoat);
        menu.add(heThong);
        setJMenuBar(menu);

        them("Sinh viên", new PanelSinhVien());
        them("Hợp đồng", new PanelHopDong());
        them("Khu nhà", new PanelKhuNha());
        them("Loại phòng", new PanelLoaiPhong());
        them("Phòng", new PanelPhong());
        them("Giường", new PanelGiuong());
        them("Chỉ số điện nước", new PanelChiSoDienNuoc());
        them("Hóa đơn", new PanelHoaDon());
        them("Chi tiết hóa đơn", new PanelChiTietHoaDon());
        them("Thanh toán", new PanelThanhToan());
        them("Sửa chữa", new PanelYeuCauSuaChua());
        them("Khen thưởng - Kỷ luật", new PanelKyLuat());
        them("Nhân viên", new PanelNhanVien());

        tabs.addChangeListener(e -> {
            int i = tabs.getSelectedIndex();
            if (i >= 0) dsPanel.get(i).taiLai();
        });

        add(chao, BorderLayout.NORTH);
        add(tabs, BorderLayout.CENTER);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowOpened(java.awt.event.WindowEvent e) {
                dsPanel.get(tabs.getSelectedIndex()).taiLai();
            }
        });
    }

    private void them(String tieuDe, PanelQuanLy<?> panel) {
        dsPanel.add(panel);
        tabs.addTab(tieuDe, panel);
    }
}
