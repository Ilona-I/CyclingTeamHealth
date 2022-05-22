package ua.nure.illiashenko.ilona.dao;

import ua.nure.illiashenko.ilona.dao.entities.Message;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public class MessageDAO implements DAO <Message, Integer>{

    @Override
    public boolean insert(Message message, Connection connection) throws SQLException {
        return false;
    }

    @Override
    public Optional<Message> get(Integer key, Connection connection) throws SQLException {
        return null;
    }

    @Override
    public boolean update(Integer key, Message newEntity, Connection connection) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(Integer key, Connection connection) throws SQLException {
        return false;
    }
}
