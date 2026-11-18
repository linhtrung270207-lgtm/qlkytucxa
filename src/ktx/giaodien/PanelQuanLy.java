package ktx.giaodien;

import java.awt.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.JTextComponent;
public abstract class PanelQuanLy<T> extends JPanel {

    private final String tieuDe;
    private final DefaultTableModel moHinh;
    private final JTable bang;
    private final TableRowSorter<DefaultTableModel> boLoc;
    private final JTextField txtTimKiem = new JTextField(20);
    private final List<String> dsNhan = new ArrayList<>();
    private final List<JComponent> dsTruong = new ArrayList<>();
    private final List<ComboFK> dsCombo = new ArrayList<>();
    private final List<T> danhSach = new ArrayList<>();
    private T dangChon;

    protected PanelQuanLy(String tieuDe, String[] tenCot) {
        this.tieuDe = tieuDe;
        moHinh = new DefaultTableModel(tenCot, 0) {
            @Override
            public boolean isCellEditable(int r, int c) { return false; }
        };
        bang = new JTable(moHinh);
        boLoc = new TableRowSorter<>(moHinh);
        bang.setRowSorter(boLoc);
    }

    // ---------- các hàm lớp con phải cài đặt ----------
    protected abstract List<T> taiDuLieu() throws SQLException;
    protected abstract Object[] chuyenHang(T e);
    protected abstract void hienLenForm(T e);
    protected abstract void lamTrongForm();
    protected abstract T docForm(boolean laThem);
    protected abstract void themBanGhi(T e) throws SQLException;
    protected abstract void suaBanGhi(T e) throws SQLException;
    protected abstract void xoaBanGhi(T e) throws SQLException;

    protected void themTruong(String nhan, JComponent truong) {
        dsNhan.add(nhan);
        dsTruong.add(truong);
        if (truong instanceof ComboFK) dsCombo.add((ComboFK) truong);
    }

