package ua.nure.illiashenko.ilona.dao;

import ua.nure.illiashenko.ilona.dao.entities.TrainingResults;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public class TrainingResultsDAO implements DAO<TrainingResults, Integer> {

    @Override
    public boolean insert(TrainingResults trainingResults, Connection connection) throws SQLException {
        return false;
    }

    @Override
    public Optional<TrainingResults> get(Integer key, Connection connection) throws SQLException {
        return null;
    }

    @Override
    public boolean update(Integer key, TrainingResults newEntity, Connection connection) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(Integer key, Connection connection) throws SQLException {
        return false;
    }
}
