package ktx.giaodien;

import java.sql.SQLException;
import javax.swing.JComboBox;
import ktx.dao.DongBo;
import ktx.tienich.PhienLamViec;
public class ComboFK extends JComboBox<String> {
    private final String bang;
    private final String cot;
    private final boolean choPhepTrong;

    public ComboFK(String bang, String cot, boolean choPhepTrong) {
        this.bang = bang;
        this.cot = cot;
        this.choPhepTrong = choPhepTrong;
        if (choPhepTrong) addItem("");
    }

    public void napLai() throws SQLException {
        String cu = giaTri();
        removeAllItems();
        if (choPhepTrong) addItem("");
        for (String ma : DongBo.layDanhSachMa(bang, cot)) addItem(ma);
        chon(cu);
    }

    public String giaTri() {
        Object o = getSelectedItem();
        String s = o == null ? "" : o.toString().trim();
        return s.isEmpty() ? null : s;
    }

    public void chon(String ma) {
        if (ma == null || ma.isEmpty()) {
            if (getItemCount() > 0) setSelectedIndex(0);
        } else {
            setSelectedItem(ma);
        }
    }

    public void chonNhanVienHienTai() {
        chon(PhienLamViec.maNhanVien());
    }
}
