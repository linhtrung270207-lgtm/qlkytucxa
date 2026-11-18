package ktx.giaodien;

import java.awt.*;
import java.sql.SQLException;
import javax.swing.*;
import ktx.dao.NhanVienDAO;
import ktx.thucthe.NhanVien;
import ktx.tienich.PhienLamViec;


public class DangNhapFrame extends JFrame {

    private final JTextField txtTaiKhoan = new JTextField(16);
    private final JPasswordField txtMatKhau = new JPasswordField(16);
    private final NhanVienDAO nhanVienDAO = new NhanVienDAO();

    public DangNhapFrame() {
        super("Đăng nhập - Quản lý ký túc xá");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel tieuDe = new JLabel("QUẢN LÝ KÝ TÚC XÁ", SwingConstants.CENTER);
        tieuDe.setFont(tieuDe.getFont().deriveFont(Font.BOLD, 20f));
        tieuDe.setBorder(BorderFactory.createEmptyBorder(14, 10, 6, 10));

        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(6, 8, 6, 8);
        g.anchor = GridBagConstraints.WEST;
        g.gridx = 0; g.gridy = 0; form.add(new JLabel("Tài khoản:"), g);
        g.gridx = 1; form.add(txtTaiKhoan, g);
        g.gridx = 0; g.gridy = 1; form.add(new JLabel("Mật khẩu:"), g);
        g.gridx = 1; form.add(txtMatKhau, g);

        JButton btnDangNhap = new JButton("Đăng nhập");
        JButton btnThoat = new JButton("Thoát");
        btnDangNhap.addActionListener(e -> dangNhap());
        btnThoat.addActionListener(e -> System.exit(0));
        txtMatKhau.addActionListener(e -> dangNhap());
        txtTaiKhoan.addActionListener(e -> txtMatKhau.requestFocus());
        JPanel nut = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 8));
        nut.add(btnDangNhap);
        nut.add(btnThoat);

        add(tieuDe, BorderLayout.NORTH);
        add(form, BorderLayout.CENTER);
        add(nut, BorderLayout.SOUTH);
        getRootPane().setDefaultButton(btnDangNhap);
        pack();
        setLocationRelativeTo(null);
    }

    private void dangNhap() {
        String tk = txtTaiKhoan.getText().trim();
        String mk = new String(txtMatKhau.getPassword());
        if (tk.isEmpty() || mk.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tài khoản và mật khẩu.");
            return;
        }
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            NhanVien nv = nhanVienDAO.dangNhap(tk, mk);
            if (nv == null) {
                JOptionPane.showMessageDialog(this, "Sai tài khoản, mật khẩu hoặc tài khoản đã bị khóa.",
                        "Đăng nhập thất bại", JOptionPane.WARNING_MESSAGE);
                return;
            }
            PhienLamViec.setNhanVienHienTai(nv);
            new ManHinhChinh().setVisible(true);
            dispose();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,
                    "Không kết nối được CSDL:\n" + ex.getMessage()
                            + "\n\nKiểm tra file cauhinh.properties, SQL Server đang chạy và đã thêm mssql-jdbc vào thư mục lib.",
                    "Lỗi kết nối", JOptionPane.ERROR_MESSAGE);
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }
}
