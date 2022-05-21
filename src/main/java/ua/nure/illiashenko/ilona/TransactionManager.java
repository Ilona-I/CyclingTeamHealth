package ua.nure.illiashenko.ilona;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.illiashenko.ilona.dao.DBConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.function.Function;

public class TransactionManager {

    private static final Logger logger = LoggerFactory.getLogger(TransactionManager.class);
    private final DBConnectionPool dbConnectionPool;

    public TransactionManager(DBConnectionPool dbConnectionPool) {
        this.dbConnectionPool = dbConnectionPool;
    }

    public <R> R doInTransaction(Function<Connection, R> function) throws SQLException {
        try (Connection connection = dbConnectionPool.getDBConnection()) {
            startTransaction(connection);
            R result = function.apply(connection);
            commit(connection);
            finishTransaction(connection);
            return result;
        }
    }

    private void startTransaction(Connection connection) throws SQLException {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new SQLException();
        }
    }

    private void commit(Connection connection) throws SQLException {
        try {
            connection.commit();
        } catch (SQLException e) {
            rollback(connection);
            logger.error(e.getMessage());
            throw new SQLException();
        }
    }

    private void rollback(Connection connection) throws SQLException {
        try {
            connection.rollback();
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new SQLException();
        }
    }

    private void finishTransaction(Connection connection) throws SQLException {
        try {
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new SQLException();
        }
    }
}
