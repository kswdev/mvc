package org.example.jdbc.domain.util;

import java.sql.SQLException;

public interface PreparedStatementSetter<T> {

    void setter(T t) throws SQLException;
}
