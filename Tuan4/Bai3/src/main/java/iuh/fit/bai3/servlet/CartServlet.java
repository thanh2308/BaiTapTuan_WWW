package iuh.fit.bai3.servlet;

import iuh.fit.bai3.beans.CartBean;
import iuh.fit.bai3.beans.Product;
import iuh.fit.bai3.dao.ProductDAO;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import javax.sql.DataSource;
import java.io.IOException;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {

    private ProductDAO productDAO;

    @Resource(name = "jdbc/shopdb")
    private DataSource dataSource;

    @Override
    public void init() {
        productDAO = new ProductDAO(dataSource);
    }

    @Override
    protected void doGet(
            HttpServletRequest req,
            HttpServletResponse resp)
            throws ServletException, IOException {

        req.getRequestDispatcher(
                "/cart.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(
            HttpServletRequest req,
            HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession();

        CartBean cart = (CartBean) session.getAttribute("cart");

        if (cart == null) {

            cart = new CartBean();

            session.setAttribute(
                    "cart",
                    cart);
        }

        String action = req.getParameter("action");

        try {

            // ADD
            if ("add".equals(action)) {

                int id = Integer.parseInt(
                        req.getParameter("id"));

                int quantity = Integer.parseInt(
                        req.getParameter("quantity"));

                if (quantity < 1) {
                    quantity = 1;
                }

                Product p = productDAO.getProductById(id);

                if (p != null) {

                    for (int i = 0; i < quantity; i++) {

                        cart.addProduct(p);
                    }
                }

            }

            // UPDATE
            else if ("update".equals(action)) {

                int id = Integer.parseInt(
                        req.getParameter("productId"));

                int quantity = Integer.parseInt(
                        req.getParameter("quantity"));

                cart.updateQuantity(
                        id,
                        quantity);
            }

            // REMOVE
            else if ("remove".equals(action)) {

                int id = Integer.parseInt(
                        req.getParameter("productId"));

                cart.removeProduct(id);
            }

            // CLEAR
            else if ("clear".equals(action)) {

                cart.clear();
            }

        } catch (Exception e) {

            throw new ServletException(
                    "Invalid cart request",
                    e);
        }

        resp.sendRedirect(
                req.getContextPath() + "/cart");
    }
}