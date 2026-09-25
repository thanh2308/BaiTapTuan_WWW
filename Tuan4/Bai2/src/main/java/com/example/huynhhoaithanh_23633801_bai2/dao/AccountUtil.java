package com.example.huynhhoaithanh_23633801_bai2.dao;

import com.example.huynhhoaithanh_23633801_bai2.model.Account;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountUtil {
    private final DataSource datasource;

    public AccountUtil(DataSource datasource) {
        this.datasource = datasource;
    }

    public List<Account> getAccounts() throws SQLException {
        List<Account> accounts = new ArrayList<>();
        String sql = "SELECT * FROM accounts ORDER BY ID DESC";

        try (Connection conn = datasource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("ID");
                String fname = rs.getString("FIRSTNAME");
                String lname = rs.getString("LASTNAME");
                String email = rs.getString("EMAIL");
                String password = rs.getString("PASSWORD");
                Date dob = rs.getDate("DATEOFBIRTH");

                accounts.add(new Account(id, fname, lname, email, password, dob));
            }
        }
        return accounts;
    }

    public void addAccount(Account acc) throws SQLException {
        String sql = "INSERT INTO accounts (FIRSTNAME, LASTNAME, EMAIL, PASSWORD, DATEOFBIRTH) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = datasource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, acc.getFirstname());
            ps.setString(2, acc.getLastname());
            ps.setString(3, acc.getEmail());
            ps.setString(4, acc.getPassword());
            ps.setDate(5, acc.getDateOfBirth());

            ps.executeUpdate();
        }
    }
}