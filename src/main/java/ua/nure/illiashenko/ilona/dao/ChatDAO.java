package ua.nure.illiashenko.ilona.dao;

import ua.nure.illiashenko.ilona.dao.entities.Chat;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public class ChatDAO implements DAO<Chat, Integer>{

    @Override
    public boolean insert(Chat chat, Connection connection) throws SQLException {
        return false;
    }

    @Override
    public Optional<Chat> get(Integer key, Connection connection) throws SQLException {
        return null;
    }

    @Override
    public boolean update(Integer key, Chat newEntity, Connection connection) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(Integer key, Connection connection) throws SQLException {
        return false;
    }
}
