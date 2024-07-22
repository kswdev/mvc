package org.example.jdbc.domain.user.repository;

import org.example.customer.domain.User;
import org.example.jdbc.domain.util.JdbcTemplate;

import java.sql.*;

public class UserDao {

    private final JdbcTemplate jdbcTemplate = new JdbcTemplate();

    public void create(User user) throws SQLException {
        String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?)";
        jdbcTemplate.executeUpdate(sql, ps -> {
            ps.setString(1, user.getUserId());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getName());
            ps.setString(4, user.getEmail());
        });
    }


    public User findById(String userId) throws SQLException {
        String sql = "SELECT userId, password, name, email FROM USERS WHERE userId = ?";

        return (User) jdbcTemplate.executeQuery(sql,
                ps -> ps.setString(1, userId),
                rs -> {
                    String selectedUserId = rs.getString("userId");
                    String selectedPassword = rs.getString("password");
                    String selectedName = rs.getString("name");
                    String selectedEmail = rs.getString("email");

                    return new User(selectedUserId, selectedPassword, selectedName, selectedEmail);
        });
    }
}
