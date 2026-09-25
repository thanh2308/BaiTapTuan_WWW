package iuh.fit.bai3.dao;


import iuh.fit.bai3.beans.Product;
import iuh.fit.bai3.util.DBUtil;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    private final DBUtil dbUtil;

    public ProductDAO(DataSource dataSource) {
        dbUtil = new DBUtil(dataSource);
    }

    // READ ALL
    public List<Product> getAllProducts() {

        List<Product> list = new ArrayList<>();

        String sql =
                "SELECT ID, MODEL, DESCRIPTION, QUANTITY, PRICE, IMGURL " +
                        "FROM products ORDER BY ID";

        try (
                Connection conn = dbUtil.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)
        ) {

            while (rs.next()) {

                Product p = new Product(
                        rs.getInt("ID"),
                        rs.getString("MODEL"),
                        rs.getString("DESCRIPTION"),
                        rs.getInt("QUANTITY"),
                        rs.getDouble("PRICE"),
                        rs.getString("IMGURL")
                );

                list.add(p);
            }

        } catch (Exception e) {

            throw new RuntimeException(
                    "Cannot load products",
                    e
            );
        }

        return list;
    }

    // READ BY ID
    public Product getProductById(int id) {

        String sql =
                "SELECT ID, MODEL, DESCRIPTION, QUANTITY, PRICE, IMGURL " +
                        "FROM products WHERE ID = ?";

        try (
                Connection conn = dbUtil.getConnection();
                PreparedStatement ps =
                        conn.prepareStatement(sql)
        ) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    return new Product(
                            rs.getInt("ID"),
                            rs.getString("MODEL"),
                            rs.getString("DESCRIPTION"),
                            rs.getInt("QUANTITY"),
                            rs.getDouble("PRICE"),
                            rs.getString("IMGURL")
                    );
                }
            }

        } catch (Exception e) {

            throw new RuntimeException(
                    "Cannot load product",
                    e
            );
        }

        return null;
    }
}