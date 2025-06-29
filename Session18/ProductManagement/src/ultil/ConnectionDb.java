package ultil;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDb {
    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/productdb",
                    "root",
                    "Weak"

            );
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(" Lỗi kết nối DB: " + e.getMessage(), e);
        }
        return conn;
    }

    public static void closeConnection(Connection conn, CallableStatement call) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if (call != null) {
            try {
                call.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }


}
