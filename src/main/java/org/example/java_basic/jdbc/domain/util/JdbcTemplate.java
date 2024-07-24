package org.example.java_basic.jdbc.domain.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcTemplate {

    public void executeUpdate(String sql, PreparedStatementSetter<PreparedStatement> pstmts) throws SQLException {

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmts.setter(pstmt);

            pstmt.executeUpdate();
        }
    }

    public Object executeQuery(String sql, PreparedStatementSetter<PreparedStatement> pstmts, RowMapper mapper) throws SQLException {
        try(Connection conn = ConnectionManager.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);) {

            pstmts.setter(pstmt);

            try(ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapper.map(rs);
                }
            }

            return null;
        }
    }
}
