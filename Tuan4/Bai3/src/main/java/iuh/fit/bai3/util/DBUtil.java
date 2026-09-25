package iuh.fit.bai3.util;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

    private DataSource dataSource;

    public DBUtil(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Connection getConnection() {
        // 1. Thử lấy từ DataSource của Tomcat
        if (this.dataSource == null) {
            try {
                InitialContext ctx = new InitialContext();
                try {
                    this.dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/shopdb");
                } catch (NamingException e) {
                    this.dataSource = (DataSource) ctx.lookup("jdbc/shopdb");
                }
            } catch (Exception ignored) {
            }
        }

        if (this.dataSource != null) {
            try {
                return this.dataSource.getConnection();
            } catch (Exception e) {
                System.err.println("DataSource connection failed, falling back to DriverManager: " + e.getMessage());
            }
        }

        // 2. Fallback trực tiếp qua DriverManager đảm bảo luôn kết nối thành công
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            return DriverManager.getConnection(
                    "jdbc:mariadb://localhost:3306/shopdb?useUnicode=true&characterEncoding=UTF-8",
                    "root",
                    "sapassword"
            );
        } catch (Exception e) {
            throw new RuntimeException("Cannot get DB connection", e);
        }
    }
}