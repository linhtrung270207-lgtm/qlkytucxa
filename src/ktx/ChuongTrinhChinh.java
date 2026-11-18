package ktx;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import ktx.giaodien.DangNhapFrame;

public class ChuongTrinhChinh {
    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ignored) {

        }
        SwingUtilities.invokeLater(() -> new DangNhapFrame().setVisible(true));
    }
}