    /** Dựng giao diện; gọi ở cuối constructor của lớp con. */
    protected final void khoiTao() {
        setLayout(new BorderLayout(8, 8));
        setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        JPanel form = new JPanel(new GridBagLayout());
        form.setBorder(BorderFactory.createTitledBorder(tieuDe));
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(3, 6, 3, 6);
        g.anchor = GridBagConstraints.WEST;
        for (int i = 0; i < dsTruong.size(); i++) {
            int hang = i / 2;
            int cot = (i % 2) * 2;
            g.gridy = hang;
            g.gridx = cot;
            g.weightx = 0;
            g.fill = GridBagConstraints.NONE;
            form.add(new JLabel(dsNhan.get(i)), g);
            g.gridx = cot + 1;
            g.weightx = 1;
            g.fill = GridBagConstraints.HORIZONTAL;
            form.add(dsTruong.get(i), g);
        }

        JPanel nut = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 4));
        nut.add(taoNut("Thêm", e -> hanhDongThem()));
        nut.add(taoNut("Sửa", e -> hanhDongSua()));
        nut.add(taoNut("Xóa", e -> hanhDongXoa()));
        nut.add(taoNut("Làm mới form", e -> datLaiForm()));
        nut.add(taoNut("Tải lại dữ liệu", e -> taiLai()));
        nut.add(Box.createHorizontalStrut(20));
        nut.add(new JLabel("Tìm kiếm:"));
        nut.add(txtTimKiem);

        JPanel phiaTren = new JPanel(new BorderLayout());
        phiaTren.add(form, BorderLayout.CENTER);
        phiaTren.add(nut, BorderLayout.SOUTH);
        add(phiaTren, BorderLayout.NORTH);

        bang.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        bang.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        bang.setRowHeight(24);
        for (int i = 0; i < bang.getColumnCount(); i++) bang.getColumnModel().getColumn(i).setPreferredWidth(140);
        add(new JScrollPane(bang), BorderLayout.CENTER);

        bang.getSelectionModel().addListSelectionListener(ev -> {
            if (ev.getValueIsAdjusting()) return;
            int r = bang.getSelectedRow();
            if (r < 0) return;
            int idx = bang.convertRowIndexToModel(r);
            if (idx < 0 || idx >= danhSach.size()) return;
            dangChon = danhSach.get(idx);
            hienLenForm(dangChon);
        });

        txtTimKiem.getDocument().addDocumentListener(new DocumentListener() {
            @Override public void insertUpdate(DocumentEvent e) { loc(); }
            @Override public void removeUpdate(DocumentEvent e) { loc(); }
            @Override public void changedUpdate(DocumentEvent e) { loc(); }
        });

        lamTrongForm();
    }

    private JButton taoNut(String ten, java.awt.event.ActionListener hd) {
        JButton b = new JButton(ten);
        b.addActionListener(hd);
        return b;
    }

    private void loc() {
        String tk = txtTimKiem.getText().trim();
        boLoc.setRowFilter(tk.isEmpty() ? null : RowFilter.regexFilter("(?i)" + Pattern.quote(tk)));
    }

    /** Nạp lại ComboBox khóa ngoại và dữ liệu bảng từ CSDL. */
    public void taiLai() {
        try {
            for (ComboFK cb : dsCombo) cb.napLai();
            danhSach.clear();
            danhSach.addAll(taiDuLieu());
            moHinh.setRowCount(0);
            for (T e : danhSach) moHinh.addRow(chuyenHang(e));
            dangChon = null;
            bang.clearSelection();
            lamTrongForm();
        } catch (SQLException ex) {
            baoLoiCSDL(ex);
        }
    }

    private void datLaiForm() {
        dangChon = null;
        bang.clearSelection();
        lamTrongForm();
    }

    private void hanhDongThem() {
        try {
            themBanGhi(docForm(true));
            JOptionPane.showMessageDialog(this, "Thêm thành công.");
            taiLai();
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Dữ liệu chưa hợp lệ", JOptionPane.WARNING_MESSAGE);
        } catch (SQLException ex) {
            baoLoiCSDL(ex);
        }
    }

    private void hanhDongSua() {
        if (dangChon == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một dòng trong bảng để sửa.");
            return;
        }
        try {
            suaBanGhi(docForm(false));
            JOptionPane.showMessageDialog(this, "Cập nhật thành công.");
            taiLai();
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Dữ liệu chưa hợp lệ", JOptionPane.WARNING_MESSAGE);
        } catch (SQLException ex) {
            baoLoiCSDL(ex);
        }
    }

    private void hanhDongXoa() {
        if (dangChon == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một dòng trong bảng để xóa.");
            return;
        }
        int kq = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa bản ghi đang chọn?", "Xác nhận xóa",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (kq != JOptionPane.YES_OPTION) return;
        try {
            xoaBanGhi(dangChon);
            JOptionPane.showMessageDialog(this, "Đã xóa.");
            taiLai();
        } catch (SQLException ex) {
            baoLoiCSDL(ex);
        }
    }

    private void baoLoiCSDL(SQLException ex) {
        String goiY;
        switch (ex.getErrorCode()) {
            case 547:
                goiY = "Vi phạm ràng buộc (khóa ngoại hoặc CHECK): dữ liệu đang được bảng khác sử dụng hoặc giá trị không hợp lệ.\n\n";
                break;
            case 2627:
            case 2601:
                goiY = "Dữ liệu bị trùng (mã, CCCD, số điện thoại, email, tài khoản hoặc kỳ ghi điện nước đã tồn tại).\n\n";
                break;
            default:
                goiY = "";
        }
        JOptionPane.showMessageDialog(this, goiY + ex.getMessage(), "Lỗi cơ sở dữ liệu", JOptionPane.ERROR_MESSAGE);
    }

    // ---------- hàm hỗ trợ đọc dữ liệu từ form (ném IllegalArgumentException nếu sai) ----------
    protected static String dinhDang(Object v) {
        if (v == null) return "";
        if (v instanceof Timestamp) return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((Timestamp) v);
        if (v instanceof Date) return new SimpleDateFormat("yyyy-MM-dd").format((Date) v);
        if (v instanceof BigDecimal) return ((BigDecimal) v).stripTrailingZeros().toPlainString();
        return v.toString();
    }

    protected String layChuoi(JTextComponent c, String nhan, boolean batBuoc) {
        String s = c.getText().trim();
        if (s.isEmpty()) {
            if (batBuoc) throw new IllegalArgumentException("Vui lòng nhập: " + nhan + ".");
            return null;
        }
        return s;
    }

    protected String layFK(ComboFK cb, String nhan, boolean batBuoc) {
        String s = cb.giaTri();
        if (s == null && batBuoc) throw new IllegalArgumentException("Vui lòng chọn: " + nhan + ".");
        return s;
    }

    protected int layIntFK(ComboFK cb, String nhan) {
        String s = layFK(cb, nhan, true);
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException(nhan + " không hợp lệ.");
        }
    }

    protected int layInt(JTextComponent c, String nhan) {
        try {
            return Integer.parseInt(layChuoi(c, nhan, true));
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException(nhan + " phải là số nguyên.");
        }
    }

    protected Double layDouble(JTextComponent c, String nhan, boolean batBuoc, String macDinh) {
        String s = layChuoi(c, nhan, batBuoc && macDinh == null);
        if (s == null) s = macDinh;
        if (s == null) return null;
        try {
            return Double.valueOf(s.replace(',', '.'));
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException(nhan + " phải là số.");
        }
    }

    protected BigDecimal layTien(JTextComponent c, String nhan, boolean batBuoc, String macDinh) {
        String s = layChuoi(c, nhan, batBuoc && macDinh == null);
        if (s == null) s = macDinh;
        if (s == null) return null;
        try {
            return new BigDecimal(s.replace(",", ""));
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException(nhan + " phải là số.");
        }
    }

    protected Date layNgay(JTextComponent c, String nhan, boolean batBuoc) {
        String s = layChuoi(c, nhan, batBuoc);
        if (s == null) return null;
        try {
            return Date.valueOf(s.length() > 10 ? s.substring(0, 10) : s);
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException(nhan + " phải đúng định dạng yyyy-MM-dd.");
        }
    }

    protected Timestamp layGio(JTextComponent c, String nhan, boolean batBuoc) {
        String s = layChuoi(c, nhan, batBuoc);
        if (s == null) return null;
        try {
            return Timestamp.valueOf(s.length() <= 10 ? s + " 00:00:00" : s);
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException(nhan + " phải đúng định dạng yyyy-MM-dd HH:mm:ss.");
        }
    }
}
