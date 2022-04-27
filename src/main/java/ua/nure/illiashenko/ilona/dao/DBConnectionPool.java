package ua.nure.illiashenko.ilona.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

import static ua.nure.illiashenko.ilona.constants.DBConnectionData.MY_SQL_DRIVER_CLASS_NAME;
import static ua.nure.illiashenko.ilona.constants.DBConnectionData.MY_SQL_JDBC_URL;
import static ua.nure.illiashenko.ilona.constants.DBConnectionData.MY_SQL_PASSWORD;
import static ua.nure.illiashenko.ilona.constants.DBConnectionData.MY_SQL_USERNAME;


public class DBConnectionPool {

    private static final Logger logger = LoggerFactory.getLogger(DBConnectionPool.class);
    private final HikariDataSource dataSource;

    public DBConnectionPool() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(MY_SQL_JDBC_URL);
        config.setUsername(MY_SQL_USERNAME);
        config.setPassword(MY_SQL_PASSWORD);
        config.setDriverClassName(MY_SQL_DRIVER_CLASS_NAME);
        dataSource = new HikariDataSource(config);
    }

    public Connection getDBConnection() throws SQLException {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new SQLException("Cannot get connection!");
        }
    }
}
