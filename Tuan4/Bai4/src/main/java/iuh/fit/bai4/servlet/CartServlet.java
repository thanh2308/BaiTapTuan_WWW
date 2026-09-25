package iuh.fit.bai4.servlet;

import iuh.fit.bai4.dao.BookDAO;
import iuh.fit.bai4.model.Book;
import iuh.fit.bai4.model.Cart;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import javax.sql.DataSource;
import java.io.IOException;

@WebServlet(name = "CartServlet", urlPatterns = {
        "/cart",
        "/giohang",
        "/checkout",
        "/thanhtoan"
})
public class CartServlet extends HttpServlet {

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

        HttpSession session = req.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }

        String servletPath = req.getServletPath();
        if ("/checkout".equals(servletPath) || "/thanhtoan".equals(servletPath)) {
            req.getRequestDispatcher("/thanhtoan.jsp").forward(req, resp);
            return;
        }

        req.getRequestDispatcher("/giohang.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }

        String servletPath = req.getServletPath();

        // Xử lý lưu thông tin thanh toán (thanhtoan.jsp)
        if ("/checkout".equals(servletPath) || "/thanhtoan".equals(servletPath)) {
            String fullname = req.getParameter("fullname");
            String address = req.getParameter("address");
            String payment = req.getParameter("payment");

            cart.clear(); // Xóa giỏ sau khi đặt hàng thành công
            req.setAttribute("message", "Đặt hàng thành công! Đơn hàng sẽ được chuyển đến: " + address + " (" + payment + "). Cảm ơn quý khách " + (fullname != null ? fullname : "") + "!");
            req.getRequestDispatcher("/thanhtoan.jsp").forward(req, resp);
            return;
        }

        // Xử lý các thao tác giỏ hàng
        String action = req.getParameter("action");

        try {
            if ("add".equals(action)) {
                int id = Integer.parseInt(req.getParameter("id"));
                Book book = bookDAO.getBookById(id);

                if (book != null) {
                    cart.addBook(book);
                }
            } else if ("update".equals(action)) {
                int id = Integer.parseInt(req.getParameter("id"));
                int quantity = Integer.parseInt(req.getParameter("quantity"));

                cart.updateQuantity(id, quantity);
            } else if ("remove".equals(action)) {
                int id = Integer.parseInt(req.getParameter("id"));
                cart.removeBook(id);
            } else if ("clear".equals(action)) {
                cart.clear();
            }
        } catch (NumberFormatException e) {
            throw new ServletException("Dữ liệu không hợp lệ", e);
        }

        resp.sendRedirect(req.getContextPath() + "/cart");
    }
}