package org.example.java_basic.jdbc.domain.util;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper {
    Object map(ResultSet rs) throws SQLException;
}
