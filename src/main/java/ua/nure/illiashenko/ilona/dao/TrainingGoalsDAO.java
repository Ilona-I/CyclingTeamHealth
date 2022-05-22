package ua.nure.illiashenko.ilona.dao;

import ua.nure.illiashenko.ilona.dao.entities.TrainingGoals;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public class TrainingGoalsDAO implements DAO<TrainingGoals, Integer>{
    @Override
    public boolean insert(TrainingGoals trainingGoals, Connection connection) throws SQLException {
        return false;
    }

    @Override
    public Optional<TrainingGoals> get(Integer key, Connection connection) throws SQLException {
        return null;
    }

    @Override
    public boolean update(Integer key, TrainingGoals newEntity, Connection connection) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(Integer key, Connection connection) throws SQLException {
        return false;
    }
}
