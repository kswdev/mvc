package org.example.jdbc.domain.util;

import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionManager {

    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:tcp://localhost:1521/test";
    private static final String DB_ID = "sa";
    private static final String DB_PW = "";
    private static final int MAX_POOL_SIZE = 40;
    private static final DataSource ds;

    static {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName(DB_DRIVER);
        dataSource.setJdbcUrl(DB_URL);
        dataSource.setUsername(DB_ID);
        dataSource.setPassword(DB_PW);
        dataSource.setMaximumPoolSize(MAX_POOL_SIZE);
        dataSource.setMinimumIdle(MAX_POOL_SIZE);

        ds = dataSource;
    }

    public static Connection getConnection() {
        try {
            return ds.getConnection();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static DataSource getDataSource() {
        return ds;
    }
}
