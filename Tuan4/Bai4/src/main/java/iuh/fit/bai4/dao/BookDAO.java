package iuh.fit.bai4.dao;

import iuh.fit.bai4.model.Book;
import iuh.fit.bai4.util.DBUtil;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {

    private final DBUtil dbUtil;

    public BookDAO(DataSource dataSource) {
        dbUtil = new DBUtil(dataSource);
    }

    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books ORDER BY id";

        try (
                Connection conn = dbUtil.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)
        ) {
            while (rs.next()) {
                books.add(mapResultSetToBook(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching all books: " + e.getMessage(), e);
        }

        return books;
    }

    public Book getBookById(int id) {
        String sql = "SELECT * FROM books WHERE id = ?";

        try (
                Connection conn = dbUtil.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToBook(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching book by id " + id + ": " + e.getMessage(), e);
        }

        return null;
    }

    public List<Book> searchBooks(String keyword) {
        List<Book> books = new ArrayList<>();

        try (Connection conn = dbUtil.getConnection()) {
            String titleCol = getTitleColumnName(conn);
            String sql = "SELECT * FROM books WHERE " + titleCol + " LIKE ? OR author LIKE ? ORDER BY id";

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                String key = "%" + (keyword == null ? "" : keyword.trim()) + "%";
                ps.setString(1, key);
                ps.setString(2, key);

                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        books.add(mapResultSetToBook(rs));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error searching books: " + e.getMessage(), e);
        }

        return books;
    }

    private String getTitleColumnName(Connection conn) {
        try (ResultSet rs = conn.getMetaData().getColumns(null, null, "books", "title")) {
            if (rs.next()) {
                return "title";
            }
        } catch (Exception ignored) {
        }
        return "tittle";
    }

    private boolean hasColumn(ResultSetMetaData meta, int colCount, String colName) throws SQLException {
        for (int i = 1; i <= colCount; i++) {
            if (colName.equalsIgnoreCase(meta.getColumnLabel(i)) || colName.equalsIgnoreCase(meta.getColumnName(i))) {
                return true;
            }
        }
        return false;
    }

    private Book mapResultSetToBook(ResultSet rs) throws SQLException {
        ResultSetMetaData meta = rs.getMetaData();
        int colCount = meta.getColumnCount();

        int id = rs.getInt("id");

        String title = "";
        if (hasColumn(meta, colCount, "title")) {
            title = rs.getString("title");
        } else if (hasColumn(meta, colCount, "tittle")) {
            title = rs.getString("tittle");
        }

        String author = hasColumn(meta, colCount, "author") ? rs.getString("author") : "";

        String image = "";
        if (hasColumn(meta, colCount, "image")) {
            image = rs.getString("image");
        } else if (hasColumn(meta, colCount, "imgbook")) {
            image = rs.getString("imgbook");
        }

        double price = 50000;
        if (hasColumn(meta, colCount, "price")) {
            double p = rs.getDouble("price");
            if (p > 0) {
                price = p;
            }
        }

        int quantity = 10;
        if (hasColumn(meta, colCount, "quantity")) {
            int q = rs.getInt("quantity");
            if (q > 0) {
                quantity = q;
            }
        }

        String description = "Tác phẩm xuất sắc: " + title + " của tác giả " + author + ".";
        if (hasColumn(meta, colCount, "description")) {
            String d = rs.getString("description");
            if (d != null && !d.isBlank()) {
                description = d;
            }
        }

        return new Book(id, title, author, description, price, quantity, image);
    }
}