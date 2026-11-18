package ktx.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public final class KetNoi {

    private static final String URL =
            "jdbc:sqlserver://127.0.0.1:1433;databaseName=Qlkytucxa;encrypt=true;trustServerCertificate=true;";
    private static final String TAI_KHOAN = "Qlkytucxa";
    private static final String MAT_KHAU = "123456"; // đổi thành mật khẩu SQL Server của bạn

    private KetNoi() { }

    public static Connection layKetNoi() throws SQLException {
        return DriverManager.getConnection(URL, TAI_KHOAN, MAT_KHAU);
    }

    public static void gan(PreparedStatement ps, int viTri, Object giaTri, int kieuSql) throws SQLException {
        if (giaTri == null) ps.setNull(viTri, kieuSql);
        else ps.setObject(viTri, giaTri);
    }
}
