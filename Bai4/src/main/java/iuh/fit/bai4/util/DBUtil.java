package iuh.fit.bai4.util;

import javax.naming.Context;
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
        if (dataSource != null) {
            try {
                return dataSource.getConnection();
            } catch (SQLException ignored) {
            }
        }

        try {
            Context initContext = new InitialContext();
            try {
                Context envContext = (Context) initContext.lookup("java:/comp/env");
                dataSource = (DataSource) envContext.lookup("jdbc/bookstoredb");
            } catch (NamingException e) {
                dataSource = (DataSource) initContext.lookup("jdbc/bookstoredb");
            }
            if (dataSource != null) {
                return dataSource.getConnection();
            }
        } catch (Exception ignored) {
        }

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            return DriverManager.getConnection(
                    "jdbc:mariadb://localhost:3306/bookstoredb?useUnicode=true&characterEncoding=UTF-8",
                    "root",
                    "sapassword"
            );
        } catch (Exception e) {
            throw new RuntimeException("Cannot connect to database: " + e.getMessage(), e);
        }
    }
}