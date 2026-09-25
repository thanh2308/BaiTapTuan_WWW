package com.example.huynhhoaithanh_23633801_bai2.controller;

import com.example.huynhhoaithanh_23633801_bai2.dao.AccountUtil;
import com.example.huynhhoaithanh_23633801_bai2.model.Account;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/registerform")
public class AccountRegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Resource(name = "jdbc/storedb")
    private DataSource dataSource;

    private AccountUtil accountUtil;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        accountUtil = new AccountUtil(dataSource);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            List<Account> accounts = accountUtil.getAccounts();
            req.setAttribute("accounts", accounts);
            req.getRequestDispatcher("/account.jsp").forward(req, resp);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        int day = Integer.parseInt(req.getParameter("day"));
        int month = Integer.parseInt(req.getParameter("month"));
        int year = Integer.parseInt(req.getParameter("year"));

        LocalDate localDate = LocalDate.of(year, month, day);
        Date dob = Date.valueOf(localDate);

        Account account = new Account(firstname, lastname, email, password, dob);

        try {
            accountUtil.addAccount(account);
            List<Account> accounts = accountUtil.getAccounts();
            req.setAttribute("accounts", accounts);
            req.getRequestDispatcher("/account.jsp").forward(req, resp);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}