package iuh.fit.bai4.servlet;

import iuh.fit.bai4.dao.BookDAO;
import iuh.fit.bai4.model.Book;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "BookServlet", urlPatterns = {
        "",
        "/books",
        "/book",
        "/search"
})
public class BookServlet extends HttpServlet {

    private BookDAO bookDAO;

    @Resource(name = "jdbc/bookstoredb")
    private DataSource dataSource;

    @Override
    public void init() {
        bookDAO = new BookDAO(dataSource);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String servletPath = req.getServletPath();

        // Chi tiết sách
        if ("/book".equals(servletPath)) {
            try {
                int id = Integer.parseInt(req.getParameter("id"));
                Book book = bookDAO.getBookById(id);

                if (book == null) {
                    resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Không tìm thấy sách");
                    return;
                }

                req.setAttribute("book", book);
                req.getRequestDispatcher("/chitietsach.jsp").forward(req, resp);
                return;
            } catch (NumberFormatException e) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID sách không hợp lệ");
                return;
            }
        }

        // Tìm kiếm sách
        if ("/search".equals(servletPath)) {
            String keyword = req.getParameter("keyword");
            if (keyword == null) {
                keyword = "";
            }

            List<Book> books = bookDAO.searchBooks(keyword);
            req.setAttribute("books", books);
            req.setAttribute("keyword", keyword);
            req.getRequestDispatcher("/danhsach.jsp").forward(req, resp);
            return;
        }

        // Danh sách toàn bộ sách (/books)
        List<Book> books = bookDAO.getAllBooks();
        req.setAttribute("books", books);
        req.getRequestDispatcher("/danhsach.jsp").forward(req, resp);
    }
}