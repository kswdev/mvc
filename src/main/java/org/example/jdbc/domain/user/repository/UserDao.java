package org.example.jdbc.domain.user.repository;

import org.example.customer.domain.User;

import java.sql.*;

public class UserDao {

    private Connection getConnection() {
        String url = "jdbc:h2:tcp://localhost:1521/test";
        String id = "sa";
        String pw = "";

        try {
            Class.forName("org.h2.Driver");
            return DriverManager.getConnection(url, id, pw);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void create(User user) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();
            String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getUserId());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getName());
            pstmt.setString(4, user.getEmail());

            pstmt.executeUpdate();
        } finally {
            if (pstmt != null)
                pstmt.close();

            if (conn != null)
                conn.close();
        }
    }


    public User findById(String userId) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            String sql = "SELECT userId, password, name, email FROM USERS WHERE userId = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userId);

            rs = pstmt.executeQuery();

            User user = null;
            if (rs.next()) {
                String selectedUserId = rs.getString("userId");
                String selectedPassword = rs.getString("password");
                String selectedName = rs.getString("name");
                String selectedEmail = rs.getString("email");

                user = new User(selectedUserId, selectedPassword, selectedName, selectedEmail);
            }
            return user;
        } finally {
            if (rs != null)
                rs.close();

            if (pstmt != null)
                pstmt.close();

            if (conn != null)
                conn.close();
        }
    }
}
