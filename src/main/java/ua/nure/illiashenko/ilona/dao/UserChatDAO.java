package ua.nure.illiashenko.ilona.dao;

import ua.nure.illiashenko.ilona.dao.entities.UserChat;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public class UserChatDAO implements DAO<UserChat, Integer> {
    @Override
    public boolean insert(UserChat userChat, Connection connection) throws SQLException {
        return false;
    }

    @Override
    public Optional<UserChat> get(Integer key, Connection connection) throws SQLException {
        return null;
    }

    @Override
    public boolean update(Integer key, UserChat newEntity, Connection connection) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(Integer key, Connection connection) throws SQLException {
        return false;
    }
}
