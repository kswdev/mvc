package org.example.jdbc.domain.user.repository;

import org.example.customer.domain.User;
import org.example.jdbc.domain.util.ConnectionManager;
import org.example.jdbc.domain.user.repository.UserDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

public class UserRepositoryTest {

    @BeforeEach
    void setUp() {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("db_schema.sql"));
        DatabasePopulatorUtils.execute(populator, ConnectionManager.getDataSource());
    }

    @Test
    void createUser() throws SQLException {
        UserDao userDao = new UserDao();

        userDao.create(new User("wizard", "password", "name", "email"));

        User user = userDao.findById("wizard");
        assertThat(user).isEqualTo(new User("wizard", "password", "name", "email"));
    }
}
