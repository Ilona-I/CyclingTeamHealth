package ua.nure.illiashenko.ilona.dao;

import ua.nure.illiashenko.ilona.dao.entities.User;

import java.sql.Connection;
import java.sql.SQLException;

public class UserDAO implements DAO<User, String> {

    @Override
    public boolean insert(User user, Connection connection) throws SQLException {
        return false;
    }

    @Override
    public User get(String key, Connection connection) throws SQLException {
        return null;
    }

    @Override
    public boolean update(String key, User newEntity, Connection connection) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String key, Connection connection) throws SQLException {
        return false;
    }
}
