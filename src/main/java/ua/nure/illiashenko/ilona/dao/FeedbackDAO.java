package ua.nure.illiashenko.ilona.dao;

import ua.nure.illiashenko.ilona.dao.entities.Feedback;

import java.sql.Connection;
import java.sql.SQLException;

public class FeedbackDAO implements DAO<Feedback, Integer>{
    @Override
    public boolean insert(Feedback feedback, Connection connection) throws SQLException {
        return false;
    }

    @Override
    public Feedback get(Integer key, Connection connection) throws SQLException {
        return null;
    }

    @Override
    public boolean update(Integer key, Feedback newEntity, Connection connection) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(Integer key, Connection connection) throws SQLException {
        return false;
    }
}
