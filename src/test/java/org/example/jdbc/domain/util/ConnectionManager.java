package org.example.jdbc.domain.util;

import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

public class ConnectionManager {

    public static DataSource getDataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setJdbcUrl("jdbc:h2:tcp://localhost:1521/test");
        dataSource.setUsername("sa");
        dataSource.setPassword("");

        return dataSource;
    }
}
