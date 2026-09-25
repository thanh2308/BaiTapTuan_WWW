package iuh.fit.bai3.servlet;

import iuh.fit.bai3.beans.Product;
import iuh.fit.bai3.dao.ProductDAO;
import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

@WebServlet({ "/products", "/product" })
public class ProductServlet extends HttpServlet {

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

                String idStr = req.getParameter("id");

                // Xem chi tiết
                if (idStr != null && !idStr.isBlank()) {

                        try {

                                int id = Integer.parseInt(idStr);

                                Product product = productDAO.getProductById(id);

                                if (product != null) {

                                        req.setAttribute(
                                                        "product",
                                                        product);

                                        RequestDispatcher dispatcher = getServletContext()
                                                        .getRequestDispatcher(
                                                                        "/product-detail.jsp");

                                        dispatcher.forward(req, resp);

                                        return;
                                }

                                resp.sendError(
                                                HttpServletResponse.SC_NOT_FOUND,
                                                "Product not found");

                                return;

                        } catch (NumberFormatException e) {

                                resp.sendError(
                                                HttpServletResponse.SC_BAD_REQUEST,
                                                "Invalid product id");

                                return;
                        }
                }

                // Danh sách sản phẩm
                List<Product> products = productDAO.getAllProducts();

                req.setAttribute(
                                "products",
                                products);

                RequestDispatcher dispatcher = getServletContext()
                                .getRequestDispatcher(
                                                "/product-list.jsp");

                dispatcher.forward(req, resp);
        }
}